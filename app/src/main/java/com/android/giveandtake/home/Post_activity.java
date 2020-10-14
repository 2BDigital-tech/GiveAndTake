package com.android.giveandtake.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.giveandtake.Connect_Fragment;
import com.android.giveandtake.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class Post_activity extends AppCompatActivity implements View.OnClickListener {

    private Button createPost;
    private EditText freeText;
    private Button cancelBtn;
    private String courrentName;
    private String courrentPhone;
    private String couurentGive;
    private String courrentCity;
    private String courrentHours;
    private int pic;

    private String courrentTake;
    private String []giveOptions;
    private String []takeOptions;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private String currentUserID;
    private DatabaseReference RootRef;
    private String MoreInfoText;
    private Spinner mySpinner_take;
    private Spinner mySpinner_give;
    private Spinner mySpinner_hours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activity);

        createPost = findViewById(R.id.createPostbtn);

        freeText = findViewById(R.id.freeText);
        cancelBtn = findViewById(R.id.cancelBtn);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        RootRef = firebaseDatabase.getInstance().getReference();
        mySpinner_take = (Spinner) findViewById(R.id.spinner_take);
        mySpinner_give = (Spinner) findViewById(R.id.spinner_give);
        mySpinner_hours = (Spinner) findViewById(R.id.spinner_hours);



        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Post_activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Option1));
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Post_activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Hours));


        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner_take.setAdapter(myAdapter);
        mySpinner_give.setAdapter(myAdapter);
        mySpinner_hours.setAdapter(myAdapter2);


        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPostToDataBase();
                Intent i = new Intent(Post_activity.this, Connect_Fragment.class);
                startActivity(i);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Post_activity.this, Connect_Fragment.class);
                startActivity(i);
                finish();
            }
        });


    }

    public void registerPostToDataBase(){
        MoreInfoText = freeText.getText().toString().trim();
       courrentTake = (String) mySpinner_take.getSelectedItem().toString();
       couurentGive = (String) mySpinner_give.getSelectedItem().toString();
       courrentHours = (String) mySpinner_hours.getSelectedItem().toString();

        if (mySpinner_give.getSelectedItemPosition() < 0) {
            Toast.makeText(Post_activity.this, "Please select Give Option", Toast.LENGTH_LONG).show();
        }
         if (couurentGive.isEmpty()) {
            Toast.makeText(Post_activity.this, "Please select Take Option", Toast.LENGTH_LONG).show();
            return;
        }
        if (couurentGive.isEmpty()) {
            Toast.makeText(Post_activity.this, "Please select Take Option", Toast.LENGTH_LONG).show();
            return;
        }
        if (courrentHours.isEmpty()) {
            Toast.makeText(Post_activity.this, "Please select Hours Option", Toast.LENGTH_LONG).show();
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
                        Post p = new Post(R.drawable.item_24dp,courrentName,courrentPhone,courrentCity,couurentGive,courrentTake,MoreInfoText,currentUserID,postId,now,courrentHours);


                        FirebaseDatabase.getInstance().getReference("Posts").child(postId).setValue(p);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });

    }


    @Override
    public void onClick(View view) {

    }
}


