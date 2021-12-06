package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.adminrecyclerview.UserListAdapter;
import com.group2.katching.entity.Transaction;
import com.group2.katching.entity.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {

    final private String TAG = AdminDashboard.class.getSimpleName();
    RecyclerView recyclerView;
    UserListAdapter adapter;
    ArrayList<User> userArrayList;


    // DATABASE VARIABLES
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

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


//         get all users from realtime database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // Recycler view starts here
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userArrayList = new ArrayList<User>();
        adapter = new UserListAdapter(this, userArrayList);
        recyclerView.setAdapter(adapter);



        // Listener from data
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // create a user to add in the list
                    User userTemp = child.getValue(User.class);
                    userTemp.setDataSnapshot(child);

                    // Save all user into the ArrayList
                    userArrayList.add(userTemp);
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
