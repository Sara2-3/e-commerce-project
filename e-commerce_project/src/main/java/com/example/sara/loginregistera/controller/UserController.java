package com.example.sara.loginregistera.controller;

import com.example.sara.loginregistera.model.LoginUser;
import com.example.sara.loginregistera.model.User;
import com.example.sara.loginregistera.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userServ;

    /**
     * Render the login/registration page if the user is not logged in.
     * Redirects logged-in users to the dashboard.
     */
    @GetMapping("/")
    public String loginRegPage(Model viewModel, HttpSession session) {
        // Prevent logged-in users from visiting the registration page
        if (session.getAttribute("userID") != null) {
            return "redirect:/dashboard";
        }

        // Add new user objects for registration and login
        viewModel.addAttribute("newUser", new User());
        viewModel.addAttribute("newLogin", new LoginUser());

        return "login"; // View name (login page)
    }

    /**
     * Render the registration page for GET requests to /register.
     */
    @GetMapping("/register")
    public String showRegisterPage(Model viewModel) {
        // Add a new User object for form binding
        viewModel.addAttribute("newUser", new User());
        return "register"; // View name for the registration page (register.jsp)
    }

    /**
     * Handle user registration via POST requests to /register.
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session, Model viewModel) {
        // Validate registration
        User registeredUser = userServ.validateRegistration(newUser, result);

        if (result.hasErrors()) {
            // Send the user back to the registration page if there are errors
            return "register";
        }

        // Save the registered user's ID and role in session
        session.setAttribute("userID", registeredUser.getId());
        session.setAttribute("userRole", registeredUser.getRole());

        return "redirect:/dashboard";
    }

    /**
     * Handle login requests.
     */
    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, HttpSession session, Model viewModel) {
        User loggedInUser = userServ.validateLogin(newLogin, result);

        if (result.hasErrors()) {
            // Forward new user registration object for index page
            viewModel.addAttribute("newUser", new User());
            return "login";
        }

        // Save the logged-in user's ID and role in session
        session.setAttribute("userID", loggedInUser.getId());
        session.setAttribute("userRole", loggedInUser.getRole());

        return "redirect:/dashboard";
    }

    /**
     * Render the dashboard page for logged-in users.
     */
    @GetMapping("/dashboard")
    public String dashboardPage(HttpSession session, Model viewModel) {
        // Check if user is logged in
        Object userIDObj = session.getAttribute("userID");
        if (userIDObj == null) {
            return "redirect:/";
        }

        Long userID = (Long) userIDObj;
        User loggedInUser = userServ.getUserById(userID);
        if (loggedInUser == null) {
            return "redirect:/";
        }

        // Pass the logged-in user and role to the dashboard
        viewModel.addAttribute("loggedInUser", loggedInUser);
        viewModel.addAttribute("userRole", loggedInUser.getRole());

        return "dashboard"; // View name for the dashboard page
    }

    /**
     * Handle user logout.
     */
    @PostMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate(); // Clear the session
        return "redirect:/";
    }
}