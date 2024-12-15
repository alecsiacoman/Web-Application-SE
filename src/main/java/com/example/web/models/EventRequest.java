package com.example.web.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EventRequest {
    private String name;
    private String phone;
    private String theme;
    private String address;
    private String location;

    @DateTimeFormat(pattern = "yy-mm-dd")
    private LocalDate eventDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    
}
