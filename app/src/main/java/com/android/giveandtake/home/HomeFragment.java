package com.android.giveandtake.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.android.giveandtake.Center.Trade;
import com.android.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView _RecyclerView;
    private ItemAdapter _Adapter;
    private RecyclerView.LayoutManager _LayoutManager;
    private FirebaseDatabase firebaseDatabase;
    static private ArrayList<Post> PostsList;
    private DatabaseReference RootRef;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private DatabaseReference myUsers;
    private Button addItem;
    private View root;
    private Button giveBtn;
    private Button takeBtn;
    private Button filterCitybtn;
    private Button filterOptionbtn;
    private String currentUserID;
    private FirebaseAuth mAuth;
    private String name;
    private String phone;
    private String city;
    private String give;
    private String take;
    private String []giveOptions;
    private String PostID;
    static private String current_city;
    private String courrentUser;
    static private String couurentGive;

    private String freeText;
    private boolean filerCity = false;
    private boolean filerOption = false;


    /// get the Buttoms ////


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Posts");
        myUsers = firebaseDatabase.getReference("Users");
        RootRef = firebaseDatabase.getInstance().getReference();
        firebaseAuth = firebaseAuth.getInstance();
        //////////////////// Create Dialog ///////////////////
        addItem = root.findViewById(R.id.addItem);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        giveBtn = root.findViewById(R.id.giveBtn);
        takeBtn = root.findViewById(R.id.takeBtn);
        filterCitybtn = root.findViewById(R.id.filterCity);
        filterOptionbtn = root.findViewById(R.id.filterOption);
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

        filterCitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filerCity = true;
                createToShowPosts();
                updateView();

            }

        });
        filterOptionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveOptions = getResources().getStringArray(R.array.Option1);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setSingleChoiceItems(giveOptions, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        couurentGive = giveOptions[which];
                        filerOption = true;

                        dialog.dismiss();
                        createToShowPosts();
                        updateView();
                    }

                });

                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }

        });

        createToShowPosts();

        return root;

    }


    public void DeletePost(String uid) {
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
                    name = ds.child("nameAsk").getValue(String.class);
                    phone = ds.child("phoneAsk").getValue(String.class);
                    city = ds.child("city").getValue(String.class);
                    give = ds.child("give").getValue(String.class);
                    take = ds.child("take").getValue(String.class);
                    freeText = ds.child("freeText").getValue(String.class);
                    courrentUser = ds.child("currentUserID").getValue(String.class);
                    PostID = ds.child("postid").getValue(String.class);

                    myUsers.child(currentUserID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            current_city = dataSnapshot.child("city").getValue(String.class);

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                    Log.e(": TAG17=", filerOption+"");


                    if(filerCity == true && filerOption == false) {

                        if (city.equals(current_city)) {
                            Post p = new Post(R.drawable.item_24dp, name, phone, city, give, take, freeText, courrentUser, PostID);
                            PostsList.add(p);
                            updateView();
                        }
                    }
                    else if(filerCity == true && filerOption == true) {

                        if (city.equals(current_city) && give.equals(couurentGive)) {
                            Post p = new Post(R.drawable.item_24dp, name, phone, city, give, take, freeText, courrentUser, PostID);
                            PostsList.add(p);
                            updateView();
                        }
                    }
                    else if(filerCity == false && filerOption == true){

                        if(give.equals(couurentGive)){
                            Post p = new Post(R.drawable.item_24dp, name,phone,city,give,take,freeText,courrentUser,PostID);
                            PostsList.add(p);
                            updateView();
                        }

                    }else{
                        Post p = new Post(R.drawable.item_24dp, name,phone,city,give,take,freeText,courrentUser,PostID);
                        PostsList.add(p);

                    }



                }

                updateView();

                _Adapter.setOnPostClickListener(new ItemAdapter.OnPostClickListener() {
                    @Override
                    public void onPostClick(final int position) {
                        PostsList.get(position);

                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        mBuilder.setTitle("Post ID: "+PostsList.get(position).getPostid());
                        mBuilder.setMessage(PostsList.get(position).getfreeText()+"\n");

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

                                        RootRef.child("Users").child(currentUserID)
                                                .addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot ds) {
                                                        String postId = RootRef.push().getKey();

                                                        name = ds.child("name").getValue(String.class);
                                                        phone = ds.child("phone").getValue(String.class);
                                                        city = ds.child("city").getValue(String.class);
                                                        final Trade trade = new Trade(
                                                                R.drawable.black2people,
                                                                currentUserID,
                                                                PostsList.get(position).getPostid(),
                                                                postId,
                                                                PostsList.get(position).getcurrentUserID(),
                                                                PostsList.get(position).getNameAsk(),
                                                                name,
                                                                PostsList.get(position).getGive(),
                                                                PostsList.get(position).getTake(),
                                                                phone,
                                                                city,
                                                                freeText
                                                                );

                                                        FirebaseDatabase.getInstance().getReference("Trades").child(postId).setValue(trade);
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }

                                                });

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

