package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group2.katching.entity.Transaction;

public class TransactionsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TransactionsAdapter adapter;

    // DATABASE VARIABLES
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        recyclerView = (RecyclerView)findViewById(R.id.transactionsRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Transaction> options = new FirebaseRecyclerOptions.Builder<Transaction>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("transactions"), Transaction.class)
                .build();


        adapter = new TransactionsAdapter(options);
        recyclerView.setAdapter(adapter);

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

class TransactionsAdapter extends FirebaseRecyclerAdapter<Transaction, TransactionsAdapter.TransactionsViewHolder> {

    public TransactionsAdapter(@NonNull FirebaseRecyclerOptions<Transaction> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position, @NonNull Transaction model) {

        holder.dateTxt.setText(model.getDate());
        holder.descriptionTxt.setText("From: " + model.getFrom() + "\n" +
                "To: " + model.getTo());
        holder.amountTxt.setText("$" + model.getAmount());
        holder.currencyTxt.setText("CAD");
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_recycleview_item, parent, false);
        return new TransactionsViewHolder(view);
    }

    class TransactionsViewHolder extends RecyclerView.ViewHolder
    {
        TextView dateTxt, descriptionTxt, amountTxt, currencyTxt;
        public TransactionsViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTxt = (TextView) itemView.findViewById(R.id.recycle_item_date);
            descriptionTxt = (TextView) itemView.findViewById(R.id.recycle_item_transaction);
            amountTxt = (TextView) itemView.findViewById(R.id.recycle_item_amount);
            currencyTxt = (TextView) itemView.findViewById(R.id.recycle_item_currency);
        }
    }
}



