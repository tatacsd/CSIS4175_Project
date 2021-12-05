package com.group2.katching;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.group2.katching.entity.User;

import java.util.ArrayList;

public class UserListAdapter extends ListAdapter<User, UserViewHolder> {

    ArrayList<User> userArrayList = new ArrayList<>();
    Context mContext;

    protected UserListAdapter(ArrayList<User> arrayList, Context mContext, DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
        this.userArrayList = arrayList;
        this.mContext = mContext;
    }

    // this method is called when you create the view holder on top of the activity
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // everytime we call it we add to the adapter
        return UserViewHolder.create(parent);
    }

    // this method is called when you create a single item on top of the listAdapter
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        // everytime to create a item we get the position of the word in the list
        User current = getItem(position);
        // add the word using the binder
        holder.bind(current.getEmail());
    }

    // Static methods that deals with the situation when we have equal
    public static class UserDiff extends DiffUtil.ItemCallback<User> {
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return oldItem.getEmail().equals(newItem.getEmail());
        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            // compare two obejcts and return true if they are the same
            boolean areContentsTheSame = false;
            if (oldItem.getEmail().equals(newItem.getEmail()))
                areContentsTheSame = true;
            return areContentsTheSame;
        }
    }
}