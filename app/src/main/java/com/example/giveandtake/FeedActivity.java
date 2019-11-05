package com.example.giveandtake;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FeedActivity extends AppCompatActivity {
//test git naomi
    private TextView tvemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        tvemail = (TextView)findViewById(R.id.emailProfile);
        tvemail.setText(getIntent().getExtras().getString("Email"));
    }
}
