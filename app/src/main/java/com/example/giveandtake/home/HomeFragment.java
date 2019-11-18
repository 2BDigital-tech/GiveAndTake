package com.example.giveandtake.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giveandtake.R;
import com.example.giveandtake.RegistrationActivity;
import com.example.giveandtake.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RecyclerView _RecyclerView;
    private RecyclerView.Adapter _Adapter;
    private RecyclerView.LayoutManager _LayoutManager;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<Item> exampleItems = new ArrayList<>();
    private ArrayList<String> category = new ArrayList<>();
    private String myname;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser f;

    User coorentUser;

    View root;

    /// get the Buttoms ////

    private Button addItem;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference("Posts");
        firebaseAuth = firebaseAuth.getInstance();


        addItem = root.findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }

        });



        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    String phone = ds.child("phone").getValue(String.class);


                    Item Item1 = new Item(R.drawable.item_24dp, name, phone);
                    boolean add = exampleItems.add(Item1);

                }
                _RecyclerView = root.findViewById(R.id.recyclerview);
                _RecyclerView.setHasFixedSize(true);
                _LayoutManager = new LinearLayoutManager(getContext());
                _Adapter = new ItemAdapter(exampleItems);
                _RecyclerView.setLayoutManager(_LayoutManager);
                _RecyclerView.setAdapter(_Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        myRef.addListenerForSingleValueEvent(eventListener);


        return root;

    }

    private void openDialog() {
        ItemDialog itemDialog = new ItemDialog();
        itemDialog.show(getFragmentManager(), "Ex");

    }
}

