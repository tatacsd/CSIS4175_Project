package com.group2.katching.transactionsrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group2.katching.R;

import com.group2.katching.entity.Transaction;

import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    // Member variables
    Context mContext;
    ArrayList<Transaction> transactionArrayList;

    // Constructor


    public TransactionsAdapter(Context mContext, ArrayList<Transaction> transactionArrayList) {
        this.mContext = mContext;
        this.transactionArrayList = transactionArrayList;
    }

    @NonNull
    @Override
    public TransactionsAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.admin_recyclerview_item, parent, false);
        return new TransactionsAdapter.TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.TransactionViewHolder holder, int position) {
        Transaction transaction = transactionArrayList.get(position);
        holder.dateTxt.setText("Date: " + transaction.getDate());
        holder.descriptionTxt.setText("From: " + transaction.getFrom() + "\n"
                                        + "To: " + transaction.getTo());
        holder.amountTxt.setText("$" + transaction.getAmount());


    }

    @Override
    public int getItemCount() {
        // return the list length
        return transactionArrayList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        // Member variables
        TextView descriptionTxt, amountTxt, dateTxt;


        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the ui reference
            descriptionTxt = itemView.findViewById(R.id.recycle_item_transaction);
            amountTxt = itemView.findViewById(R.id.recycle_item_amount);
            dateTxt = itemView.findViewById(R.id.recycle_item_date);

        }
    }
}
