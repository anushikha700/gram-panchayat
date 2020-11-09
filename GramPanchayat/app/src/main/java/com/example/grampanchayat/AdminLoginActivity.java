package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    //private EditText loginEmailtext;
    private EditText loginPasswordtext;
    private Button loginbtn;
    private ProgressBar progressBar;
    private EditText loginUsername;
    private CheckBox checkBox;
     FirebaseAuth auth;
     FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        loginUsername= (EditText) findViewById(R.id.admin_login_username);
        loginPasswordtext=(EditText) findViewById(R.id.admin_login_password);
        loginbtn=(Button) findViewById(R.id.admin_login_button);

        checkBox=findViewById(R.id.admin_login_checkbox);
        progressBar=findViewById(R.id.admin_login_progressBar);




        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    loginPasswordtext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    loginPasswordtext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    progressBar.setVisibility(View.VISIBLE);

                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

                    databaseReference.child("admin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String loginUser=loginUsername.getText().toString();
                            String loginPass=loginPasswordtext.getText().toString();
                            System.out.println("user="+loginUser);
                            System.out.println("pass="+loginPass);

                            if(snapshot.child("username").child(loginUser).exists())
                            {
                                System.out.println("enterd");
                               if(snapshot.child(loginPass).exists()){
                                   Intent intent =new Intent(AdminLoginActivity.this, AdminMainActivity.class);
                                   startActivity(intent);
                                   finish();
                               }
                               else
                               {
                                   Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                               }

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Incorrect Username",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });






            }
        });



    }



}