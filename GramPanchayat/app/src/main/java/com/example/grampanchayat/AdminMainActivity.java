package com.example.grampanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminMainActivity extends AppCompatActivity {

    private Button signout;
    private FirebaseAuth auth;
    private Button admin_view_schemes;
    private Button add_schemes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

       // auth= FirebaseAuth.getInstance();
        signout= findViewById(R.id.admin_sign_out);
        admin_view_schemes= findViewById(R.id.admin_view_schemes);
        add_schemes= findViewById(R.id.add_schemes);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(AdminMainActivity.this,AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        admin_view_schemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminMainActivity.this,AdminSchemesActivity.class);
                startActivity(intent);
               // finish();
            }
        });

        System.out.println("I am ready to go");
        add_schemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("yea i am going");
                Intent intent =new Intent(AdminMainActivity.this,AddSchemesActivity.class);
                startActivity(intent);
               // finish();
            }
        });

    }


}