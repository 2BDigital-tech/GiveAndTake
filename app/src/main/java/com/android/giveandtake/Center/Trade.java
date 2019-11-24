package com.android.giveandtake.Center;

public class Trade {

    private int imageResocure;
    private String current_post_id;
    private String current_user_id;
    private String user_post_id;
    private String user_post_name;
    private String current_user_name;
    private String post_give;
    private String post_take;
    private String current_user_phone;
    private String current_user_city;

    public Trade(int imageResocure, String current_post_id, String current_user_id, String user_post_id, String user_post_name, String current_user_name, String post_give, String post_take, String current_user_phone, String current_user_city) {
        this.imageResocure = imageResocure;
        this.current_post_id = current_post_id;
        this.current_user_id = current_user_id;
        this.user_post_id = user_post_id;
        this.user_post_name = user_post_name;
        this.current_user_name = current_user_name;
        this.post_give = post_give;
        this.post_take = post_take;
        this.current_user_phone = current_user_phone;
        this.current_user_city = current_user_city;
    }

    public int getImageResocure() {
        return imageResocure;
    }

    public String getCurrent_post_id() {
        return current_post_id;
    }

    public String getCurrent_user_id() {
        return current_user_id;
    }

    public String getUser_post_id() {
        return user_post_id;
    }

    public String getUser_post_name() {
        return user_post_name;
    }

    public String getCurrent_user_name() {
        return current_user_name;
    }

    public String getPost_give() {
        return post_give;
    }

    public String getPost_take() {
        return post_take;
    }

    public String getCurrent_user_phone() {
        return current_user_phone;
    }

    public String getCurrent_user_city() {
        return current_user_city;
    }
}
