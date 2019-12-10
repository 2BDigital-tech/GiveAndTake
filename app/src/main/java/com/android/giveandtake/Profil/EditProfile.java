package com.android.giveandtake.Profil;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.android.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.database.FirebaseDatabase;


public class EditProfile extends AppCompatActivity {

    private Button save;
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

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        UsersRef = FirebaseDatabase.getInstance().getReference("Users").child(current_user.getUid());

        save = (Button)findViewById(R.id.btn_back);
        mySpinner = (Spinner) findViewById(R.id.cityspinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EditProfile.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.City_Admin));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String changedname = newname.getText().toString();
                final String changedemail = newemail.getText().toString();
                final String changedphone = newphone.getText().toString();
                final String changedcity = (String) mySpinner.getSelectedItem().toString();

                if (changedname.isEmpty()==false){
                    UsersRef.child("name").setValue(changedname);
                }
                if (changedemail.isEmpty()==false) {
                    UsersRef.child("email").setValue(changedemail);
                }
                if (changedphone.isEmpty()==false) {
                    UsersRef.child("phone").setValue(changedphone);
                }
                if (changedcity.contains("Choose city")==false) {
                    UsersRef.child("city").setValue(changedcity);
                }

                    finish();
            }
        });

    }

}


