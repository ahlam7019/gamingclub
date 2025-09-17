package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUser, ObjectId> {
    Optional<AdminUser> findByUsername(String username); // for login/checking uniqueness
}
