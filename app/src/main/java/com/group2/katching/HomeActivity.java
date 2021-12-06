package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.User;
import com.group2.katching.ui.UserViewModel;

public class HomeActivity extends AppCompatActivity {

    FirebaseUser user;
    UserViewModel userViewModel;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseDatabase mFirebase;
    FloatingActionButton transactionsBtn;
    private boolean isMenuDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        transactionsBtn = findViewById(R.id.floatingActionButton);

        auth = FirebaseAuth.getInstance();

        // Display all fragments
        displayFragment(new YourAccountFragment(), R.id.accountFragment);
        displayFragment(new DepositFragment(), R.id.depositFragment);
        displayFragment(new TransferFragment(), R.id.transferFragment);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_appName = findViewById(R.id.toolbar_app_name);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);

        // Turn sandwich menu visible
        toolbar_menu.setVisibility(View.VISIBLE);

        // when the user clicks display a menu with logout btn
        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (!isMenuDisplayed) {
                    // Inflate a layout with the logout option
                    // load the fragment on top of the AdminDashboard
                    fragmentTransaction.add(R.id.logoutFragmentContainerViewClient, new MenuLogoutFragmentClient())
                            .addToBackStack(null).commit();
                    isMenuDisplayed = true;
                } else {
                    // Remove fragment
                    fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.logoutFragmentContainerViewClient))
                            .commit();
                    isMenuDisplayed = false;
                }
            }
        });

        // change to admin page toolbar color and icon
        toolbar_id.setBackgroundColor(ContextCompat.getColor(this, R.color.PrimaryPurple));
        toolbar_logo.setImageResource(R.drawable.logo_green_app);

        transactionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TransactionsActivity.class);
                intent.putExtra("email", userViewModel.getUserData().getEmail());
                startActivity(intent);
            }
        });




        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                } else {
                    // just for test the user retrieving
                    String email = user.getEmail();
                    mFirebase = FirebaseDatabase.getInstance();
                    DatabaseReference data = mFirebase.getReference("users");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.v("check", "changecheck");
                            boolean matchFound = false;
                            for (DataSnapshot child : snapshot.getChildren()) {
                                String dataBaseId = String.valueOf(child.getKey());
                                String email = String.valueOf(child.child("email").getValue()).toLowerCase();
                                String balance = String.valueOf(child.child("balance").getValue()).toLowerCase();
                                if (user.getEmail().equals(email)) {
                                    matchFound = true;
                                    User appUser = new User(dataBaseId, email, false, Double.valueOf(balance));
                                    userViewModel.setUserData(appUser);
                                }
                            }
                            if (matchFound)
                                Log.v("found", "match for " + user.getEmail() + "(id: "
                                        + userViewModel.getUserData().getDataBaseId() + ")" + " found.");
                            else
                                Log.v("not found", "no match found");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        };
    }

    private void displayFragment(Fragment fragment, int containerViewId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment).addToBackStack(null).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }
}