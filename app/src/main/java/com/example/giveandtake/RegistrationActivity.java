package com.example.giveandtake;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giveandtake.home.ItemDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone;
    private ProgressBar progressBar;
    private Button ReturnBtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;


//    final String Email = editTextEmail.getText().toString().trim();
//    final String Password = editTextName.getText().toString().trim();
//    final String Phone = editTextPhone.getText().toString().trim();


    // #1 option 1 - checkbox
    String[] itemsOptions1;
    Button Option1Btn;
    boolean[] option1Checkitems;
    ArrayList<Integer> userIteamOption1 = new ArrayList<>();
    ArrayList<String> finalUserItemsOption1 = new ArrayList<>();

    // #2 option 2 - checkbox
    String[] itemsOptions2;
    Button Option2Btn;
    boolean[] option2Checkitems;
    ArrayList<Integer> userIteamOption2 = new ArrayList<>();
    ArrayList<String> finalUserItemsOption2 = new ArrayList<>();

    // #3 option 3 - checkbox
    String[] itemsOptions3;
    Button Option3Btn;
    boolean[] option3Checkitems;
    ArrayList<Integer> userIteamOption3 = new ArrayList<>();
    ArrayList<String> finalUserItemsOption3 = new ArrayList<>();

    // #4 option 4 - checkbox
    String[] itemsOptions4;
    Button Option4Btn;
    boolean[] option4Checkitems;
    ArrayList<Integer> userIteamOption4 = new ArrayList<>();
    ArrayList<String> finalUserItemsOption4 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPhone = findViewById(R.id.edit_text_phone);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button_register).setOnClickListener(this);
        ReturnBtn = (Button)findViewById(R.id.returnRegisterbtn);
        ReturnBtn.setOnClickListener(this);

        // optoins buttons
        Option1Btn = (Button)findViewById(R.id.Option1);
        Option2Btn = (Button)findViewById(R.id.Option2);
        Option3Btn = (Button)findViewById(R.id.Option3);
        Option4Btn = (Button)findViewById(R.id.Option4);




        // Check boxs ///



        // #1 option 1 - checkbox (impliment) //
        itemsOptions1 = getResources().getStringArray(R.array.Option1);
        option1Checkitems = new boolean[itemsOptions1.length];

        //--------//

        Option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistrationActivity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setMultiChoiceItems(itemsOptions1, option1Checkitems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!userIteamOption1.contains(position)) {
                                userIteamOption1.add(position);
                            }
                        } else if(userIteamOption1.contains(position)){
                                userIteamOption1.remove(position);
                            }
                    }

                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finalUserItemsOption1 = new ArrayList<>();
                        for(int e : userIteamOption1){
                            finalUserItemsOption1.add(itemsOptions1[e]);
                        }

                    }
                });


                AlertDialog mDialog  = mBuilder.create();
                mDialog.show();

            }

        });


        // end option 1 - checkbox

        // #2 option 2 - checkbox


        // #2 option 2 - checkbox (impliment) //
        itemsOptions2 = getResources().getStringArray(R.array.Option2);
        option2Checkitems = new boolean[itemsOptions2.length];


        //--------//

        Option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistrationActivity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setMultiChoiceItems(itemsOptions2, option2Checkitems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!userIteamOption2.contains(position)) {
                                userIteamOption2.add(position);
                            }
                        } else if(userIteamOption2.contains(position)){
                            userIteamOption2.remove(position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finalUserItemsOption2 = new ArrayList<>();
                        for(int e : userIteamOption2){
                            finalUserItemsOption2.add(itemsOptions2[e]);
                        }

                    }
                });

                AlertDialog mDialog  = mBuilder.create();
                mDialog.show();

            }

        });


        // end option 2 - checkbox
        // #3 option 3 - checkbox

        // #3 option 3 - checkbox (impliment) //
        itemsOptions3 = getResources().getStringArray(R.array.Option3);
        option3Checkitems = new boolean[itemsOptions3.length];


        //--------//

        Option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistrationActivity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setMultiChoiceItems(itemsOptions3, option3Checkitems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!userIteamOption3.contains(position)) {
                                userIteamOption3.add(position);
                            }
                        } else if(userIteamOption3.contains(position)){
                            userIteamOption3.remove(position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finalUserItemsOption3 = new ArrayList<>();
                        for(int e : userIteamOption3){
                            finalUserItemsOption3.add(itemsOptions3[e]);
                        }

                    }
                });

                AlertDialog mDialog  = mBuilder.create();
                mDialog.show();

            }

        });
        // end option 3 - checkbox


        // #4 option 4 - checkbox


        // #4 option 4 - checkbox (impliment) //
        itemsOptions4 = getResources().getStringArray(R.array.Option4);
        option4Checkitems = new boolean[itemsOptions4.length];


        //--------//

        Option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistrationActivity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setMultiChoiceItems(itemsOptions4, option4Checkitems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!userIteamOption4.contains(position)) {
                                userIteamOption4.add(position);
                            }
                        } else if(userIteamOption4.contains(position)){
                            userIteamOption4.remove(position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finalUserItemsOption4 = new ArrayList<>();
                        for(int e : userIteamOption4){
                            finalUserItemsOption4.add(itemsOptions4[e]);
                        }



                    }
                });

                AlertDialog mDialog  = mBuilder.create();
                mDialog.show();

            }

        });
        // end option 4 - checkbox





        ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        Log.e(": Failed=", this.editTextPhone.getText().toString());

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }
        if (phone.charAt(0) != '0') {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }
        if (phone.charAt(1) != '5') {
            editTextPhone.setError(getString(R.string.input_error_Israel_phone));
            editTextPhone.requestFocus();
            return;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference("Users_Infomation");

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    phone,
                                    finalUserItemsOption1,
                                    finalUserItemsOption2,
                                    finalUserItemsOption3,
                                    finalUserItemsOption4
                            );
                            String id = myRef.push().getKey();

                            myRef.child(id).setValue(user);

                            FirebaseDatabase.getInstance().getReference("Users_Infomation")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistrationActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                registerUser();
                break;
        }
    }
}