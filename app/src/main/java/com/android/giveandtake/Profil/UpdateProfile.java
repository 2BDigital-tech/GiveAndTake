package com.android.giveandtake.Profil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.giveandtake.R;
import com.android.giveandtake.Start_Application;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button save;
    private TextView newusername, newuserphone, newuseremail, newusercity;
    private DatabaseReference UsersRef;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        newusername = findViewById(R.id.changename);
        newuserphone = findViewById(R.id.changephone);
        newuseremail = findViewById(R.id.changeemail);
        newusercity = findViewById(R.id.changecity);
        save = findViewById(R.id.savebtn);

        firebaseAuth = firebaseAuth.getInstance();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        FirebaseUser user=firebaseAuth.getCurrentUser();
        String idUser=user.getUid();


        UsersRef.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UsersRef.child("name").setValue(newusername.getText().toString());
                UsersRef.child("phone").setValue(newuserphone.getText().toString());
                UsersRef.child("email").setValue(newuseremail.getText().toString());
                UsersRef.child("city").setValue(newusercity.getText().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();            }
        });


    }
}
