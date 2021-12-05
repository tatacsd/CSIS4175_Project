package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.User;
import com.group2.katching.ui.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    // UI Variables
    EditText etUsername, etPassword;
    Button loginBtn;
    private UserViewModel userViewModel;

    // DATABASE VARIABLES
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    // Firebase Authentication
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get ui reference
        loginBtn = findViewById(R.id.btnLogin);
        etUsername = (EditText) findViewById(R.id.txtUserEmail);
        etPassword = (EditText) findViewById(R.id.password);

        // Get the access to the firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // View model
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Get the reference from firebase
        auth = FirebaseAuth.getInstance();

        // on login btn submit the form and
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }


    private void submitForm() {
        // check if there is a user
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // validate them
        if (!checkEmail()) {
            return;
        }
        if (!checkPassword()) {
            return;
        }

        // Authenticate the user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Toast.makeText(LoginActivity.this, "failed to login", Toast.LENGTH_SHORT).show();
                        else {
//                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
                            sendUserToRightScreen();
                        }

                    }
                });
    }

    private void sendUserToRightScreen() {
        // auth listener to login the user
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                // If there is no created send user to signup
                if (user == null) {
                    startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                    finish();
                } else {
                    // Launch the client/Admin activity
                    String email = user.getEmail();

                    // Check if the user is admin or client
                    // Get user status
                    mFirebaseDatabase = mFirebaseInstance.getReference("users");
                    mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                if (user.getEmail().equals(email)) {
                                    // get user key
                                    userId = String.valueOf(child.getKey());
                                    // get the user status
                                    boolean userStatus = Boolean.valueOf((Boolean) child.child("userStatus").getValue());
                                    launchActivity(userStatus, user);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        };
    }

    private void launchActivity(boolean userStatus, FirebaseUser user) {
        Intent intent;
        if(userStatus){
            intent = new Intent(LoginActivity.this, AdminDashboard.class);
        } else {
            // Launch the correct activity
            intent = new Intent(LoginActivity.this, HomeActivity.class);
        }
        String userEmail = user.getEmail();
        intent.putExtra(userEmail, user.getEmail());
        startActivity(intent);
        finish();
    }

    private boolean checkEmail() {
        String email = etUsername.getText().toString().trim();
        if (email.isEmpty() || !isEmailValid(email)) {

            Toast.makeText(LoginActivity.this, "wrong email or password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkPassword() {
        String password = etPassword.getText().toString().trim();
        if (password.isEmpty() || !isPasswordValid(password)) {
            Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
            requestFocus(etPassword);
            return false;
        }
        return true;
    }

    private static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isPasswordValid(String password) {
        return (password.length() >= 6);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}