package com.example.giveandtake;

import java.util.ArrayList;

public class User {
    static int countUsers = 0;
    String Name;
    int idnum;
    String UserEmail;
    String PhoneNumber;
    ArrayList<String> computerSubject;

    public User(String userEmail,ArrayList<String> computerSubject) {
        UserEmail = userEmail;
        this.computerSubject = computerSubject;
        idnum = countUsers;
        this.countUsers++;

    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public ArrayList<String> getComputer() {
        return computerSubject;
    }
}
