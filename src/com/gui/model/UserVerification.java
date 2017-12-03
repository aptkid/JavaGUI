package com.gui.model;

public class UserVerification {

    private static String username;

    private static String password;

    public UserVerification(String username) {


    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserVerification.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserVerification.password = password;
    }
}
