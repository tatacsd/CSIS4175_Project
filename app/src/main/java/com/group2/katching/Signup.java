package com.group2.katching;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;


public class Signup extends Fragment {

    private FirebaseAuth auth;
//    private ProgressBar progressBar;
    private EditText signupInputEmail;
    private EditText signupInputPassword;
    private Button btnSignUp;
    private Button btnLinkToLogIn;
//    private TextInputLayout signupInputLayoutEmail;
//    private TextInputLayout signupInputLayoutPassword;
    private static final String TAG = "FIREBASE AUTHENTICATION";


    // Get the email and password editText
    View view;
    private EditText userEmail, userPassword;
    private Button createAccountBtn;

    public Signup() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        return view;
    }
}