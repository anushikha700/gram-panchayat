package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminViewActivity extends AppCompatActivity {

    private TextView txt_title;
    private TextView txt_desc;
    private TextView txt_category;
    private TextView txt_eligible;
    private Button viewApplicants;
    Schemes schemes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        Intent intent =getIntent();
        // To get message from activity sent using intent
        String title_clicked=intent.getStringExtra("EXTRA_MESSAGE");

        schemes= new Schemes();
        schemes.setTitle(title_clicked);
        String title_clicked2= schemes.getTitle();
        System.out.println("title clicked in appply1="+title_clicked2);

        txt_title=(TextView) findViewById(R.id.apply1_title);
        txt_desc=(TextView) findViewById(R.id.apply1_desc);
        txt_category=(TextView)findViewById(R.id.apply1_category);
        txt_eligible= (TextView)findViewById(R.id.apply1_eligible);

        // reference= FirebaseDatabase.getInstance().getReference().child("Global");

        // query for getting descriptiion where title is given
        Query query = FirebaseDatabase.getInstance().getReference().child("Schemes")
                .orderByChild("title")
                .equalTo(title_clicked);


        //


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                    Schemes schemes= postSnapshot.getValue(Schemes.class);
                    String title= schemes.getTitle();
                    String desc= schemes.getDesc();
                    String category= schemes.getCategory();
                    String eligible= schemes.getEligiblity();
                    txt_title.setText(title);
                    txt_desc.setText(desc);
                    txt_category.setText(category);
                    txt_eligible.setText(eligible);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminViewActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        viewApplicants= (Button)findViewById(R.id.viewApplications);
        viewApplicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminViewActivity.this, ViewApplicantsActivity.class);

                //To send some string to other activity using intent
                intent.putExtra("EXTRA_MESSAGE",title_clicked);
                startActivity(intent);
                // finish();
            }
        });



    }


}