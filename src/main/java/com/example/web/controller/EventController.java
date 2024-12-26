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
public class EventController {


    @Autowired
    private EventService eventService;

    @GetMapping("/book-event")
    public String showBookEventPage(Model model) {
        model.addAttribute("eventRequest", new EventRequest());
        return "book-event";
    }

    @PostMapping("/book-event")
    public String submitEventRequest(@ModelAttribute EventRequest eventRequest, Model model) {
        eventService.saveEventRequest(eventRequest);
        return "redirect:/book-event";
    }
}
