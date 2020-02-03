package com.learn.demo;

import com.learn.demo.database.DatabaseService;
import com.learn.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;


public class DatabaseAuthenticationHandlerImplTest {

    @Test
    public void testAuthenticatePositiveCase() throws Exception {

        //mocking DatabaseService class
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        //providing behaviour to fetchUser API
        //returning given User object when fetchUser API is called from authenticate(targeted API)
        Mockito.when(databaseService.fetchUser(any(String.class))).thenReturn(new User("testUser", "testPw"));

        //initializing target class with mocked object
        AuthenticationHandler handler = new DataBaseAuthenticationHandlerImpl(databaseService);

        //testing authenticate method
        boolean isAuthenticated = handler.authenticate("testUser", "testPw");
        Assert.assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticateWrongPasswordCase() throws Exception {

        //mocking DatabaseService class
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        //providing behaviour to fetchUser API
        //returning given User object when fetchUser API is called from authenticate(targeted API)
        Mockito.when(databaseService.fetchUser(any(String.class))).thenReturn(new User("testUser", "testPw"));

        //initializing target class with mocked object
        AuthenticationHandler handler = new DataBaseAuthenticationHandlerImpl(databaseService);

        //testing authenticate method when wrong password is given
        boolean isAuthenticated = handler.authenticate("testUser", "wrongPw");
        Assert.assertFalse(isAuthenticated);
    }

    @Test
    public void testAuthenticateUserNotFoundCase() {

        //mocking DatabaseService class
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        //providing behaviour to fetchUser API
        //returning null User object when fetchUser API is called from authenticate(targeted API)
        Mockito.when(databaseService.fetchUser(any(String.class))).thenReturn(null);

        //initializing target class with mocked object
        AuthenticationHandler handler = new DataBaseAuthenticationHandlerImpl(databaseService);

        try {
            //testing authenticate method when null is gets return from fetchUser API
            boolean isAuthenticated = handler.authenticate("testUser", "testPw");
            Assert.assertTrue(false);
        } catch(Exception e) {
            Assert.assertTrue(e.getMessage().equals("Invalid User"));
        }
    }
}
