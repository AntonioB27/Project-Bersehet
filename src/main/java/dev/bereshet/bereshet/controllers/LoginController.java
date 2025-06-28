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
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        User found = userRepository.findAll().stream()
            .filter(u -> u.getUsername().equals(user.getUsername()) && passwordEncoder.matches(user.getPassword(), u.getPassword()))
            .findFirst()
            .orElse(null);

        if (found != null) {
            // You can set session attributes here if needed
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }
}