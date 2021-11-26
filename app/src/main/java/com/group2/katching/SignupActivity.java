package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText signupInputEmail;
    private EditText signupInputPassword;
    private Button btnSignUp;
    private Button btnLinkToLogIn;

    private static final String TAG = "FIREBASE AUTHENTICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        signupInputEmail = (EditText) findViewById(R.id.txtUserEmail);
        signupInputPassword = (EditText) findViewById(R.id.password);

        btnSignUp = (Button) findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
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

    }

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