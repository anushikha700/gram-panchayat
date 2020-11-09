package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    ArrayList<Applicants> list2;
    MyAdapter2 adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();
        String email= firebaseUser.getEmail();

        //Intent intent =getIntent();
        //String title_clicked=intent.getStringExtra("EXTRA_MESSAGE");

        Query query = FirebaseDatabase.getInstance().getReference().child("Applicants")
                .orderByChild("email")
                .equalTo(email);

        recyclerView= (RecyclerView)findViewById(R.id.myRecycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list2= new ArrayList<Applicants>();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Applicants a= dataSnapshot1.getValue(Applicants.class);
                    list2.add(a);

                }
                adapter2= new MyAdapter2(TrackActivity.this,list2);
                recyclerView.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TrackActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }






}




