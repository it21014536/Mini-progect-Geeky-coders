package com.example.inquiry;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ShowActivity activity;
    private List<com.example.inquiry.Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyAdapter(ShowActivity activity , List<com.example.inquiry.Model> mList){
        this.activity = activity;
        this.mList = mList;


    }
//-----------------update------------------------------------------

    public void updateData(int position){
        com.example.inquiry.Model item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId" , item.getId());
        bundle.putString("uName" , item.getName());
        bundle.putString("uEmail" , item.getEmail());
        bundle.putString("uPhone" , item.getPhone());
        bundle.putString("uSubject" , item.getSubject());
        bundle.putString("uMessage" , item.getMessage());
        Intent intent = new Intent(activity , com.example.inquiry.MainActivityfb.class);
        intent.putExtra( "item",item);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    //----------------delete---------------------------------------

    public void deleteData(int position){
        com.example.inquiry.Model item = mList.get(position);
        db.collection("Inquiry").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted !!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

 //--------------------------delete close------------------------------------------------------------
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Name.setText(mList.get(position).getName());
        holder.Email.setText(mList.get(position).getEmail());
        holder.phone.setText(mList.get(position).getPhone());
        holder.subject.setText(mList.get(position).getSubject());
        holder.message.setText(mList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name,Email,phone,subject,message;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.Name_text);
            Email= itemView.findViewById(R.id.Email_email);
            phone = itemView.findViewById(R.id.phone_text);
            subject= itemView.findViewById(R.id.subject_text);
            message = itemView.findViewById(R.id.message_text);

        }
    }
}