package com.android.giveandtake;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.giveandtake.Login.LoginActivity;
import com.android.giveandtake.Login.RegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Start_Application extends AppCompatActivity {
    private TextView editsignup;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        final Button RegisterBtn = (Button)findViewById(R.id.RegisterMainBox);

        editsignup = findViewById(R.id.signup_start);
        String text = "Already have an account? SIGN IN";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent in = new Intent(Start_Application.this,LoginActivity.class);
                startActivity(in);
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(true);
            }
        };

        ss.setSpan(clickableSpan1, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        editsignup.setText(ss);
        editsignup.setMovementMethod(LinkMovementMethod.getInstance());


        Button ReturnBtn = (Button)findViewById(R.id.returnLoginbtn);





        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Application.this, RegistrationActivity.class);
                startActivity(i);
                finish();

            }
        });

    }


}
