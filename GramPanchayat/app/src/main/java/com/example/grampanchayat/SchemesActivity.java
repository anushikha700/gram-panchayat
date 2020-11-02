package com.example.grampanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SchemesActivity extends AppCompatActivity {
    private RecyclerView schemeList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("/Global");
        mDatabase.keepSynced(true);

        schemeList=(RecyclerView)findViewById(R.id.recycler_view);
        schemeList.setHasFixedSize(true);
        schemeList.setLayoutManager(new LinearLayoutManager(schemeList.getContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Schemes> options =
                new FirebaseRecyclerOptions.Builder<Schemes>()
                        .setQuery(mDatabase, Schemes.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Schemes, SchemesViewHolder>(options) {
            @Override
            public SchemesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.scheme_row, parent, false);

                return new SchemesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(SchemesViewHolder viewHolder, int position, Schemes model) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
            }
        };
        schemeList.setAdapter(adapter);
    }

    public static class SchemesViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public SchemesViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

        }
        public void setTitle(String title)
        {
            TextView scheme_title= (TextView)mView.findViewById(R.id.scheme_title);
            scheme_title.setText(title);
        }
        public void setDesc(String desc)
        {
            TextView scheme_desc= (TextView)mView.findViewById(R.id.scheme_desc);
            scheme_desc.setText(desc);
        }


    }
}