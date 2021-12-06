package com.group2.katching.adminrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.group2.katching.R;
import com.group2.katching.entity.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    // Member variables
    Context mContext;
    ArrayList<User> userArrayList;

    // Constructor


    public UserListAdapter(Context mContext, ArrayList<User> userArrayList) {
        this.mContext = mContext;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.admin_recyclerview_item, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.userEmailView.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        // return the list length
        return userArrayList.size();
    }

    // Class
    public static class UserViewHolder extends RecyclerView.ViewHolder {

        // Member variables
        TextView userEmailView;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the ui reference
            userEmailView = itemView.findViewById(R.id.client_username);




        }
    }


}