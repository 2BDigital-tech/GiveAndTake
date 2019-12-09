package com.android.giveandtake.Login;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.giveandtake.Begin_Application;
import com.android.giveandtake.Connect_Fragment;
import com.android.giveandtake.Login.LoginActivity;
import com.android.giveandtake.R;
import com.android.giveandtake.Start_Application;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account_Google extends AppCompatActivity  {

    private EditText editTextPhone;
    private EditText editTextName;
    private FirebaseAuth firebaseAuth;
    private Spinner mySpinner;
    private String phone;
    private String city;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__google);
        editTextPhone = findViewById(R.id.edit_text_phone2);
        editTextName = findViewById(R.id.edit_text_name2);

        //first we intialized the FirebaseAuth object
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser myuser = firebaseAuth.getCurrentUser();


        mySpinner = (Spinner) findViewById(R.id.cityspinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Account_Google.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.City));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        findViewById(R.id.Cancler_buuton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Account_Google.this, "Good Bye", Toast.LENGTH_LONG).show();
                            Intent activi = new Intent(Account_Google.this, Begin_Application.class);
                            startActivity(activi);
                            finish();
                        } else {
                            Toast.makeText(Account_Google.this, "Try again or Contact Administrator", Toast.LENGTH_LONG).show();
                        }
                    }
                    });
                }
        });



        findViewById(R.id.Submit_register_google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = editTextPhone.getText().toString().trim();
                name = editTextName.getText().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError(getString(R.string.input_error_name));
                    editTextName.requestFocus();
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
                city = (String) mySpinner.getSelectedItem().toString();


                final FirebaseUser user = firebaseAuth.getCurrentUser();

                User user_current = new User(
                        name,
                        user.getEmail().toString(),
                        phone,
                        city);


                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user_current).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Account_Google.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Account_Google.this, Connect_Fragment.class));
                            finish();
                        } else {
                            Toast.makeText(Account_Google.this, "Error Registration", Toast.LENGTH_LONG).show();
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent activi = new Intent(Account_Google.this, Start_Application.class);
                                        startActivity(activi);
                                        finish();
                                        Toast.makeText(Account_Google.this, getString(R.string.error_Register), Toast.LENGTH_LONG).show();

//
                                    }
                                }

                            });
                        }
                    }

                });
            }
        });

    }

}