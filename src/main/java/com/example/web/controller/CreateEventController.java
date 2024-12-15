package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.web.models.EventRequest;
import com.example.web.services.EventService;

@Controller
public class CreateEventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping("/book-event")
    public String showBookEventPage(Model model) {
        model.addAttribute("eventRequest", new EventRequest());
        model.addAttribute("availableDates", eventService.getAvailableDates());
        return "book-event";
    }

    @PostMapping("/book-event")
    public String submitEventRequest(@ModelAttribute EventRequest eventRequest, Model model) {
        boolean success = eventService.saveEventRequest(eventRequest);

        if (success) {
            model.addAttribute("message", "Your event request has been submitted successfully!");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "There was an issue submitting your request. Please try again.");
            model.addAttribute("messageType", "error");
        }

        model.addAttribute("availableDates", eventService.getAvailableDates());
        model.addAttribute("eventRequest", eventRequest);
        
        return "book-event";
    }
}
