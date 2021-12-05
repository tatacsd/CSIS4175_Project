package com.group2.katching;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    // Instantiate a what is inside the recycle_item
    private final View hrView;
    private final TextView userEmailView;
    private final Button deleteBtnView;

    // constructor
    public UserViewHolder(View itemView) {
        super(itemView);
        // get the textView for the recycler view item
        hrView = itemView.findViewById(R.id.hrView);
        userEmailView = itemView.findViewById(R.id.client_username);
        deleteBtnView = itemView.findViewById(R.id.adminDashboardBtn);
    }

    // we gonna need two methods:
    // bind to set the text of the texview
    public void bind(String text) {
        userEmailView.setText(text);
        // TODO: bind delete user btn

    }

    // static method ti get the access of the parent holding the list to create the
    // listitem
    public static UserViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_recyclerview_item, parent, false);
        // use the constructor to create the specified view
        return new UserViewHolder(view);
    }
}