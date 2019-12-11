package com.android.giveandtake.Admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giveandtake.History.History;
import com.android.giveandtake.History.historyAdapter;
import com.android.giveandtake.R;
import com.android.giveandtake.home.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class History_Show_All extends AppCompatActivity{

    private DatabaseReference HistoryRef;
    private FirebaseDatabase firebaseDatabase;
    static private ArrayList<History> historyList;
    private RecyclerView _RecyclerView;
    private historyAdapter _Adapter;
    private FirebaseAuth mAuth;
    private RecyclerView.LayoutManager _LayoutManager;
    private String currentUserID;
    private Button filterAccpet;
    private boolean bool_filerAccept = false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__show__all);

        firebaseDatabase = FirebaseDatabase.getInstance();
        HistoryRef = firebaseDatabase.getReference("History");
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        filterAccpet = findViewById(R.id.filterAccpet);



        filterAccpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bool_filerAccept == true){
                    bool_filerAccept=false;
                }
                else{
                    bool_filerAccept=true;
                }
                createToShowhistory();
                updateView();

            }

        });


        createToShowhistory();

    }

    public void createToShowhistory() {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                historyList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String current_historyId = ds.child("current_historyId").getValue(String.class);
                    String history_giveOption = ds.child("history_giveOption").getValue(String.class);
                    String history_takeOption = ds.child("history_takeOption").getValue(String.class);
                    int imageResocure = ds.child("imageResocure").getValue(Integer.class);
                    String name_userPost = ds.child("name_userPost").getValue(String.class);
                    String name_userTrade = ds.child("name_userTrade").getValue(String.class);
                    String user_postedID = ds.child("user_postedID").getValue(String.class);
                    long time = ds.child("history_time").getValue(long.class);



                        History h = new History(imageResocure,name_userPost,name_userTrade,history_giveOption,history_takeOption,current_historyId,user_postedID,time);
                        historyList.add(h);

                }
//                if(bool_filerAccept == true) {
//
//                    if (city.equals(current_city)) {
//                        Post p = new Post(R.drawable.item_24dp, name, phone, city, give, take, freeText, courrentUser, PostID,time,Hours);
//                        PostsList.add(p);
//                        if(filterDate == true){
//                            Collections.sort(PostsList,comparator);
//                        }
//                        updateView();
//                    }
//                }


                updateView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        };
        HistoryRef.addListenerForSingleValueEvent(eventListener);


    }

    public void updateView(){
        _RecyclerView = findViewById(R.id.recyclerviewHistory_Admin);
        _RecyclerView.setHasFixedSize(true);
        _LayoutManager = new LinearLayoutManager(History_Show_All.this);
        _Adapter = new historyAdapter(historyList);
        _RecyclerView.setLayoutManager(_LayoutManager);
        _RecyclerView.setAdapter(_Adapter);
    }

}