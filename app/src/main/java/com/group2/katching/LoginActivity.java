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
        // String email = etUsername.getText().toString().trim();
        // String password = etPassword.getText().toString().trim();

        String email = "oi@oi.ca";
        String password = "123456";

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
                            Log.v("test", "TestLogin email and password exist");
                            checkUserStatus();
                        }

                    }
                });
    }

    private void checkUserStatus() {
        Log.v("test", "TestLogin inside sendUserToRightScreen");
        // String email = etUsername.getText().toString().trim();
        String email = "oi@oi.ca";
        // Get the current user
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            // Get the reference from firebase
            mFirebaseDatabase = mFirebaseInstance.getReference("users");
            // Get the user from the database
            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        User user = child.getValue(User.class);
                        String emailDataSnap = String.valueOf(child.child("email").getValue());
                        boolean userStatusDataSnap = (boolean) child.child("userStatus").getValue();
                        String userIdDataSnap = String.valueOf(child.child("dataBaseId").getValue());
                        if (email.equals(emailDataSnap)) {
                            Log.v("test", "TestLogin databaseId: " + userIdDataSnap);
                            launchActivity(userStatusDataSnap, user);
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void launchActivity(boolean userStatus, User user) {
        Intent intent;
        if (userStatus) {
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
        // String email = etUsername.getText().toString().trim();
        String email = "oi@oi.ca";
        if (email.isEmpty() || !isEmailValid(email)) {

            Toast.makeText(LoginActivity.this, "wrong email or password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkPassword() {
        // String password = etPassword.getText().toString().trim();
        String password = "123456";
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