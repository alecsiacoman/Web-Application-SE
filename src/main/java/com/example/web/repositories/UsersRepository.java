package com.example.web.repositories;

import com.example.web.models.Users;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsersRepository{

    private final ObjectMapper objectMapper;
    private static final String USERS_JSON_PATH = "web/src/main/resources/data/users.json";
    private List<Users> users;

    public UsersRepository() {
        this.objectMapper = new ObjectMapper();
        this.users = loadUsersFromFile(USERS_JSON_PATH);
        verifyFileIntegrity();
    }

    public List<Users> getUsers() {
        return new ArrayList<>(users);
    }

    private List<Users> loadUsersFromFile(String path) {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            System.out.println("DA");
            try {
                return objectMapper.readValue(file, new TypeReference<List<Users>>() {});
            } catch (IOException e) {
                throw new RuntimeException("Failed to load users: " + e.getMessage(), e);
            }
        }
        return new ArrayList<>();
    }

    private void verifyFileIntegrity() {
        List<Long> allIds = getAllUsersIds();
        List<Long> duplicateIds = allIds.stream()
                .filter(id -> allIds.stream().filter(id::equals).count() > 1)
                .collect(Collectors.toList());

        if (!duplicateIds.isEmpty()) {
            throw new IllegalStateException("Duplicate IDs detected: " + duplicateIds);
        }
    }

    public List<Long> getAllUsersIds() {
        List<Long> usersIds = getUsers().stream().map(Users::getId).collect(Collectors.toList());
        return usersIds;
    }

    public Users findByUsername(String username) {
        for(Users user: users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
//        int id = 5;
//        return new Users((long) id,"admin","admin");
    }

    public void saveUser(Users user) {
        if (validateUserRequest(user)) {
            long id = getNextAvailableId();
            user.setId(id);

            if(!isIdUnique(user.getId())){
                throw new IllegalArgumentException("User ID must be unique.");
            }

            users.add(user);
            reloadJson(USERS_JSON_PATH, users);
        } else {
            throw new IllegalArgumentException("Invalid EventRequest: Validation failed.");
        }
    }

    private boolean validateUserRequest(Users user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            System.err.println("Validation failed: username is required.");
            return false;
        }
        if (user.getPassword() == null) {
            System.err.println("Validation failed: password is required.");
            return false;
        }
        return true;
    }

    private long getNextAvailableId() {
        List<Users> allUsers = new ArrayList<>();
        allUsers.addAll(users);

        return allUsers.stream()
                .mapToLong(Users::getId)
                .max()
                .orElse(0L) + 1;
    }

    private boolean isIdUnique(Long id){
        return !getAllUsersIds().contains(id);
    }

    private void reloadJson(String file, List<Users> list){
        try {
            objectMapper.writeValue(new File(file), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
