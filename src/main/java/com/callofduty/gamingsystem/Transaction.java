package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private ObjectId _id;   // Primary Key

    private ObjectId memberId;   // Reference → members._id
    private ObjectId gameId;     // Reference → games._id
    private Double amount;       // NOT NULL
    private Date dateTime;       // DEFAULT now()

    // Constructor
    public Transaction() {
        this.dateTime = new Date(); // default now
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

    public ObjectId getGameId() {
        return gameId;
    }

    public void setGameId(ObjectId gameId) {
        this.gameId = gameId;
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
        if (dateTime == null) {
            this.dateTime = new Date();
        } else {
            this.dateTime = dateTime;
        }
    }
}
