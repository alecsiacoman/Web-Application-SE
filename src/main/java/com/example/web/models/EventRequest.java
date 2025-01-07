package com.example.web.models;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Component
public class EventRequest {
    private Long id;

    @NotBlank(message = "Name is requierd!")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Email(message = "Please provide a valid email!")
    private String email;

    @NotBlank(message = "Phone number is required!")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Date is required!")
    private String date;

    @NotBlank(message = "Theme is required!")
    private String theme;

    @NotBlank(message = "Description is required!") // Add validation for description
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters") // Example size constraint
    private String description; // New field for event description

    @NotBlank(message = "Address is required!")
    private String address;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    public String getDescription() { return description; } // Getter for description
    public void setDescription(String description) { this.description = description; } // Setter for description
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "EventRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' + // Include description in toString
                ", address='" + address + '\'' +
                '}';
    }
}
