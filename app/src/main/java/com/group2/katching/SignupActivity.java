package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.User;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText signupInputEmail;
    private EditText signupInputPassword;
    private Button btnSignUp;
    private Button btnLinkToLogIn;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private double balance = 0;
    private boolean status = false;

    private static final String TAG = "FIREBASE AUTHENTICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toast.makeText(SignupActivity.this, "test toast", Toast.LENGTH_SHORT).show();

        auth = FirebaseAuth.getInstance();

        signupInputEmail = (EditText) findViewById(R.id.txtUserEmail);
        signupInputPassword = (EditText) findViewById(R.id.password);

        btnSignUp = (Button) findViewById(R.id.btnSignup);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitForm();

                Toast.makeText(SignupActivity.this, "signed up", Toast.LENGTH_SHORT).show();

                SignupActivity.super.onBackPressed();

            }
        });




    }

    private void submitForm() {
        String email = signupInputEmail.getText().toString().trim();
        String password = signupInputPassword.getText().toString().trim();

        if(!checkEmail()) {
            return;
        }
        if(!checkPassword()) {
            return;
        }
        //setErrorEnabled

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()) {
                            startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Log.d(TAG, "Authentication failed." + task.getException());
                        }
                    }
                });

        if(TextUtils.isEmpty(email)) {
            createUser(email, balance, status);
        }

        Toast.makeText(getApplicationContext(),
                "You are successfully registered!", Toast.LENGTH_SHORT).show();


    }

    private void createUser(String email, double balance, boolean Status) {
        // In real apps this userId should be fetched
        // if you dont have an id, create one

        //add the user object values
         User user = new User(email, status, balance);
         mFirebaseDatabase.child(email).setValue(user);
          }

//    private void addUserChangeListener() {
//    }

    private boolean checkEmail() {

        String email = signupInputEmail.getText().toString().trim();

        if (email.isEmpty() || !isEmailValid(email)) {



            //Todo: Enter error message here

//            signupInputLayoutEmail.setError(getString(R.string.err_msg_email));
//
//            signupInputEmail.setError(getString(R.string.err_msg_required));

            requestFocus(signupInputEmail);

            return false;

        }

        //Todo: Enter error message here
//        signupInputLayoutEmail.setErrorEnabled(false);

        return true;

    }



    private boolean checkPassword() {



        String password = signupInputPassword.getText().toString().trim();

        if (password.isEmpty() || !isPasswordValid(password)) {


            //Todo: Enter error message here
//            signupInputLayoutPassword.setError(getString(R.string.err_msg_password));

//            signupInputPassword.setError(getString(R.string.err_msg_required));

            requestFocus(signupInputPassword);
            return false;
        }
        //Todo: Enter error message here
//        signupInputLayoutPassword.setErrorEnabled(false);
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

    @Override

    protected void onResume() {
        super.onResume();
//        progressBar.setVisibility(View.GONE);

    }
}