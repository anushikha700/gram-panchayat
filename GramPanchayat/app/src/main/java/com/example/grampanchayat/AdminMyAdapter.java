package com.example.grampanchayat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminMyAdapter extends RecyclerView.Adapter<AdminMyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Schemes> schemes;

    public AdminMyAdapter(Context c, ArrayList<Schemes>s)
    {
        context =c;
        schemes=s;
    }

    @NonNull
    @Override
    public AdminMyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminMyAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_scheme_row,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminMyAdapter.MyViewHolder holder, int position) {
        holder.title.setText(schemes.get(position).getTitle());
        holder.desc.setText(schemes.get(position).getDesc());

    }


    @Override
    public int getItemCount() {
        return schemes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, desc;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            title= (TextView)itemView.findViewById(R.id.scheme_title);
            desc= (TextView)itemView.findViewById(R.id.scheme_desc);
        }

    }


}
