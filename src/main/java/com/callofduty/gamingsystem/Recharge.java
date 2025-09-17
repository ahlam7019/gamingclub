package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "recharges")
public class Recharge {

    @Id
    private ObjectId _id;

    private ObjectId memberId; // reference → members._id
    private Double amount;     // NOT NULL
    private Date dateTime;     // DEFAULT now()

    // Constructor
    public Recharge() {
        this.dateTime = new Date(); // auto set to current time
    }

    // Getters and Setters
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        this.amount = amount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
