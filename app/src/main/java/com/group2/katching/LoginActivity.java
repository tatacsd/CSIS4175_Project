package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername;
        EditText etPassword;

        etUsername = findViewById(R.id.txtUserEmail);
        etPassword = findViewById(R.id.password);


    }
}