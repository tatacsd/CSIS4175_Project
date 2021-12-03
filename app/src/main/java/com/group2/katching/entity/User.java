package com.group2.katching.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String dataBaseId;
    private String email;
    private String firstName;
    private String lastName;
    private boolean userStatus;
    private double balance;

    public User(String dataBaseId, String email, boolean userStatus, double balance) {
        this.dataBaseId = dataBaseId;
        this.email = email;
        this.userStatus = userStatus;
        this.balance = balance;
    }

    public String getDataBaseId() {return dataBaseId;}

    public String getEmail() { return email;  }

    public void setEmail(String email) { this.email = email; }

    public boolean isUserStatus() { return userStatus; }

    public void setUserStatus(boolean userStatus) { this.userStatus = userStatus; }

    public double getBalance() { return balance; }

    public void setBalance(double balance) {this.balance = balance;}
}
