package dev.bereshet.bereshet.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.bereshet.bereshet.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}