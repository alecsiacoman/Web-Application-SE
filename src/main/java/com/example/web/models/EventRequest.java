package com.example.web.models;

public class EventRequest {
    private String name;
    private String email;
    private String phone;
    private String theme;
    private String address;
    private String date;

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

    public String getEventDate() {
        return date;
    }

    public void setEventDate(String eventDate) {
        this.date = eventDate;
    }

    @Override
    public String toString() {
        return "EventRequest{" +
               "name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", phone='" + phone + '\'' +
               ", date='" + date + '\'' +
               ", theme='" + theme + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
    
}
