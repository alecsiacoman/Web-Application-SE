package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.web.models.EventRequest;
import com.example.web.services.EventService;

import jakarta.validation.Valid;

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
    public String submitEventRequest(@Valid @ModelAttribute EventRequest eventRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("eventRequest", eventRequest);
            return "book-event";
        }

        eventService.saveEventRequest(eventRequest);
        return "redirect:/book-event";
    }
}
