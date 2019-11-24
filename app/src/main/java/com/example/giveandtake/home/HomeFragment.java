package com.example.giveandtake.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giveandtake.Connect_Fragment;
import com.example.giveandtake.LoginActivity;
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

    private RecyclerView _RecyclerView;
    private ItemAdapter _Adapter;
    private RecyclerView.LayoutManager _LayoutManager;
    private FirebaseDatabase firebaseDatabase;
    static private ArrayList<Post> PostsList;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private Button addItem;
    private View root;
    private Button giveBtn;
    private Button takeBtn;
    private String currentUserID;
    private FirebaseAuth mAuth;


    /// get the Buttoms ////


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Posts");
        firebaseAuth = firebaseAuth.getInstance();

        //////////////////// Create Dialog ///////////////////
        addItem = root.findViewById(R.id.addItem);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        giveBtn = root.findViewById(R.id.giveBtn);
        takeBtn = root.findViewById(R.id.takeBtn);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Post_activity.class);
                startActivity(i);
               // openDialog();
            }

        });

        createToShowPosts();

        return root;

    }

//    public void buildRecyclerView(){
//
//
//    }

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




                    Post p = new Post(R.drawable.item_24dp, name,phone,city,give,take,freeText,courrentUser,PostID);
                    if(!PostsList.contains(p)){
                        PostsList.add(p);
                    }
                }

                updateView();

                _Adapter.setOnPostClickListener(new ItemAdapter.OnPostClickListener() {
                    @Override
                    public void onPostClick(final int position) {
                        PostsList.get(position);

                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        mBuilder.setTitle(PostsList.get(position).getNameAsk() +" - Post");
                        mBuilder.setMessage(PostsList.get(position).getfreeText()+"");
                        Log.e(": TAG7=",PostsList.get(position).getcurrentUserID()+" "+currentUserID);

                        if(PostsList.get(position).getcurrentUserID().equals(currentUserID)){

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
                        }else{
                            mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }

                            });
                            mBuilder.setNegativeButton("Trade", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    // Send to New Activite That Get all user Trades.
                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();
                        }
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
        _RecyclerView = root.findViewById(R.id.recyclerview);
        _RecyclerView.setHasFixedSize(true);
        _LayoutManager = new LinearLayoutManager(getContext());
        _Adapter = new ItemAdapter(PostsList);
        _RecyclerView.setLayoutManager(_LayoutManager);
        _RecyclerView.setAdapter(_Adapter);
    }

}

