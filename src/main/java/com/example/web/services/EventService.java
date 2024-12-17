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
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "src/main/resources/data/request-events.json"; // Use relative path

    private List<EventRequest> eventRequests = new ArrayList<>();

    public EventService(){
        loadRequests();
    }

    private void loadRequests(){
        try{
            File file = new File(filePath);
            if(file.exists()){
                List<EventRequest> loadedRequests = objectMapper.readValue(file, new TypeReference<List<EventRequest>>() {});
                eventRequests.addAll(loadedRequests);
            } 
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveEventRequest(EventRequest eventRequest){
        eventRequests.add(eventRequest); 

        try{
            objectMapper.writeValue(new File(filePath), eventRequests); 
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Event Request Saved: " + eventRequest);
    }
}
