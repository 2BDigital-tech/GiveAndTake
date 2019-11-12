package com.example.giveandtake;


import java.util.ArrayList;

public class User {
    public String name, email, phone;
    public ArrayList<String> Option1 = new ArrayList<>();
    public ArrayList<String> Option2 = new ArrayList<>();
    public ArrayList<String> Option3 = new ArrayList<>();
    public ArrayList<String> Option4 = new ArrayList<>();

    public User(String name, String email, String phone, ArrayList<String> option1, ArrayList<String> option2, ArrayList<String> option3, ArrayList<String> option4) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
    }

}


