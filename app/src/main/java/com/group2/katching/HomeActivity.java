package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group2.katching.ui.UserViewModel;

public class HomeActivity extends AppCompatActivity {

    FirebaseUser user;
    UserViewModel userViewModel;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();

        // Display all fragments
        displayFragment(new YourAccountFragment(),R.id.accountFragment);
        displayFragment(new DepositFragment(),R.id.depositFragment);
        displayFragment(new TransferFragment(),R.id.transferFragment);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user == null){
                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                    finish();
                } else{
                    user = firebaseAuth.getCurrentUser();

                    //just for test the user retrieving
                    String email = user.getEmail();
                    Toast.makeText(HomeActivity.this,email, Toast.LENGTH_LONG).show();

                    

                }
            }
        };
    }

    private void displayFragment(Fragment fragment, int containerViewId ) {
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