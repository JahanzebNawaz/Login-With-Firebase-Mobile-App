package com.jkcodes.loginform;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    Button loginbtn, btnregister;
    ProgressBar loginprogressBar;
    EditText input_email, input_pass;


    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        input_email = findViewById(R.id.login_mail);
        input_pass = findViewById(R.id.login_pass);

        progressDialog = new ProgressDialog(this);

        loginbtn = findViewById(R.id.btn_login);
        loginprogressBar = findViewById(R.id.progressBar);
        loginprogressBar.setVisibility(View.INVISIBLE);

        btnregister =  findViewById(R.id.btn_signup);


       if(firebaseAuth.getCurrentUser() != null){
           //
       }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

       btnregister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               register();
           }
       });

    }

    public  void userLogin(){
        String email = input_email.getText().toString().trim();
        String password = input_pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("loging to Portal");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });



    }

    public void register(){
        finish();
        startActivity(new Intent(this, registerActivity.class));
    }



}
