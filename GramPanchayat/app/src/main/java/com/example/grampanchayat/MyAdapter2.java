package com.example.grampanchayat;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    Context context;
    ArrayList<Applicants>applicants;


    public MyAdapter2(Context c,ArrayList<Applicants>a)
    {
        context=c;
        applicants=a;

    }


    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapter2.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.application_row,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {
        holder.aid2.setText(applicants.get(position).getAid());
        holder.status2.setText(applicants.get(position).getStatus());
        holder.apply_title2.setText(applicants.get(position).getApplied_for());
    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView aid2,status2,apply_title2;
        public MyViewHolder(View itemView) {
            super(itemView);
            aid2= (TextView)itemView.findViewById(R.id.aid2);
            status2=(TextView)itemView.findViewById(R.id.status2);
            apply_title2=(TextView)itemView.findViewById(R.id.apply_title2);
        }
    }
}
