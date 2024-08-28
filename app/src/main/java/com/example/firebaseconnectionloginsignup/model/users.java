package com.example.firebaseconnectionloginsignup.model;

public class users {
    String name;
    String gmail;
    String pass;

    public users() {
    }

    public users(String gmail, String name, String pass) {
        this.gmail = gmail;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
