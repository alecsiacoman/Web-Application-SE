package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateEventController {
    @GetMapping("/book-event")
    public String showBookEventPage(){
        return "book-event";
    }
}
