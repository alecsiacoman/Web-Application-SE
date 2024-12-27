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

    public EventService() {
        this.objectMapper = new ObjectMapper();
        this.eventRequests = loadEventRequestsFromFile();
    }

    /**
     * Save an EventRequest to the list and write it to the JSON file.
     * Includes validation and runtime verification for file integrity.
     */
    public void saveEventRequest(EventRequest eventRequest) {
        if (validateEventRequest(eventRequest)) {
            try {
                // Verify and load existing data from file
                File file = new File(REQUEST_JSON_PATH);
                if (file.exists() && file.length() > 0) {
                    eventRequests = objectMapper.readValue(file, new TypeReference<List<EventRequest>>() {});
                }

                // Add the new request to the list
                eventRequests.add(eventRequest);

                // Save the updated list to the JSON file
                objectMapper.writeValue(file, eventRequests);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save event request: " + e.getMessage(), e);
            }
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
    private List<EventRequest> loadEventRequestsFromFile() {
        File file = new File(REQUEST_JSON_PATH);
        if (file.exists() && file.length() > 0) {
            try {
                return objectMapper.readValue(file, new TypeReference<List<EventRequest>>() {});
            } catch (IOException e) {
                throw new RuntimeException("Failed to load event requests: " + e.getMessage(), e);
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
}
