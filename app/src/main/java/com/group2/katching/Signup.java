package com.group2.katching;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Signup extends Fragment {


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