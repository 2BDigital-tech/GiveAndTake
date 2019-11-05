package com.example.giveandtake;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private Button ReturnBtn;
    private EditText emailbox;
    private EditText passwordbox;
    private Button RegisterBtn;
    private FirebaseAuth firebaseAut;
    private DatabaseReference firebaseDatabase;
    private Button computerBtn;
    boolean[] checkItems;
    String[] listItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ReturnBtn = (Button)findViewById(R.id.returnRegisterbtn);
        emailbox = (EditText)findViewById(R.id.emailboxRegister);
        passwordbox = (EditText)findViewById(R.id.passwordboxRegister);
        RegisterBtn = (Button)findViewById(R.id.buttonRegister);
        computerBtn = (Button)findViewById(R.id.computerBtn);
        firebaseAut = firebaseAut.getInstance();

        // if there is problme, its here
            firebaseDatabase =  FirebaseDatabase.getInstance().getReference();


        listItems = getResources().getStringArray(R.array.computerOps);
        checkItems = new boolean[listItems.length];

        computerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistrationActivity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setMultiChoiceItems(listItems, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!mUserItems.contains(position)){
                                mUserItems.add(position);
                            } else{
                                mUserItems.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String Email = emailbox.getText().toString().trim();
                        String id = firebaseDatabase.push().getKey();
                        User newUser = new User(Email,mUserItems);
                        firebaseDatabase.child(id).setValue(newUser);


                    }
                });

                AlertDialog mDialog  = mBuilder.create();
                mDialog.show();

            }

        });















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
