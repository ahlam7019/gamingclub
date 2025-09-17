package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "collections")
public class Collection {

    @Id
    private ObjectId _id;   // Primary Key

    private Date date;      // NOT NULL
    private Double amount;  // NOT NULL

    // Constructor
    public Collection() {}

    public Collection(Date date, Double amount) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        this.date = date;
        this.amount = amount;
    }

    // Getters and Setters
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        this.amount = amount;
    }
}
