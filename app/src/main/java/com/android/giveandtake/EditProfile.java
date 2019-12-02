package com.android.giveandtake;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.database.FirebaseDatabase;


public class EditProfile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button save, changename, changephone, changeemail;
    private EditText newname, newphone, newemail;
    private DatabaseReference UsersRef;
    private FirebaseUser current_user;
    private Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        newname = findViewById(R.id.changename);
        newphone = findViewById(R.id.changephone);
        newemail = findViewById(R.id.changemail);


        firebaseAuth = firebaseAuth.getInstance();

        save = (Button)findViewById(R.id.savebtn);
        changename = (Button)findViewById(R.id.namebutton);
        changephone = (Button)findViewById(R.id.phonebutton);
        changeemail = (Button)findViewById(R.id.emailbutton);
        mySpinner = (Spinner) findViewById(R.id.cityspinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EditProfile.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.City));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String changedname = newname.getText().toString();
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = newname.getText().toString();
                final String email = newemail.getText().toString();
                final String city = (String) mySpinner.getSelectedItem().toString();
                final String phone = newphone.getText().toString();
                editProfile(name,email,phone,city);
                finish();
            }
        });

    }

    private void editProfile(String name, String email, String phone, String city) {

        current_user = firebaseAuth.getCurrentUser();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid());
        User myuser = new User(name,email,phone,city);

        UsersRef.setValue(myuser);

    }

    }


