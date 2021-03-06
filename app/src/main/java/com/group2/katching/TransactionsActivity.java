package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.Transaction;
import com.group2.katching.transactionsrecyclerview.TransactionsAdapter;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

    final private String TAG = AdminDashboard.class.getSimpleName();
    RecyclerView recyclerView;
    TransactionsAdapter adapter;
    ArrayList<Transaction> transactionArrayList;

    // DATABASE VARIABLES
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth auth;


    private boolean isMenuDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        auth = FirebaseAuth.getInstance();
        String email = auth.getCurrentUser().getEmail();

        // Get toolbar items reference
        ImageView toolbar_arrowBack = findViewById(R.id.toolbar_backArrow);
        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_appName = findViewById(R.id.toolbar_app_name);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);

        // Turn sandwich menu visible
        toolbar_menu.setVisibility(View.VISIBLE);

        // when the user clicks display a menu with logout btn
        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isMenuDisplayed) {
                    // Inflate a layout with the logout option
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // load the fragment on top of the AdminDashboard
                    fragmentTransaction.add(R.id.logoutFragmentContainerView, new MenuLogoutFragmentAdmin())
                            .addToBackStack(null).commit();
                    isMenuDisplayed = true;
                } else {
                    // Remove fragment
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.logoutFragmentContainerView))
                            .commit();
                    isMenuDisplayed = false;

                }

            }
        });

        // change to admin page toolbar color and icon
        toolbar_id.setBackgroundColor(ContextCompat.getColor(this, R.color.SecondaryGreen));
        toolbar_logo.setImageResource(R.drawable.logo_purple_app);

        // get all transactions from realtime database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("transactions");

        // Recycler view starts here
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transactionArrayList = new ArrayList<Transaction>();
        adapter = new TransactionsAdapter(this, transactionArrayList);
        recyclerView.setAdapter(adapter);

        // Listener from data
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // create a transaction to add in the list
                    Transaction transactionTemp = child.getValue(Transaction.class);
                    transactionTemp.setDataSnapshot(child);

                    if(transactionTemp.getFrom().equals(email) || transactionTemp.getTo().equals(email))
                        transactionArrayList.add(transactionTemp);
                }
                // update the adapter
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.v(TAG, "Failed to read value.", error.toException());
            }
        });

    }



}



