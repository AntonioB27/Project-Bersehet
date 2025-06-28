package dev.bereshet.bereshet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.bereshet.bereshet.repositories.UserRepository;

@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

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
        model.addAttribute("users", userRepository.findAll());
        return "hello";
    }
}