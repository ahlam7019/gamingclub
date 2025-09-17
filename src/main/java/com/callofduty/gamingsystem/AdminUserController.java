package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-users")
public class AdminUserController {

    @Autowired
    private AdminUserRepository repo;

    // 🔹 POST → Create Admin User
    @PostMapping
    public AdminUser create(@RequestBody AdminUser adminUser) {
        if (adminUser.getUsername() == null || adminUser.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (adminUser.getPassword() == null || adminUser.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        adminUser.set_id(null); // let MongoDB generate _id
        return repo.save(adminUser);
    }

    // 🔹 GET → Find all Admin Users
    @GetMapping
    public List<AdminUser> findAll() {
        return repo.findAll();
    }

    // 🔹 GET → Find Admin User by ID
    @GetMapping("/{id}")
    public AdminUser findById(@PathVariable ObjectId id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Admin User not found"));
    }

    // 🔹 PUT → Update Admin User
    @PutMapping("/{id}")
    public AdminUser update(@PathVariable ObjectId id, @RequestBody AdminUser updated) {
        AdminUser old = repo.findById(id).orElseThrow(() -> new RuntimeException("Admin User not found"));

        if (updated.getUsername() != null && !updated.getUsername().isBlank()) {
            old.setUsername(updated.getUsername());
        }
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            old.setPassword(updated.getPassword());
        }

        return repo.save(old);
    }
}
