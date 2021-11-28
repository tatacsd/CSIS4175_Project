package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // Field variables
    private Button signupFragmentBtn, loginFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get toolbar items reference
        ImageView toolbar_arrowBack = findViewById(R.id.toolbar_backArrow);
        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_appName = findViewById(R.id.toolbar_app_name);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);


        // Create a button reference
        signupFragmentBtn = findViewById(R.id.signup_btn_main);
        loginFragmentBtn = findViewById(R.id.login_btn_main);

        // Sign up click listener to inflate the fragment
        signupFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the fragment
//                addFragmentToView(new Signup());

                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Longin click listener to inflate the fragment
        loginFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    // Method that add the fragment to the view
//    private void addFragmentToView(Fragment fragment) {
//        // Use fragment manager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout_main, fragment);
//        fragmentTransaction.commit();
//    }
}