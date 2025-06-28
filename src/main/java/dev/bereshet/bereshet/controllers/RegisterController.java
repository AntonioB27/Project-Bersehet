package dev.bereshet.bereshet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.bereshet.bereshet.entities.User;
import dev.bereshet.bereshet.helpers.PasswordHelper;
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
     * Handles user registration requests.
     * 
     * Validates the user's password and, if valid, encodes and saves the user to the repository.
     * If the password is invalid, returns the registration page with an error message.
     *
     * @param user  the user object populated from the registration form
     * @param model the model to pass attributes to the view
     * @return the name of the view to render ("register" if validation fails, "login" on success)
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if(!PasswordHelper.isPassvordValid(user.getPassword())){
            model.addAttribute("error", "Password must be at least 8 characters long.");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }
}