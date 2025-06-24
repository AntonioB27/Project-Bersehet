package dev.bereshet.bereshet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bereshet.bereshet.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}