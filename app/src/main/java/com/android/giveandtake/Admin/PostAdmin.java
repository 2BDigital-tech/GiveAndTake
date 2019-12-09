package com.android.giveandtake.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giveandtake.R;
import com.android.giveandtake.home.ItemAdapter;
import com.android.giveandtake.home.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostAdmin extends AppCompatActivity {

    private RecyclerView _RecyclerView;
    private com.android.giveandtake.home.ItemAdapter _Adapter;
    private RecyclerView.LayoutManager _LayoutManager;
    private FirebaseDatabase firebaseDatabase;
    static private ArrayList<com.android.giveandtake.home.Post> PostsList;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    private String currentUserID;
    private FirebaseAuth mAuth;


    /// get the Buttoms ////


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_admin);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Posts");
        firebaseAuth = firebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();




        createToShowPosts();

    }





    public void DeletePost(String uid) {
        //myRef = firebaseDatabase.getReference("Posts");
        myRef.child(uid).orderByKey().equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();
                dataSnapshot.getRef().removeValue();
                updateView();
                createToShowPosts();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }



    public void createToShowPosts(){


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PostsList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("nameAsk").getValue(String.class);
                    String phone = ds.child("phoneAsk").getValue(String.class);
                    String city = ds.child("city").getValue(String.class);
                    String give = ds.child("give").getValue(String.class);
                    String take = ds.child("take").getValue(String.class);
                    String freeText = ds.child("freeText").getValue(String.class);
                    String courrentUser = ds.child("currentUserID").getValue(String.class);
                    String PostID = ds.child("postid").getValue(String.class);
                    long time=ds.child("time").getValue(long.class);
                    String Hours = ds.child("hours").getValue(String.class);




                    com.android.giveandtake.home.Post p = new Post(R.drawable.item_24dp, name,phone,city,give,take,freeText,courrentUser,PostID,time,Hours);
                    if(!PostsList.contains(p)){
                        PostsList.add(p);
                    }
                }

                updateView();

                _Adapter.setOnPostClickListener(new com.android.giveandtake.home.ItemAdapter.OnPostClickListener() {
                    @Override
                    public void onPostClick(final int position) {
                        PostsList.get(position);

                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(PostAdmin.this);
                        mBuilder.setTitle(PostsList.get(position).getNameAsk() +" - Post");
                        mBuilder.setMessage(PostsList.get(position).getfreeText()+"");
                        Log.e(": TAG7=",PostsList.get(position).getcurrentUserID()+" "+currentUserID);

                            mBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DeletePost(PostsList.get(position).getPostid());
                                    updateView();
                                }
                            });
                            mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();


                    }



                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        myRef.addListenerForSingleValueEvent(eventListener);

    }

    public void updateView(){
        _RecyclerView = findViewById(R.id.recyclerview2);
        _RecyclerView.setHasFixedSize(true);
        _LayoutManager = new LinearLayoutManager(PostAdmin.this);
        _Adapter = new ItemAdapter(PostsList);
        _RecyclerView.setLayoutManager(_LayoutManager);
        _RecyclerView.setAdapter(_Adapter);
    }

}

