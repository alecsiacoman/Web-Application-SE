package com.example.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage(HttpSession session){
        session.setAttribute("role", "admin");
        return "redirect:/admin-events";
    }
}
