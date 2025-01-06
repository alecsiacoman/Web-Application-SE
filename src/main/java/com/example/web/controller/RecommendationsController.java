package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RecommendationsController {

    @GetMapping("/recommendations")
    public String getRecommendations() {
        return "recommendations"; // Return the name of the HTML template
    }
}
