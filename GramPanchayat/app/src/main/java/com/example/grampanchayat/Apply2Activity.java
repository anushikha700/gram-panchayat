package com.example.grampanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Apply2Activity extends AppCompatActivity {

    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText phone;
    private EditText country;
    private EditText city;
    private EditText pincode;
    private EditText house_no;
    private Button submit;

    DatabaseReference dbApplicants;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply2);

        dbApplicants = FirebaseDatabase.getInstance().getReference().child("Applicants");
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

        Schemes schemes= new Schemes();
        String title= schemes.getTitle();
        System.out.println("title:"+title);

        fname = (EditText)findViewById(R.id.apply_fname);
        lname = findViewById(R.id.apply_lname);
        email = findViewById(R.id.login_email);
        phone = findViewById(R.id.apply_phone);
        country = findViewById(R.id.apply_country);
        city = findViewById(R.id.apply_city);
        pincode = findViewById(R.id.apply_pincode);
        house_no = findViewById(R.id.apply_house);
        submit = findViewById(R.id.submit);

        email.setText(firebaseUser.getEmail());
        email.setEnabled(false);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addApplicant();
            }
        });

    }

    private void addApplicant(){
        String fname1= fname.getText().toString().trim();
        String lname1= lname.getText().toString().trim();
        String email1= email.getText().toString().trim();
        //System.out.println("email:"+email1);
        String phone1= phone.getText().toString();
        String country1= country.getText().toString();
        String city1= city.getText().toString();
        String pincode1= pincode.getText().toString();
        String house_no1= house_no.getText().toString();
        String app_status= null;

        if(TextUtils.isEmpty(fname1)||TextUtils.isEmpty(lname1)||TextUtils.isEmpty(email1)||TextUtils.isEmpty(phone1)||TextUtils.isEmpty(country1)||TextUtils.isEmpty(city1)||TextUtils.isEmpty(pincode1)||TextUtils.isEmpty(house_no1)){
            Toast.makeText(this, "Cannot Submit Form (Fields are empty)", Toast.LENGTH_LONG).show();
        }
        else
        {


            String id= dbApplicants.push().getKey();
            Applicants applicants= new Applicants(email1, fname1, lname1, phone1, country1, city1, pincode1, house_no1,app_status);

            dbApplicants.child(id).setValue(applicants);
            Toast.makeText(this, "Form Submitted", Toast.LENGTH_LONG).show();
        }
    }

}