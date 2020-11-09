package com.example.grampanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSchemesActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private EditText category;
    private  EditText eligiblity;
    private Button btn;


    private ProgressBar progressBar;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schemes);

        System.out.println("i came here");
        title= findViewById(R.id.sc_title);
        desc= findViewById(R.id.sc_desc);
        category= findViewById(R.id.sc_category);
        eligiblity= findViewById(R.id.sc_eligiblity);
        btn= findViewById(R.id.add_scheme);
        progressBar= findViewById(R.id.sc_progressBar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSchemes();
                Intent intent = new Intent(AddSchemesActivity.this, AdminSchemesActivity.class);

                startActivity(intent);
            }
        });

    }

    private void addSchemes(){
        String title1= title.getText().toString();
        String desc1= desc.getText().toString();
        String category1= category.getText().toString();
        String eligiblity1= eligiblity.getText().toString();




        if(TextUtils.isEmpty(title1)||TextUtils.isEmpty(desc1)||TextUtils.isEmpty(category1)||TextUtils.isEmpty(eligiblity1)){
            Toast.makeText(this, "Cannot Submit (Fields are empty)", Toast.LENGTH_LONG).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Schemes");
            String sid= mDatabase.push().getKey();
            System.out.println("now i will set it");
            Schemes schemes= new Schemes(title1,desc1,category1,eligiblity1);
            mDatabase.child(sid).setValue(schemes);




        }
        Toast.makeText(this, "Form Submitted", Toast.LENGTH_LONG).show();
    }

}