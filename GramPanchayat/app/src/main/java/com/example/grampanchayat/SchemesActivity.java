package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SchemesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference reference;

   public TextView title;
    ArrayList<Schemes> list;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);

        // to display back button in action bar

      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ac29a")));
        actionBar.setDisplayHomeAsUpEnabled(true);
      */

        //

        reference= FirebaseDatabase.getInstance().getReference().child("Global");

        recyclerView= (RecyclerView)findViewById(R.id.myRecycler);
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
                adapter= new MyAdapter(SchemesActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SchemesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        title= (TextView)findViewById(R.id.scheme_title);






    }
// to send back to parent activity
   // @Override
    /*public boolean onNavigateUp()
    {
        finish();
        return true;
    }
   */

    public void onClick1(View view) {
        try{
            Intent intent = new Intent(SchemesActivity.this, Apply1Activity.class);
            startActivity(intent);
            finish();
        } catch(Exception e) {
            Log.e("Error",String.valueOf(e));
        }


    }


}