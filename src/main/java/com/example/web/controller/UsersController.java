package com.example.web.controller;

import com.example.web.models.Users;
import com.example.web.repositories.UsersRepository;
import com.example.web.services.EventService;
import com.example.web.services.JWTService;
import com.example.web.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UsersService usersService;

    @Autowired UsersRepository usersRepository;


    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        usersService.register(user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {

        return usersService.verify(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> users = usersRepository.getUsers();
//        System.out.println("AICI");
//        System.out.println(users);
//
//        int id = 5;
//        users.add(new Users((long) id,"admin","admin"));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
