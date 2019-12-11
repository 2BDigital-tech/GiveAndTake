package com.android.giveandtake.Login;


public class User {
    public String name, email, phone,city;

    public User(){

    }

    public User(String name, String email, String phone,String city) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city=city;
    }



//    public String getUserEmail() {
//        return email;
//    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }
//
//    public String getUserName() {
//        return name;
//    }

    public void setUserName(String userName) {
        this.name = userName;
    }


//    public String getUserPhone() {
//        return phone;
//    }

    public void setUserPhone(String userPhone) {
        this.phone = phone;
    }


    ////setters and getters for Option1'2'3
}


