package org.example.services;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.User;


public class AuthenticationServices {
    private static User loggedInUser;

    public static void setLoggedUser(User user){
        loggedInUser = user;
    }
    public static User getLoggedInUser (){
        return loggedInUser;
    }
    public static void logout(){
        loggedInUser = null;
    }
}
