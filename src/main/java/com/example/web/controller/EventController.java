package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.web.models.EventRequest;
import com.example.web.services.EventService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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

//    @GetMapping("/admin-events")
//    public String showAdminEventsPage(HttpSession session, Model model){
//        String role = (String) session.getAttribute("role");
//
//        if("admin".equals(role)){
//            model.addAttribute("eventRequest", eventService.getEventRequests());
//            model.addAttribute("bookedEvent", eventService.getBookedEvents());
//            return "admin-events";
//        } else {
//            return "redirect:/access-denied";
//        }
//
//    }

    @GetMapping("/admin-events")
    public String showAdminEventsPage(HttpSession session) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(session.getAttribute("role"));
        if ("admin".equals(session.getAttribute("role"))) {
            return "admin-events";
        }
        return "redirect:/login";
    }

    @PostMapping("/admin-events/accept/{id}")
    @ResponseBody
    public String acceptEventRequest(@PathVariable @NotNull Long id) {
        if(!eventService.eventExists(id, eventService.getEventRequests())){
            return "{\"status\": \"error\", \"message\": \"Event not found\"}"; 
        }
        eventService.acceptEventRequest(id);
        return "{\"status\": \"success\"}";
    }

    @PostMapping("/admin-events/decline/{id}")
    @ResponseBody
    public String declineEventRequest(@PathVariable @NotNull Long id) {
        if(!eventService.eventExists(id, eventService.getEventRequests())){
            return "{\"status\": \"error\", \"message\": \"Event not found\"}"; 
        }

        eventService.declineEventRequest(id);
        return "{\"status\": \"success\"}";
    }

    @PostMapping("/admin-events/finish/{id}")
    @ResponseBody
    public String finishEventRequest(@PathVariable @NotNull Long id){
        if(!eventService.eventExists(id, eventService.getBookedEvents())){
            return "{\"status\": \"error\", \"message\": \"Event not found\"}"; 
        }

        eventService.finishEvent(id);
        return "{\"status\": \"success\"}";
    }

    
    @GetMapping("/check-date-availability")
    @ResponseBody
    public boolean checkDateAvailability(@RequestParam("date") String date) {
        return eventService.isDateAvailable(date);
    }

}
