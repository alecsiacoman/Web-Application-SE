package com.example.web.models;

import org.springframework.stereotype.Component;

@Component
public class TeamMember {
    private String name;
    private String quote;
    private String photo;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
