package com.android.giveandtake.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.giveandtake.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_code extends AppCompatActivity {
private Button returnn;
private Button Sub;
private EditText emailboxLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_code);

        emailboxLogin = (EditText)findViewById(R.id.mail_box);

        returnn=(Button)findViewById(R.id.btn_back);
        Sub=(Button)findViewById(R.id.btn_reset_password);

        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forgot_code.this,LoginActivity.class));
                finish();
            }
        });

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_email = emailboxLogin.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(user_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Forgot_code.this, "An email has been sent to you! Please check it " +
                                            "so you can change your password", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Forgot_code.this,LoginActivity.class));
                                    finish();
                                }
                                Toast.makeText(Forgot_code.this, "Email Not Found, Please try again", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

    }
}
