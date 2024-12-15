package com.example.web.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.web.models.EventRequest;

@Service
public class EventService {
    private List<LocalDate> bookedDates = new ArrayList<>();
    private List<EventRequest> eventRequests = new ArrayList<>();

    public List<LocalDate> getAvailableDates(){
        List<LocalDate> availableDates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            LocalDate date = today.plusDays(i);
            if (!bookedDates.contains(date)) {
                availableDates.add(date);
            }
        }
        return availableDates;
    }

    public boolean saveEventRequest(EventRequest request) {
        if (!bookedDates.contains(request.getEventDate())) {
            bookedDates.add(request.getEventDate());
            eventRequests.add(request);
            return true;
        }
        return false;
    }
}
