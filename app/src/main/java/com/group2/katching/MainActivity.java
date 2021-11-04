package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group2.katching.ui.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    // Field variables
    private Button signupFragmentBtn, loginFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a button reference
        signupFragmentBtn = findViewById(R.id.signup_btn);
        loginFragmentBtn = findViewById(R.id.login_btn);

        // Sign up click listener to inflate the fragment
        signupFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the fragment
                addFragmentToView(new Signup());
            }
        });

        // Longin click listener to inflate the fragment
        loginFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the fragment
                addFragmentToView(new LoginFragment());
            }
        });
    }

    // Method that add the fragment to the view
    private void addFragmentToView(Fragment fragment) {
        // Use fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }




}