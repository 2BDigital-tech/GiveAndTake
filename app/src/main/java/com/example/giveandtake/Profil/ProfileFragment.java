package com.example.giveandtake.Profil;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.giveandtake.Admin.PostAdmin;
import com.example.giveandtake.R;
import com.example.giveandtake.Start_Application;
import com.example.giveandtake.home.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel notificationsViewModel;
    private FirebaseAuth firebaseAuth;
    private Button unsigout;
    private TextView name;
    private DatabaseReference UsersRef;
    private FirebaseUser firebaseUser;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

        unsigout = (Button) root.findViewById(R.id.Disconnect);

        firebaseAuth = firebaseAuth.getInstance();

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        FirebaseUser user=firebaseAuth.getCurrentUser();

       name=(TextView)root.findViewById(R.id.nameprofile);
String idUser=user.getUid();

      UsersRef.child(idUser).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String userId=dataSnapshot.child("name").getValue(String.class);
              name.setText(userId);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });



        unsigout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.getInstance().signOut();

                Intent activi = new Intent(getActivity(), Start_Application.class);
                startActivity(activi);
                Toast.makeText(getActivity(), "Disconnect Full", Toast.LENGTH_LONG).show();

            }
        });


        return root;
    }



}
