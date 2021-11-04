package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Display all fragments
        displayFragment(new YourAccountFragment(),R.id.accountFragment);
        displayFragment(new DepositFragment(),R.id.depositFragment);
        displayFragment(new TransferFragment(),R.id.transferFragment);
    }

    private void displayFragment(Fragment fragment, int containerViewId ) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment).addToBackStack(null).commit();
    }


}