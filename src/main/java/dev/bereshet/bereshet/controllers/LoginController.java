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
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Handles GET requests to the "/login" endpoint.
     * Prepares and returns the login form view by adding a new User object to the model.
     *
     * @param model the model to which attributes can be added for rendering views
     * @return the name of the login view template
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * Handles POST requests for user login.
     * Authenticates the user by checking the provided username and password against stored users.
     * If authentication is successful, stores the username in the session and redirects to the home page.
     * If authentication fails, adds an error message to the model and returns the login view.
     *
     * @param user    The user object containing login credentials from the form.
     * @param model   The model to pass attributes to the view.
     * @param session The HTTP session to store user information upon successful login.
     * @return A redirect to the home page if login is successful, or the login view with an error message if not.
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        User found = userRepository.findByUsername(user.getUsername());
        if (found != null && passwordEncoder.matches(user.getPassword(), found.getPassword())) {
            session.setAttribute("loggedInUser", found.getUsername());
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }

    /**
     * Handles user logout by removing the "loggedInUser" attribute from the session.
     * 
     * @param session the current HTTP session
     * @return the name of the view to display after logout ("logout")
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        return "logout";
    }
}
