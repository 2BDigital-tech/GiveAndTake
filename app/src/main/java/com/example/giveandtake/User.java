package com.example.giveandtake;

import java.util.ArrayList;

public class User {

    String Name;
    String UserEmail;
    String PhoneNumber;
    ArrayList<Integer> computer;

    public User(String userEmail,ArrayList<Integer> computer) {
        UserEmail = userEmail;
        this.computer = computer;
    }


    public String getUserEmail() {
        return UserEmail;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public ArrayList<Integer> getComputer() {
        return computer;
    }
}
