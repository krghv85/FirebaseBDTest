package com.tradexl.firebasedbtest.model;

/**
 * Created by Raghav on 26-Aug-17.
 */

public class SignUpModel {
    private String fname;
    private String lname;
    SignUpModel()
    {}

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String mobile;
    private String email;
    private String password;

    public SignUpModel(String fname, String lname, String mobile, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }


}
