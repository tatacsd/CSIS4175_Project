package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        displayYourAccountFragment();
        displayTransferFragment();
    }

    public void displayYourAccountFragment() {
        YourAccountFragment simpleFragment = YourAccountFragment.newInstance();

        // TODO: Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragementTransaction = fragmentManager.beginTransaction();
    }

    public void displayTransferFragment() {
        TransferFragment simpleFragment = TransferFragment.newInstance();

        // TODO: Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragementTransaction = fragmentManager.beginTransaction();
    }
}