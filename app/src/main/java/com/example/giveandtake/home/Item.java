package com.example.giveandtake.home;

public class Item {
    private int imageResocure;
    private String NameAsk;
    private String PhoneAsk;
    private String Name;

    public void setImageResocure(int imageResocure) {
        this.imageResocure = imageResocure;
    }

    public void setNameAsk(String nameAsk) {
        NameAsk = nameAsk;
    }

    public void setPhoneAsk(String phoneAsk) {
        PhoneAsk = phoneAsk;
    }

    public int getImageResocure() {
        return imageResocure;
    }

    public String getNameAsk() {
        return NameAsk;
    }

    public String getPhoneAsk() {
        return PhoneAsk;
    }

    public Item(int imageResocure, String nameAsk, String phoneAsk) {
        this.imageResocure = imageResocure;
        NameAsk = nameAsk;
        PhoneAsk = phoneAsk;
    }
}
