package com.example.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage(HttpSession session){
        session.setAttribute("role", "admin");
        //return "redirect:/admin-events";
        return "login";
    }

    @GetMapping("/admin-events")
    public String adminEvents() {
        return "admin-events"; // Refers to the Thymeleaf template admin-events.html
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidates the session
        return "redirect:/"; // Redirects to the login page with a logout query parameter
    }
}
