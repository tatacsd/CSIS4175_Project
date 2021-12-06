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
import com.group2.katching.entity.Transaction;
import com.group2.katching.entity.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {

    final private String TAG = AdminDashboard.class.getSimpleName();
    RecyclerView recyclerView;
    AdminAdapter adapter;

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

        recyclerView = (RecyclerView)findViewById(R.id.adminRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Transaction> options = new FirebaseRecyclerOptions.Builder<Transaction>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("transactions"), Transaction.class)
                .build();

        adapter = new AdminAdapter(options);
        recyclerView.setAdapter(adapter);

        // Recycler view and adpter
//        ArrayList<User> userArrayList = new ArrayList<User>();
//        UserListAdapter adapter = new UserListAdapter(userArrayList, this, null);
//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        // get all users from realtime database
//        mFirebaseInstance = FirebaseDatabase.getInstance();
//        mFirebaseDatabase = mFirebaseInstance.getReference("users");
//        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                int count = 0;
//                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                    User user = child.getValue(User.class);
//                    String emailSnapshot = user.getEmail();
//                    Boolean userStatusSnapshot = user.isUserStatus();
//                    String userIdSnapshot = user.getDataBaseId();
//                    if (userStatusSnapshot == false) {
//                        // TODO: HERE WILL BE AVAILABLE ALL USERS THAT IS CLIENT
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.v(TAG, "Failed to read value.", error.toException());
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

class AdminAdapter extends FirebaseRecyclerAdapter<Transaction, AdminAdapter.AdminViewHolder> {

    public AdminAdapter(@NonNull FirebaseRecyclerOptions<Transaction> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminViewHolder holder, int position, @NonNull Transaction model) {

        holder.dateTxt.setText(model.getDate());
        holder.descriptionTxt.setText("From: " + model.getFrom() + "/n" +
                                       "To: " + model.getTo());
        holder.amountTxt.setText("$" + model.getAmount());
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_recycleview_item, parent, false);
        return new AdminViewHolder(view);
    }

    class AdminViewHolder extends RecyclerView.ViewHolder
    {
        TextView dateTxt, descriptionTxt, amountTxt;
        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTxt = (TextView) itemView.findViewById(R.id.recycle_item_date);
            descriptionTxt = (TextView) itemView.findViewById(R.id.recycle_item_transaction);
            amountTxt = (TextView) itemView.findViewById(R.id.recycle_item_amount);
        }
    }
}