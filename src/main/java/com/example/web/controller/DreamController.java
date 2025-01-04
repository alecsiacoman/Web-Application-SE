package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DreamController {
    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model){
        if(session.getAttribute("role") == null){
            session.setAttribute("role", "client");
        }
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);
        return "home";
    }
}
