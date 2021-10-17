package com.group2.katching.entity;

public class Transaction {
    private int from;
    private int to;
    private String userStatus;
    private float amount;
    private String memo;

    public Transaction(int from, int to, String userStatus, float amount, String memo) {
        this.from = from;
        this.to = to;
        this.userStatus = userStatus;
        this.amount = amount;
        this.memo = memo;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
