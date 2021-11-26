package com.group2.katching.entity;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private boolean userStatus;
    private double balance;

    public User(String email, boolean userStatus, double balance) {
        this.email = email;
        this.userStatus = userStatus;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
