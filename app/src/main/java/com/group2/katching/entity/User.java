package com.group2.katching.entity;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

public class User implements Serializable {
    private String dataBaseId;
    private String email;
    private String firstName;
    private String lastName;
    private boolean userStatus;
    private double balance;
    private DataSnapshot dataSnapshot;

    public User (){}

    public User(String dataBaseId, String email, boolean userStatus, double balance) {
        this.dataBaseId = dataBaseId;
        this.email = email;
        this.userStatus = userStatus;
        this.balance = balance;
    }

    public String getDataBaseId() {return dataBaseId;}
    public String getEmail() { return email;  }
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName; }
    public boolean isUserStatus() { return userStatus; }
    public double getBalance() { return balance; }
    public DataSnapshot getDataSnapshot() { return dataSnapshot; }

    public void setDataBaseId(String dataBaseId) {this.dataBaseId = dataBaseId;}
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) {this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUserStatus(boolean userStatus) { this.userStatus = userStatus; }
    public void setBalance(double balance) {this.balance = balance;}
    public void setDataSnapshot(DataSnapshot dataSnapshot) {this.dataSnapshot = dataSnapshot;}
}
