package com.example.web.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.web.models.EventRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class EventService {
    private final ObjectMapper objectMapper;
    private static final String REQUEST_JSON_PATH = "web/src/main/resources/data/request-events.json";
    private static final String BOOKED_JSON_PATH = "web/src/main/resources/data/booked-events.json";
    private List<EventRequest> eventRequests;
    private List<EventRequest> bookedEvents;

    public EventService() {
        this.objectMapper = new ObjectMapper();
        this.eventRequests = loadEventRequestsFromFile(REQUEST_JSON_PATH);
        this.bookedEvents = loadEventRequestsFromFile(BOOKED_JSON_PATH);
    }

    /**
     * Save an EventRequest to the list and write it to the JSON file.
     * Includes validation and runtime verification for file integrity.
     */
    public void saveEventRequest(EventRequest eventRequest) {
        if (validateEventRequest(eventRequest)) {
                long nextId = eventRequests.stream().mapToLong(EventRequest::getId).max().orElse(0) + 1;
                eventRequest.setId(nextId);
                saveToJson(eventRequest, REQUEST_JSON_PATH, eventRequests);
        } else {
            throw new IllegalArgumentException("Invalid EventRequest: Validation failed.");
        }
    }

    /**
     * Validate the given EventRequest for null values and basic constraints.
     */
    private boolean validateEventRequest(EventRequest eventRequest) {
        if (eventRequest.getName() == null || eventRequest.getName().isEmpty()) {
            System.err.println("Validation failed: Name is required.");
            return false;
        }
        if (eventRequest.getEmail() == null || !eventRequest.getEmail().contains("@")) {
            System.err.println("Validation failed: Valid email is required.");
            return false;
        }
        if (eventRequest.getPhone() == null || eventRequest.getPhone().length() < 7) {
            System.err.println("Validation failed: Valid phone number is required.");
            return false;
        }
        if (eventRequest.getDate() == null) {
            System.err.println("Validation failed: Date is required.");
            return false;
        }
        if (eventRequest.getTheme() == null || eventRequest.getTheme().isEmpty()) {
            System.err.println("Validation failed: Theme is required.");
            return false;
        }
        if (eventRequest.getAddress() == null || eventRequest.getAddress().isEmpty()) {
            System.err.println("Validation failed: Address is required.");
            return false;
        }
        return true;
    }

    /**
     * Load event requests from the JSON file.
     */
    private List<EventRequest> loadEventRequestsFromFile(String path) {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            try {
                return objectMapper.readValue(file, new TypeReference<List<EventRequest>>() {});
            } catch (IOException e) {
                throw new RuntimeException("Failed to load events: " + e.getMessage(), e);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Retrieve a copy of the current list of event requests.
     */
    public List<EventRequest> getEventRequests() {
        return new ArrayList<>(eventRequests);
    }

    public void acceptEventRequest(Long id){
        Optional<EventRequest> event = findEventById(id);
        event.ifPresent(eventRequest -> {
            saveToJson(eventRequest, BOOKED_JSON_PATH, bookedEvents);
            eventRequests.remove(eventRequest);
            reloadRequests();
        });
    }

    public void declineEventRequest(Long id){
        Optional<EventRequest> event = findEventById(id);
        event.ifPresent(eventRequest -> {
            eventRequests.remove(eventRequest);
            reloadRequests();
        });
    }

    private Optional<EventRequest> findEventById(Long id) {
        return eventRequests.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

    private void saveToJson(EventRequest eventRequest, String path, List<EventRequest> list){
        try {
            eventRequests.add(eventRequest);
            objectMapper.writeValue(new File(path), list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save event request: " + e.getMessage(), e);
        }
    }

    private void reloadRequests(){
        try {
            objectMapper.writeValue(new File(REQUEST_JSON_PATH), eventRequests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
