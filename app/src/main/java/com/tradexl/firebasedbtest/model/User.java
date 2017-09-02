package com.tradexl.firebasedbtest.model;

/**
 * Created by Raghav on 26-Aug-17.
 */

public class User {
    public   User()
    {}
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String name;
    public String email;
}
