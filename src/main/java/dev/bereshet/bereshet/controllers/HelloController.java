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

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "hello";
    }
}