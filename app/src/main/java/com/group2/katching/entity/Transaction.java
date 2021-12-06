package com.group2.katching.entity;

import java.util.Date;

public class Transaction {
    private String from;
    private String to;
    private Double amount;
    private String memo;
    private String date;

    public Transaction(String from, String to, Double amount, String memo, String date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.memo = memo;
        this.date = date;
    }

    public Transaction() {
        this.from = null;
        this.to = null;
        this.amount = null;
        this.memo = null;
        this.date = null;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

}
