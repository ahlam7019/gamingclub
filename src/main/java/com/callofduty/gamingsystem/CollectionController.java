package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CollectionRepository repo;

    // 🔹 POST → Create Collection
    @PostMapping
    public Collection create(@RequestBody Collection collection) {
        if (collection.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (collection.getAmount() == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        collection.set_id(null); // Let MongoDB generate _id
        return repo.save(collection);
    }

    // 🔹 GET → Find all Collections
    @GetMapping
    public List<Collection> findAll() {
        return repo.findAll();
    }

    // 🔹 GET → Find Collection by ID
    @GetMapping("/{id}")
    public Collection findById(@PathVariable ObjectId id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Collection not found"));
    }

    // 🔹 PUT → Update Collection by ID
    @PutMapping("/{id}")
    public Collection update(@PathVariable ObjectId id, @RequestBody Collection updated) {
        Collection old = repo.findById(id).orElseThrow(() -> new RuntimeException("Collection not found"));

        if (updated.getDate() != null) old.setDate(updated.getDate());
        if (updated.getAmount() != null) old.setAmount(updated.getAmount());

        return repo.save(old);
    }
}
