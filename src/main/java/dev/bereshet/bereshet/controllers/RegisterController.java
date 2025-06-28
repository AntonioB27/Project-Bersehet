package dev.bereshet.bereshet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.bereshet.bereshet.entities.User;
import dev.bereshet.bereshet.repositories.UserRepository;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Handles GET requests to the "/register" endpoint.
     * Adds a new {@link User} object to the model to be used in the registration form.
     *
     * @param model the model to which attributes can be added
     * @return the name of the registration view ("register")
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles HTTP POST requests for user registration.
     * Encodes the user's password, saves the user to the repository,
     * and returns the "hello" view upon successful registration.
     *
     * @param user  the User object populated from the registration form
     * @param model the Model object for passing attributes to the view
     * @return the name of the view to render ("hello")
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "hello";
    }
}