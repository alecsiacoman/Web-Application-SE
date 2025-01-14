package com.example.web.models;


import java.util.List;

public class LoginResponse {
    private Long user_id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String password;
    private List<String> roles;

    public LoginResponse(Long user_id, String token, String name, String password, List<String> roles) {
        this.user_id = user_id;
        this.token = token;
        this.username = name;
        this.password = password;
        this.roles = roles;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
