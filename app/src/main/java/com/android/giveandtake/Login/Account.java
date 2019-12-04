package com.android.giveandtake.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.giveandtake.R;

public class Account extends AppCompatActivity {
private Button Create_account;
private Button Google_Create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

    Create_account=(Button)findViewById(R.id.createaccount);
    Google_Create=(Button)findViewById(R.id.use_google);

    Create_account.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(Account.this,RegistrationActivity.class));
        }

    });
    Google_Create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Account.this,Account_Google.class));
        }
    });


    }
}
