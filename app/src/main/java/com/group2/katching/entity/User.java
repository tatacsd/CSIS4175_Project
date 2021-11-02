package com.group2.katching.entity;

public class User {
    private String firstName;
    private String lastName;
    private String userStatus;
    private float balance;

    public User(String firstname, String lastName, String userStatus, float balance) {
        this.firstName = firstname;
        this.lastName = lastName;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
