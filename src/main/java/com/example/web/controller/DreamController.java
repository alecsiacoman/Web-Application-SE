package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DreamController {
    @GetMapping("/")
    public String showHomePage(HttpSession session){
        if(session.getAttribute("role") == null){
            session.setAttribute("role", "client");
        }
        return "home";
    }
}
