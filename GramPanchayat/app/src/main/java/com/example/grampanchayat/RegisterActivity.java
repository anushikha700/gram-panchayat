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

public class RegisterActivity extends AppCompatActivity {

    private EditText register_email;
    private  EditText register_password;
    private EditText register_confirm_password;
    private Button regbtn;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    private Button reg_login_btn;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_confirm_password = findViewById(R.id.register_confirm_password);
        regbtn = findViewById(R.id.register_button);
        checkBox=findViewById(R.id.register_checkbox);
        progressBar=findViewById(R.id.register_progressBar);
        reg_login_btn=findViewById(R.id.reg_login_button);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    register_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    register_confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    register_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    register_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        regbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email= register_email.getText().toString().trim();
                String password= register_password.getText().toString();
                String confirm_pass= register_confirm_password.getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(confirm_pass)) {
                    if (password.equals(confirm_pass)) {
                        progressBar.setVisibility(View.VISIBLE);
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sendtoMain();
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show();
                                }

                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });

        reg_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
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


    private void sendtoMain() {

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

    }





}