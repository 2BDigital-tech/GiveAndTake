package com.example.giveandtake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.giveandtake.Admin.AdminConnect;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Start_Application extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        final Button RegisterBtn = (Button)findViewById(R.id.RegisterMainBox);
        Button LoginBtn = (Button)findViewById(R.id.LoginMainBox);
        Button ReturnBtn = (Button)findViewById(R.id.returnLoginbtn);
        Button testhome = (Button)findViewById(R.id.testhome);
/*
if user connect as not open a login and register box 
 */
//        firebaseAuth=firebaseAuth.getInstance();
//
//        FirebaseUser user=firebaseAuth.getCurrentUser();
//        if(user!=null){
//
//            Intent activi=new Intent(Start_Application.this, Connect_Fragment.class);
//            startActivity(activi);
//        }


        testhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Application.this, AdminConnect.class);
                startActivity(i);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Application.this, LoginActivity.class);
                startActivity(i);
            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Application.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

    }


}
