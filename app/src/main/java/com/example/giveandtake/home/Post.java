package com.example.giveandtake.home;

public class Post {
    private int imageResocure;
    private String NameAsk;
    private String PhoneAsk;
    private String Give;
    private String Take;
    private String currentUserID;


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
    public String getGive() {
        return Give;
    }
    public String getTake() {
        return Take;
    }
    public String getcurrentUserID() {
        return currentUserID;
    }



    public Post(int img ,String nameAsk, String phoneAsk, String GiveAsk, String TakeAsk,String id) {
        imageResocure = img;
        NameAsk = nameAsk;
        PhoneAsk = phoneAsk;
        Give = GiveAsk;
        Take = TakeAsk;
        currentUserID = id;

    }
}
