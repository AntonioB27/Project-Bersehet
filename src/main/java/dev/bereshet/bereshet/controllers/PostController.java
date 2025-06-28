package dev.bereshet.bereshet.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.bereshet.bereshet.entities.Post;
import dev.bereshet.bereshet.repositories.PostRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/create")
    public String showCreatePostForm(Model model, HttpSession session) {
        if(session.getAttribute("loggedInUser") == null) {
            model.addAttribute("message", "You must be logged in to create a post.");
            return "redirect:/login";
        }
        model.addAttribute("post", new Post());
        return "create_post";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, HttpSession session) {
        String username = (String) session.getAttribute("loggedInUser");
        post.setSeller(username);
        post.setCreatedAt(LocalDateTime.now());
        post.setStatus("available");
        postRepository.save(post);
        return "redirect:/";
    }
}
