package dev.bereshet.bereshet.entities;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private Integer price;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    private String seller;
    private String status;
    private LocalDateTime createdAt;
}
