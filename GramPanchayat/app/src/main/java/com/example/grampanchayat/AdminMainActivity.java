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
    private Button view_applications;
    private Button add_schemes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        auth= FirebaseAuth.getInstance();
        signout= findViewById(R.id.admin_sign_out);
        admin_view_schemes= findViewById(R.id.admin_view_schemes);
        view_applications=findViewById(R.id.view_applications);
        add_schemes= findViewById(R.id.add_schemes);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent =new Intent(AdminMainActivity.this,AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null) {
            Intent intent =new Intent(AdminMainActivity.this,AdminLoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}