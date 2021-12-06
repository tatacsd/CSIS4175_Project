package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        // change to admin page toolbar color and icon
        toolbar_id.setBackgroundColor(ContextCompat.getColor(this, R.color.PrimaryPurple));
        toolbar_logo.setImageResource(R.drawable.logo_green_app);
    }
}