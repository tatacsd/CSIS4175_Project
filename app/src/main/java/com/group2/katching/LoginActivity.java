package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group2.katching.entity.User;
import com.group2.katching.ui.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth auth;
    private String userId;
    EditText etUsername;
    EditText etPassword;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        auth = FirebaseAuth.getInstance();


        Button loginButton = findViewById(R.id.btnLogin);

        etUsername = (EditText) findViewById(R.id.txtUserEmail);
        etPassword = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

            private void submitForm() {
                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(!checkEmail()) {
                    return;
                }
                if(!checkPassword()) {
                    return;
                }


                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {


                                    FirebaseUser user = auth.getCurrentUser();
                                    //send user to viewmodel
                                    userViewModel.setUser(user);

                                    startActivity(new Intent (LoginActivity.this, HomeActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "failed to login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });






    }

    private boolean checkEmail() {
        String email = etUsername.getText().toString().trim();
        if(email.isEmpty() || !isEmailValid(email)) {

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