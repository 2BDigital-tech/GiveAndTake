package com.android.giveandtake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.android.giveandtake.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button save;
    private TextView name, phone, email, city;
    private DatabaseReference UsersRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name = findViewById(R.id.changename);
        phone = findViewById(R.id.changephone);
        email = findViewById(R.id.changeemail);
        city = findViewById(R.id.changecity);
        save = findViewById(R.id.savebtn);

        firebaseAuth = firebaseAuth.getInstance();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        user=firebaseAuth.getCurrentUser();
        String idUser=user.getUid();


        UsersRef.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UsersRef.child("name").setValue(name.getText().toString());
                UsersRef.child("phone").setValue(phone.getText().toString());
                UsersRef.child("email").setValue(email.getText().toString());
                UsersRef.child("city").setValue(city.getText().toString());

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
