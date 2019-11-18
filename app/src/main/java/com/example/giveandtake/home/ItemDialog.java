package com.example.giveandtake.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.giveandtake.R;
import com.example.giveandtake.RegistrationActivity;
import com.example.giveandtake.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;

public class ItemDialog extends AppCompatDialogFragment {

    private Button giveBtn;
    private Button takeBtn;
    private FirebaseDatabase firebaseDatabase;
    private EditText finalName;





    public ItemDialog(){

    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        giveBtn = view.findViewById(R.id.giveBtn);
        takeBtn = view.findViewById(R.id.takeBtn);




        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference("Users_Infomation");

        giveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        mBuilder.setView(view)
                .setTitle("New Post")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return mBuilder.create();
    }
}
