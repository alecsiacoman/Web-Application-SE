package com.example.web.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.web.models.EventRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class EventService {
    private final ObjectMapper objectMapper;
    private static final String REQUEST_JSON_PATH = "web/src/main/resources/data/request-events.json";

    private List<EventRequest> eventRequests;

    public EventService(){
        this.eventRequests = new ArrayList<>();
        this.objectMapper = new ObjectMapper();  
    }

    public void saveEventRequest(EventRequest eventRequest) {
        try {
            File file = new File(REQUEST_JSON_PATH);
            List<EventRequest> eventRequests = new ArrayList<>();

            if (file.exists() && file.length() > 0) {
                eventRequests = objectMapper.readValue(file, new TypeReference<List<EventRequest>>() {});
            }

            eventRequests.add(eventRequest);

            objectMapper.writeValue(file, eventRequests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EventRequest> getEventRequests() {
        return new ArrayList<>(eventRequests);
    }
}
