package com.example.giveandtake;


import java.util.ArrayList;

public class User {
    public String name, email, phone;
    public ArrayList<String> itemComputer = new ArrayList<>();

    public User(){

    }


    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        //this.itemComputer = itemComputer;
    }
}
