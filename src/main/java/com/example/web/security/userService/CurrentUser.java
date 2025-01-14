package com.example.web.security.userService;

import com.example.web.models.Users;

public class CurrentUser {

    private static CurrentUser instance = null;

    public Users user;

    private CurrentUser()
    {
        user = new Users();
    }

    public static CurrentUser getInstance()
    {
        if(instance == null)
            instance = new CurrentUser();
        return instance;
    }

}
