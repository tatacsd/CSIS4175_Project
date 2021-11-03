package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        // Get toolbar items reference
        ImageView toolbar_arrowBack = findViewById(R.id.toolbar_backArrow);
        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_appName = findViewById(R.id.toolbar_app_name);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);

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