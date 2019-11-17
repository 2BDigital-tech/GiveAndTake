package com.example.giveandtake;


import java.util.ArrayList;

public class User2 {
    public String name, phone;

    public User2(String name ,String phone){
        this.name = name;
        this.phone = phone;
            }
public User2(){
    this.name = "";
    this.phone = "";
}

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }


    public String getUserPhone() {
        return phone;
    }

    public void setUserPhone(String userPhone) {
        this.phone = phone;
    }


    ////setters and getters for Option1'2'3
}


