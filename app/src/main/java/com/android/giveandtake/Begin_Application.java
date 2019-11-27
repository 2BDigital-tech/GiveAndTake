package com.android.giveandtake;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Begin_Application extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin__application);



//if user connect as not open a login and register box

        firebaseAuth=firebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){

                    Intent activi=new Intent(Begin_Application.this, Connect_Fragment.class);
                    startActivity(activi);
                    finish();
                }
                else{
                    Intent activi2=new Intent(Begin_Application.this, Start_Application.class);
                    startActivity(activi2);
                    finish();


                }
            }
        }, 1000);



    }
}
