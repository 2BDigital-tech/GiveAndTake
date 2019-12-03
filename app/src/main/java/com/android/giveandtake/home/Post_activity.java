package com.android.giveandtake.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.giveandtake.Connect_Fragment;
import com.android.giveandtake.Login.RegistrationActivity;
import com.android.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class Post_activity extends AppCompatActivity {
    private Button giveBtn;
    private Button takeBtn;
    private Button createPost;
    private EditText freeText;
    private Button cancelBtn;
    private String courrentName;
    private String courrentPhone;
    private String couurentGive;
    private String courrentCity;

    private String courrentTake;
    private String []giveOptions;
    private String []takeOptions;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private String currentUserID;
    private DatabaseReference RootRef;
    private String MoreInfoText;
    private Spinner mySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activity);

        createPost = findViewById(R.id.createPostbtn);
        giveBtn = findViewById(R.id.giveBtn);
        takeBtn = findViewById(R.id.takeBtn);
        freeText = findViewById(R.id.freeText);
        cancelBtn = findViewById(R.id.cancelBtn);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        RootRef = firebaseDatabase.getInstance().getReference();
        mySpinner = (Spinner) findViewById(R.id.cityspinner);




        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPostToDataBase();
                Intent i = new Intent(Post_activity.this, Connect_Fragment.class);
                startActivity(i);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Post_activity.this, Connect_Fragment.class);
                startActivity(i);
            }
        });


        giveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveOptions = getResources().getStringArray(R.array.Option1);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Post_activity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setSingleChoiceItems(giveOptions, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        couurentGive = giveOptions[which];
                        dialog.dismiss();
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
        takeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeOptions = getResources().getStringArray(R.array.Option2);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Post_activity.this);
                mBuilder.setTitle("Choose From Options:");
                mBuilder.setSingleChoiceItems(takeOptions, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        courrentTake = takeOptions[which];

                        dialog.dismiss();
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



    }

    public void registerPostToDataBase(){
        MoreInfoText = freeText.getText().toString().trim();

        if (courrentTake.isEmpty()) {
            Toast.makeText(Post_activity.this, "Please select Give Option", Toast.LENGTH_LONG).show();
        }
         if (courrentTake.isEmpty()) {
            Toast.makeText(Post_activity.this, "Please select Take Option", Toast.LENGTH_LONG).show();
            return;
        }
        RootRef.child("Users").child(currentUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String retrieveUserName = dataSnapshot.child("name").getValue().toString();
                        courrentName = retrieveUserName;
                        String retrieveUserPhone = dataSnapshot.child("phone").getValue().toString();
                        courrentPhone = retrieveUserPhone;
                        String retrieveCity = dataSnapshot.child("city").getValue().toString();
                        courrentCity = retrieveCity;
                        long now= new Date().getTime();
                        String postId = RootRef.push().getKey();
                       // LocalDateTime test= LocalDateTime.now();
                        Post p = new Post(R.drawable.item_24dp,courrentName,courrentPhone,courrentCity,couurentGive,courrentTake,MoreInfoText,currentUserID,postId,now);


                        FirebaseDatabase.getInstance().getReference("Posts").child(postId).setValue(p);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });

    }



    }


