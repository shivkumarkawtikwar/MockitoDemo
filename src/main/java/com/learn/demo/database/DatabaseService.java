package com.learn.demo.database;

import com.learn.demo.model.User;

public interface DatabaseService {

    User fetchUser(String userName);
}
