package com.example.inquiry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapterfb extends RecyclerView.Adapter<MyAdapterfb.MyViewHolder>{

    private Showfeedback activity;
    private List<Modelfb> modelfbList;


    public MyAdapterfb(Showfeedback activity, List<Modelfb> modelfbList){

        this.activity = activity;
        this.modelfbList = modelfbList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.feedbackitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.Name.setText(modelfbList.get(position).getName());
        holder.message.setText(modelfbList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return modelfbList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name,message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.Name_text);
            message = itemView.findViewById(R.id.message_text);
        }
    }
}
