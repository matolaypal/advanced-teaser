package com.myapp.app.passwordprotector.model;

/**
 * Here define the User model.
 *
 * !NOTE! Recently dead code.
 */
public class User {
    //TODO Make a decision its fate!
    private String userName;
    private String password;
    private final static String USR_KEY = "USR_KEY";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
