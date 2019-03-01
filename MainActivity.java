package com.jkcodes.loginform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textView;
    private Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            firebaseAuth = firebaseAuth.getInstance();
            textView = (TextView) findViewById(R.id.notice);
            btnlogout = findViewById(R.id.btn_logout1);

            if (firebaseAuth.getCurrentUser() == null){
                finish();
                startActivity(new Intent(this, loginActivity.class));
            }

            FirebaseUser userfirebase = firebaseAuth.getCurrentUser();


            textView.setText("Hi " + userfirebase.getEmail());



            btnlogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext(), loginActivity.class));
                }
            });

    }



}
