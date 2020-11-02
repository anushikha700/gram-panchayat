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

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmailtext;
    private EditText loginPasswordtext;
    private Button loginbtn;
    private ProgressBar progressBar;
    private Button loginregisterbtn;
    private CheckBox checkBox;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        loginEmailtext=findViewById(R.id.login_email);
        loginPasswordtext=findViewById(R.id.login_password);
        loginbtn=findViewById(R.id.login_button);
        loginregisterbtn=findViewById(R.id.log_register_button);
        checkBox=findViewById(R.id.login_checkbox);
        progressBar=findViewById(R.id.login_progressBar);


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
                String loginEmail=loginEmailtext.getText().toString().trim();
                String loginPass=loginPasswordtext.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginPass) ){
                    progressBar.setVisibility(View.VISIBLE);

                    auth.signInWithEmailAndPassword(loginEmail,loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendtoMain();
                            }
                            else{
                                String error= task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"Error: "+ error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }
            }
        });

        loginregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null) {
           sendtoMain();
        }
    }

    private void sendtoMain(){
        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}