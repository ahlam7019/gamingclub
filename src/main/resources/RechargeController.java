package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    @Autowired
    private RechargeRepository repo;

    // Create a recharge
    @PostMapping
    public Recharge create(@RequestBody Recharge recharge) {
        recharge.set_id(null); // ensure MongoDB generates new ID
        if (recharge.getDateTime() == null) {
            recharge.setDateTime(new java.util.Date());
        }
        return repo.save(recharge);
    }

    // Get all recharges
    @GetMapping
    public List<Recharge> findAll() {
        return repo.findAll();
    }

    // Get recharge by ID
    @GetMapping("/{id}")
    public Optional<Recharge> findById(@PathVariable ObjectId id) {
        return repo.findById(id);
    }

    // Update recharge
    @PutMapping("/{id}")
    public Recharge update(@PathVariable ObjectId id, @RequestBody Recharge recharge) {
        Recharge existing = repo.findById(id).orElseThrow();
        existing.setMemberId(recharge.getMemberId());
        existing.setAmount(recharge.getAmount());
        existing.setDateTime(recharge.getDateTime() != null ? recharge.getDateTime() : new java.util.Date());
        return repo.save(existing);
    }

    // Delete recharge
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ObjectId id) {
        repo.deleteById(id);
    }
}
