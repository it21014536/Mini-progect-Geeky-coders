package com.example.project_course;

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

public class Myadapter2 extends RecyclerView .Adapter<Myadapter2.MyViewholder>{
    private ShowActivity2 activity2;
    private List<model2> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Myadapter2(ShowActivity2 activity2, List<model2>mList){
        this.activity2=activity2;
        this.mList=mList;
    }

    public void updateData(int position){
        model2 item=mList.get(position);
        Bundle bundle= new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uTitle", item.getTitle());
        bundle.putString("uSubject", item.getSub());
        bundle.putString("uFee", item.getFee());
        bundle.putString("uDesc", item.getDesc());
        Intent intent = new Intent(activity2, MainActivity22.class);
        intent.putExtras(bundle);
        activity2.startActivity(intent);


    }

    public void deleteData(int position){
        model2 item=mList.get(position);
        db.collection("Allclasses").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity2,"Deleted successfully",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(activity2,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity2.showData();
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v= LayoutInflater.from(activity2).inflate(R.layout.item2,parent,false);
         return  new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
    holder.title.setText(mList.get(position).getTitle());
    holder.sub.setText(mList.get(position).getSub());
    holder.fee.setText(mList.get(position).getFee());
    holder.desc.setText(mList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
        TextView title,sub,fee,desc;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_text);
            sub=itemView.findViewById(R.id.Subject_text);
            fee=itemView.findViewById(R.id.Fee_text);
            desc=itemView.findViewById(R.id.Desc_text);

        }
    }
}
