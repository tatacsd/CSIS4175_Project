package com.group2.katching.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.group2.katching.entity.User;

public class UserViewModel extends AndroidViewModel {

    private FirebaseUser user;

    public MutableLiveData<String> userData = new MutableLiveData<String>();

    public String getUserData() {
        return userData.getValue();
    }

    public void setUserData(String userData) {
        this.userData.setValue(userData);
    }



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
