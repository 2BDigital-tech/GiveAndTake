package com.android.giveandtake.Profil;


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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

    public class ProfileFragment extends Fragment {

    private ProfileViewModel notificationsViewModel;
    private FirebaseAuth firebaseAuth;
    private Button unsigout;
    private TextView name, phone, email, city;
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
       phone=(TextView)root.findViewById(R.id.phoneProfil);
       email=(TextView)root.findViewById(R.id.emailprofil);
       city=(TextView)root.findViewById(R.id.cityprofil);

        String idUser=user.getUid();

      UsersRef.child(idUser).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String userId=dataSnapshot.child("name").getValue(String.class);
              String phoneid=dataSnapshot.child("phone").getValue(String.class);
              String emailid=dataSnapshot.child("email").getValue(String.class);
              String cityid=dataSnapshot.child("city").getValue(String.class);

              name.setText(userId);
              phone.setText(phoneid);
              email.setText(emailid);
              city.setText(cityid);

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
