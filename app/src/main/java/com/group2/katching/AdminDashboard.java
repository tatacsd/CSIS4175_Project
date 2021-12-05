package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.User;

public class AdminDashboard extends AppCompatActivity {

    final private String TAG = AdminDashboard.class.getSimpleName();

    // DATABASE VARIABLES
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    // List of users
    private User[] usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Get toolbar items reference
        ImageView toolbar_arrowBack = findViewById(R.id.toolbar_backArrow);
        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        TextView toolbar_appName = findViewById(R.id.toolbar_app_name);
        ImageView toolbar_menu = findViewById(R.id.toolbar_menu);

        // change to admin page toolbar color and icon
        toolbar_id.setBackgroundColor(ContextCompat.getColor(this, R.color.SecondaryGreen));
        toolbar_logo.setImageResource(R.drawable.logo_purple_app);

        // get all users from realtime database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    String emailSnapshot = user.getEmail();
                    Boolean userStatusSnapshot = user.isUserStatus();
                    if (userStatusSnapshot == false) {
                        Log.v(TAG, "ADMIN DASHBOARD User: " + user.getEmail() + " is not active");

                        // get recycler view and the adapter
                        RecyclerView recyclerView = findViewById(R.id.recyclerview);
                        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserDiff());
                        // set the adapter to the recycleview
                        recyclerView.setAdapter(adapter);
                        // set the layout manager
                        recyclerView.setLayoutManager(new LinearLayoutManager(AdminDashboard.this));

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.v(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}