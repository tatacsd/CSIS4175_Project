package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LogoutActivity extends AppCompatActivity {

    private boolean isMenuDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_appName = findViewById(R.id.toolbar_app_name);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);
        ImageView arrow_back = findViewById(R.id.toolbar_backArrow);

        // Turn sandwich menu visible
        arrow_back.setVisibility(View.VISIBLE);

        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // change to admin page toolbar color and icon
        toolbar_id.setBackgroundColor(ContextCompat.getColor(this, R.color.PrimaryPurple));
        toolbar_logo.setImageResource(R.drawable.logo_green_app);
    }
}