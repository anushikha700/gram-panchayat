package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private ProgressBar progressBar;

    DatabaseReference mDatabase;
   // DatabaseReference dbAppliedFor;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Schemes schemes;
    String sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply2);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

        Intent intent =getIntent();
        String title_clicked=intent.getStringExtra("EXTRA_MESSAGE");

        fname = (EditText)findViewById(R.id.apply_fname);
        lname = findViewById(R.id.apply_lname);
        email = findViewById(R.id.login_email);
        phone = findViewById(R.id.apply_phone);
        country = findViewById(R.id.apply_country);
        city = findViewById(R.id.apply_city);
        pincode = findViewById(R.id.apply_pincode);
        house_no = findViewById(R.id.apply_house);
        submit = findViewById(R.id.submit);
        progressBar=findViewById(R.id.apply2_progressBar);

        email.setText(firebaseUser.getEmail());
        email.setEnabled(false);


        schemes=new Schemes();
        schemes.setTitle(title_clicked);
        String sc_title= schemes.getTitle();
        System.out.println("Sc_title:"+sc_title);

     /*   mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Schemes").orderByChild("title").equalTo(sc_title).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childSnapshot: snapshot.getChildren()) {
                    sid = childSnapshot.getKey();
                    System.out.println("sid=" + sid);
                    System.out.println("here calculatingn sid");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error.............");
            }
        });

*/
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println("sent data");
                addApplicant(title_clicked);
                Intent intent = new Intent(Apply2Activity.this, TrackActivity.class);

                //To send some string to other activity using intent
                //intent.putExtra("EXTRA_MESSAGE",title_clicked);
                startActivity(intent);
            }
        });

    }

    private void addApplicant(String applied_for){
        String fname1= fname.getText().toString().trim();
        String lname1= lname.getText().toString().trim();
        String email1= email.getText().toString().trim();
        //System.out.println("email:"+email1);
        String phone1= phone.getText().toString();
        String country1= country.getText().toString();
        String city1= city.getText().toString();
        String pincode1= pincode.getText().toString();
        String house_no1= house_no.getText().toString();
        String status="Pending";



        if(TextUtils.isEmpty(fname1)||TextUtils.isEmpty(lname1)||TextUtils.isEmpty(email1)||TextUtils.isEmpty(phone1)||TextUtils.isEmpty(country1)||TextUtils.isEmpty(city1)||TextUtils.isEmpty(pincode1)||TextUtils.isEmpty(house_no1)){
            Toast.makeText(this, "Cannot Submit Form (Fields are empty)", Toast.LENGTH_LONG).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Applicants");
            String uid= mDatabase.push().getKey();
            System.out.println("now i will set it");
            Applicants applicants= new Applicants(email1, fname1, lname1, phone1, country1, city1, pincode1, house_no1,applied_for,status,uid);
            mDatabase.child(uid).setValue(applicants);
      //      addAppliedfor(uid);



        }
            Toast.makeText(this, "Form Submitted", Toast.LENGTH_LONG).show();
        }



    }

