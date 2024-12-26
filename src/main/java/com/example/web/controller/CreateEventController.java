package com.example.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.web.models.EventRequest;
import com.example.web.services.EventService;

@Controller
public class CreateEventController {
    @Value("${D:\\Facultate\\AN3\\An3Sem1\\SE\\web\\web\\src\\main\\resources\\data\\request-events.json}")
    private String requestJsonPath;

    @Value("${D:\\Facultate\\AN3\\An3Sem1\\SE\\web\\web\\src\\main\\resources\\data\\booked-events.json}")
    private String bookedJsonPath;

    @Autowired
    private EventService eventService;

    @GetMapping("/book-event")
    public String showBookEventPage(Model model) {
        model.addAttribute("eventRequest", new EventRequest());
        return "book-event";
    }

    @PostMapping("/book-event")
    public String submitEventRequest(@ModelAttribute EventRequest eventRequest, Model model) {
        System.out.println(eventRequest);
        model.addAttribute("message", "Event booked successfully");
        eventService.saveEventRequest(eventRequest);
        return "book-event";
    }
}
