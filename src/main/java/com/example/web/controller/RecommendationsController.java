package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.web.models.Decor;
import com.example.web.models.Formality;
import com.example.web.models.Mood;
import com.example.web.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class RecommendationsController {

    @GetMapping("/recommendations")
    public String getRecommendations() {
        return "recommendations"; // Return the name of the HTML template
    }
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/recommend")
    public String recommend(
            @RequestParam Formality formality,
            @RequestParam Decor decor,
            @RequestParam Mood mood,
            Model model) {
        String recommendation = recommendationService.generateRecommendation(formality, decor, mood);
        model.addAttribute("recommendation", recommendation);
        return "recommendation";
    }
}

