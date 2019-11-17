package com.example.giveandtake;


import java.util.ArrayList;

public class User {

    private ArrayList<String> option1 = new ArrayList<>();
    private ArrayList<String> option2 = new ArrayList<>();
    private ArrayList<String> option3 = new ArrayList<>();
    private ArrayList<String> option4 = new ArrayList<>();
    private String email;
    private String name ;
    private String phone;

    public User(){
    }

    public User(String name, String email, String phone, ArrayList<String> option1, ArrayList<String> option2, ArrayList<String> option3, ArrayList<String> option4) {

        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.email = email;
        this.name = name;
        this.phone = phone;

    }

    public ArrayList<String> getOption1() {
        return option1;
    }

    public void setOption1(ArrayList<String> option1) {
        option1 = option1;
    }

    public ArrayList<String> getOption2() {
        return option2;
    }

    public void setOption2(ArrayList<String> option2) {
        option2 = option2;
    }

    public ArrayList<String> getOption3() {
        return option3;
    }

    public void setOption3(ArrayList<String> option3) {
        option3 = option3;
    }

    public ArrayList<String> getOption4() {
        return option4;
    }

    public void setOption4(ArrayList<String> option4) {
        option4 = option4;
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


}



