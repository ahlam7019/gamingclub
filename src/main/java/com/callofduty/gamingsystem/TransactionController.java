package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repo;

    // 🔹 POST → Create new Transaction
    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        transaction.set_id(null); // let MongoDB generate _id
        if (transaction.getDateTime() == null) {
            transaction.setDateTime(new java.util.Date()); // default now
        }
        return repo.save(transaction);
    }

    // 🔹 GET → Find all Transactions
    @GetMapping
    public List<Transaction> findAll() {
        return repo.findAll();
    }

    // 🔹 GET → Find Transaction by ID
    @GetMapping("/{id}")
    public Transaction findById(@PathVariable ObjectId id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // 🔹 PUT → Update Transaction by ID
    @PutMapping("/{id}")
    public Transaction update(@PathVariable ObjectId id, @RequestBody Transaction transaction) {
        Transaction oldTxn = repo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));

        oldTxn.setMemberId(transaction.getMemberId());
        oldTxn.setGameId(transaction.getGameId());
        oldTxn.setAmount(transaction.getAmount());
        oldTxn.setDateTime(transaction.getDateTime() != null ? transaction.getDateTime() : new java.util.Date());

        return repo.save(oldTxn);
    }
}
