package com.example.web.controller;

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

    // private final ObjectMapper objectMapper = new ObjectMapper();

    // private List<EventRequest> requests = new ArrayList<>();
    // private List<EventRequest> booked = new ArrayList<>();
    
    @Autowired
    private EventService eventService;

    @GetMapping("/book-event")
    public String showBookEventPage(Model model) {
        model.addAttribute("eventRequest", new EventRequest());
        //model.addAttribute("availableDates", eventService.getAvailableDates());
        return "book-event";
    }

    @PostMapping("/book-event")
    public String submitEventRequest(@ModelAttribute EventRequest eventRequest, Model model) {
        eventService.saveEventRequest(eventRequest);
        model.addAttribute("message", "Your event request has been submitted successfully.");
        model.addAttribute("messageType", "success");
        return "book-event";
    }
}
