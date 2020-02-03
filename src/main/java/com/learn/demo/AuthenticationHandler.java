package com.learn.demo;

public interface AuthenticationHandler {


    boolean authenticate(String userName, String password) throws Exception;
}
