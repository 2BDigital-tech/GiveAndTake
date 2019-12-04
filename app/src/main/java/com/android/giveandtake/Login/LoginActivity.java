package com.android.giveandtake.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.giveandtake.Admin.AdminConnect;
import com.android.giveandtake.Connect_Fragment;
import com.android.giveandtake.R;
import com.android.giveandtake.Start_Application;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "simplifiedcoding";
    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;
    //And also a Firebase Auth object
    FirebaseAuth mAuth;
    private EditText emailboxLogin;
    private  EditText passwordboxLogin;
    private  Button buttonLogin;
    private  Button ReturnBtn, forgotPassword;
    private int verif_is_exist;
    private FirebaseAuth firebaseAuth;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        emailboxLogin = (EditText)findViewById(R.id.emailboxLogin);
        passwordboxLogin = (EditText)findViewById(R.id.passwordboxLogin);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        firebaseAuth = firebaseAuth.getInstance();
        ReturnBtn = (Button)findViewById(R.id.returnLoginbtn);
        forgotPassword = (Button)findViewById(R.id.forgotpassword);

        ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Start_Application.class);
                startActivity(i);
            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot=new Intent(LoginActivity.this,Forgot_code.class);
                startActivity(forgot);
            }
        });




        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...", true);
                (firebaseAuth.signInWithEmailAndPassword(emailboxLogin.getText().toString(), passwordboxLogin.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {

                                    String  currentUserID="nDk5cYyLV6Vjpt858AQDF1VNClr2";

                                    if(firebaseAuth.getCurrentUser().getUid().equals(currentUserID)) {
                                        startActivity(new Intent (LoginActivity.this, AdminConnect.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                        Toast.makeText(LoginActivity.this, "Administrator Connector", Toast.LENGTH_LONG).show();
                                        finish();



                                    }else {


                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(LoginActivity.this, Connect_Fragment.class);
                                        i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                        startActivity(i);
                                        finish();
                                    }
                                } else {
                                    Log.e("onComplete: Failed=", task.getException().getMessage());
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Now we will attach a click listener to the sign_in_button
        //and inside onClick() method we are calling the signIn() method that will open
        //google sign in intent
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



    }

    protected void onStart() {
        super.onStart();

        //if the user is already signed in
        //we will close this activity
        //and take the user to profile activity
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, Connect_Fragment.class));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            final String uid=user.getUid().toString();

                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
                            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                    if(dataSnapshot.hasChild(uid)==true){
                                        startActivity(new Intent(LoginActivity.this,Connect_Fragment.class));
                                    finish();
                                    }
                                    else {
                                        startActivity(new Intent(LoginActivity.this,Account_Google.class));
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(LoginActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    //this method is called on click
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }





}
