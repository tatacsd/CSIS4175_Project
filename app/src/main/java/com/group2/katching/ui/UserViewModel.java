package com.group2.katching.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseUser;

public class UserViewModel extends AndroidViewModel {

    private FirebaseUser user;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }
}
