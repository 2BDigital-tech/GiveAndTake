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

    public ArrayList<String> getOption1() {
        return Option1;
    }

    public void setOption1(ArrayList<String> option1) {
        Option1 = option1;
    }

    public ArrayList<String> getOption2() {
        return Option2;
    }

    public void setOption2(ArrayList<String> option2) {
        Option2 = option2;
    }

    public ArrayList<String> getOption3() {
        return Option3;
    }

    public void setOption3(ArrayList<String> option3) {
        Option3 = option3;
    }

    public ArrayList<String> getOption4() {
        return Option4;
    }

    public void setOption4(ArrayList<String> option4) {
        Option4 = option4;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
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


