package dev.bereshet.bereshet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.bereshet.bereshet.entities.Post;
import dev.bereshet.bereshet.repositories.PostRepository;

@Controller
public class HelloController {

    @Autowired
    private PostRepository postRepository;

    /**
     * Handles HTTP GET requests to the root URL ("/").
     * Retrieves all users from the user repository and adds them to the model.
     * Returns the "hello" view for rendering.
     *
     * @param model the model to which user data is added
     * @return the name of the view to render ("hello")
     */
    @GetMapping("/")
    public String hello(Model model) {
        List<Post> posts = postRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdAt"))).getContent();
        model.addAttribute("posts", posts);
        return "hello";
    }
}