package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String showAdminPage(HttpSession session){
        return "admin";
    }

    @GetMapping("/login-as-admin")
    public String loginAsAdmin(HttpSession session){
        session.setAttribute("role", "admin");
        return "redirect:/login";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session){
        String role = (String) session.getAttribute("role");
        if("admin".equals(role))
            session.setAttribute("role", "client");
        return "redirect:/";
    }
}
