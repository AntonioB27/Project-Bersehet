package dev.bereshet.bereshet.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.bereshet.bereshet.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {
}