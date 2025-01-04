package com.example.web.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        verifyFileIntegrity();
    }

    /**
     * Save an EventRequest to the list and write it to the JSON file.
     * Includes validation and runtime verification for file integrity.
     */
    public void saveEventRequest(EventRequest eventRequest) {
        if (validateEventRequest(eventRequest)) {
                long id = eventRequests.size() > bookedEvents.size() ? eventRequests.size() + 1 : bookedEvents.size() + 1;
                eventRequest.setId(id);

                if(!isIdUnique(eventRequest.getId())){
                    throw new IllegalArgumentException("EventRequest ID must be unique.");
                }

                eventRequests.add(eventRequest);
                reloadJson(REQUEST_JSON_PATH, eventRequests);
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

    public List<EventRequest> getBookedEvents() {
        return new ArrayList<>(bookedEvents);
    }

    public void acceptEventRequest(Long id){
        Optional<EventRequest> event = findEventById(id, eventRequests);
        event.ifPresent(eventRequest -> {
            bookedEvents.add(eventRequest);
            eventRequests.remove(eventRequest);
            reloadJson(REQUEST_JSON_PATH, eventRequests);
            reloadJson(BOOKED_JSON_PATH, bookedEvents);
        });
    }

    public void declineEventRequest(Long id){
        Optional<EventRequest> event = findEventById(id, eventRequests);
        event.ifPresent(eventRequest -> {
            eventRequests.remove(eventRequest);
            reloadJson(REQUEST_JSON_PATH, eventRequests);
        });
    }
    
    public void finishEvent(Long id){
        Optional<EventRequest> event = findEventById(id, bookedEvents);
        event.ifPresent(eventRequest -> {
            bookedEvents.remove(eventRequest);
            System.out.println("Am ajuns aici");
            reloadJson(BOOKED_JSON_PATH, bookedEvents);
        });
    }

    private Optional<EventRequest> findEventById(Long id, List<EventRequest> list) {
        return list.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

    private void reloadJson(String file, List<EventRequest> list){
        try {
            objectMapper.writeValue(new File(file), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean eventExists(Long id, List<EventRequest> list){
        boolean exists = list.stream().filter(event -> event.getId().equals(id)).count() == 1 ? true : false;
        return exists;
    }

    public List<Long> getAllEventIds() {
        List<Long> requestIds = getEventRequests().stream().map(EventRequest::getId).collect(Collectors.toList());        
        List<Long> bookedIds = getBookedEvents().stream().map(EventRequest::getId).collect(Collectors.toList());        
        requestIds.addAll(bookedIds);
        return requestIds;
    }

    private boolean isIdUnique(Long id){
        return !getAllEventIds().contains(id);
    }

    private void verifyFileIntegrity() {
        List<Long> allIds = getAllEventIds();
        List<Long> duplicateIds = allIds.stream()
                .filter(id -> allIds.stream().filter(id::equals).count() > 1)
                .collect(Collectors.toList());

        if (!duplicateIds.isEmpty()) {
            throw new IllegalStateException("Duplicate IDs detected: " + duplicateIds);
        }
    }
}
