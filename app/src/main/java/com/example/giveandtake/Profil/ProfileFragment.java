package com.example.giveandtake.Profil;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private ProfileViewModel notificationsViewModel;
    FirebaseAuth firebaseAuth;
    Button unsigout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

    unsigout  =(Button)root.findViewById(R.id.Disconnect);

        unsigout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.getInstance().signOut();

                Intent activi=new Intent(getActivity(),com.example.giveandtake.MainActivity.class);
                startActivity(activi);
                Toast.makeText(getActivity(),"Disconnect Full", Toast.LENGTH_LONG).show();

            }
        });



        return root;
    }
}