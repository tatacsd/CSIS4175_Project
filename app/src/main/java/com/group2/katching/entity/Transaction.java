package com.group2.katching.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {
    private String from;
    private String to;
    private Double amount;
    private String memo;
    private LocalDateTime date;

    public Transaction(String from, String to, Double amount, String memo, LocalDateTime date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.memo = memo;
        this.date = date;
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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

}
