    package com.example.giveandtake;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class LoginActivity extends AppCompatActivity {

//shimon test git
    private EditText emailboxLogin;
    private  EditText passwordboxLogin;
    private  Button buttonLogin;
    private  Button ReturnBtn;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailboxLogin = (EditText)findViewById(R.id.emailboxLogin);
        passwordboxLogin = (EditText)findViewById(R.id.passwordboxLogin);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        firebaseAuth = firebaseAuth.getInstance();
        ReturnBtn = (Button)findViewById(R.id.returnLoginbtn);


        ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...", true);
                (firebaseAuth.createUserWithEmailAndPassword(emailboxLogin.getText().toString(), passwordboxLogin.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LoginActivity.this, FeedActivity.class);
                                    i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);
                                } else {
                                    Log.e("onComplete: Failed=", task.getException().getMessage());
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });



    }
}
