package com.android.giveandtake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.android.giveandtake.Admin.AdminConnect;
import com.android.giveandtake.Login.Account;
import com.android.giveandtake.Login.LoginActivity;
import com.android.giveandtake.Login.RegistrationActivity;
import com.android.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;

public class Start_Application extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        final Button RegisterBtn = (Button)findViewById(R.id.RegisterMainBox);
        Button LoginBtn = (Button)findViewById(R.id.LoginMainBox);
        Button ReturnBtn = (Button)findViewById(R.id.returnLoginbtn);



        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Application.this, LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Application.this, Account.class);
                startActivity(i);

            }
        });

    }


}
