package com.example.web.security.userService;

import com.example.web.models.User;

public class CurrentUser {

    private static CurrentUser instance = null;

    public User user;

    private CurrentUser()
    {
        user = new User();
    }

    public static CurrentUser getInstance()
    {
        if(instance == null)
            instance = new CurrentUser();
        return instance;
    }

}
