package com.example.grampanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {


    private  Button signout;
    private FirebaseAuth auth;
    private Button viewSchemes;
    private Button trackApplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth= FirebaseAuth.getInstance();
        signout= findViewById(R.id.sign_out);
        viewSchemes=findViewById(R.id.view_schemes);
        trackApplications=findViewById(R.id.track_applications);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent =new Intent(Main2Activity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewSchemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Main2Activity.this, SchemesActivity.class);
                startActivity(intent);
               // finish();
            }
        });

        trackApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Main2Activity.this, TrackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null) {
            Intent intent =new Intent(Main2Activity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }



}