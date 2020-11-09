package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminSchemesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private TextView schemeTitle;

    ArrayList<Schemes> list;
    AdminMyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schemes);

        reference= FirebaseDatabase.getInstance().getReference().child("Schemes");

        recyclerView= (RecyclerView)findViewById(R.id.AdminRecycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<Schemes>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Schemes s= dataSnapshot1.getValue(Schemes.class);
                    list.add(s);
                }
                adapter= new AdminMyAdapter(AdminSchemesActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminSchemesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void onAdminClick(View view) {

        try{
            // To get text from textview in string
            TextView tv2= (TextView)view;
            String str= tv2.getText().toString();
            System.out.println("title in schemes activity="+str);

            Intent intent = new Intent(AdminSchemesActivity.this, AdminViewActivity.class);

            //To send some string to other activity using intent
            intent.putExtra("EXTRA_MESSAGE",str);
            startActivity(intent);
            // finish();

        } catch(Exception e) {
            Log.e("Error",String.valueOf(e));
        }


    }

}
