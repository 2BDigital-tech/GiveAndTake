package com.example.giveandtake.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.giveandtake.R;
import com.example.giveandtake.home.HomeViewModel;
import com.example.giveandtake.home.Item;
import com.example.giveandtake.home.ItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowUsers extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private RecyclerView _RecyclerView;
    private RecyclerView.Adapter _Adapter;
    private RecyclerView.LayoutManager _LayoutManager;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<Item> exampleItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);



        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference("Users_Infomation");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    //String email = ds.child("email").getValue(String.class);
                    String phone = ds.child("phone").getValue(String.class);
                    Item Item1 = new Item(R.drawable.ic_phone,name,phone);
                    boolean add = exampleItems.add(Item1);

                }
                _RecyclerView = findViewById(R.id.User_recycler);
                _RecyclerView.setHasFixedSize(true);
                _LayoutManager = new LinearLayoutManager(ShowUsers.this);
                _Adapter = new ItemAdapter(exampleItems);
                _RecyclerView.setLayoutManager(_LayoutManager);
                _RecyclerView.setAdapter(_Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        myRef.addListenerForSingleValueEvent(eventListener);
























    }
}
