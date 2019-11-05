package com.example.giveandtake;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private Button ReturnBtn;
    private EditText emailbox;
    private EditText passwordbox;
    private Button RegisterBtn;
    private FirebaseAuth firebaseAut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ReturnBtn = (Button)findViewById(R.id.returnRegisterbtn);
        emailbox = (EditText)findViewById(R.id.emailboxRegister);
        passwordbox = (EditText)findViewById(R.id.passwordboxRegister);
        RegisterBtn = (Button)findViewById(R.id.buttonRegister);
        firebaseAut = firebaseAut.getInstance();



        ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });



        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please wait...", "Processing...", true);
                (firebaseAut.createUserWithEmailAndPassword(emailbox.getText().toString(), passwordbox.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);

                                    startActivity(i);
                                } else {
                                    Log.e("onComplete: Failed=", task.getException().getMessage());
                                    Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }


}
