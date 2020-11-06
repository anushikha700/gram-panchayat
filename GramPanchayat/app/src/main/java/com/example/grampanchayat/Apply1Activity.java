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


public class Apply1Activity extends AppCompatActivity {

    private TextView txt_title;
    private TextView txt_desc;
    private TextView txt_category;
    private TextView txt_eligible;
    private Button apply;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply1);

        Intent intent =getIntent();
        // To get message from activity sent using intent
        String title_clicked=intent.getStringExtra("EXTRA_MESSAGE");

        Schemes schemes= new Schemes();
        String title_clicked2= schemes.getTitle();
        System.out.println("title clicked="+title_clicked2);

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
                Toast.makeText(Apply1Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        apply= (Button)findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Apply1Activity.this, Apply2Activity.class);

                //To send some string to other activity using intent
                 intent.putExtra("EXTRA_MESSAGE",title_clicked);
                startActivity(intent);
                // finish();
            }
        });



    }

    public void onApply(View view) {

        try{
            // To get text from textview in string
            // TextView tv2= (TextView)view;
            // String str= tv2.getText().toString();
            // System.out.println("title="+str);

            Intent intent = new Intent(Apply1Activity.this, Apply2Activity.class);

            //To send some string to other activity using intent
           // intent.putExtra("EXTRA_MESSAGE",title_clicked);
            startActivity(intent);
            // finish();

        } catch(Exception e) {
            Log.e("Error",String.valueOf(e));
        }


    }


}