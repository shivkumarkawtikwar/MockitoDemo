package com.learn.demo;

import com.learn.demo.database.DatabaseService;
import com.learn.demo.model.User;

public class DataBaseAuthenticationHandlerImpl implements AuthenticationHandler {

   private DatabaseService dbService;

    public DataBaseAuthenticationHandlerImpl(DatabaseService dbService) {
        this.dbService = dbService;
    }


    public boolean authenticate(String userName, String password) throws Exception {

        User user = dbService.fetchUser(userName);
        if (user == null)
            throw new Exception("Invalid User");

        if (user.getPassword().equals(password))
            return true;

        return false;
    }


}
