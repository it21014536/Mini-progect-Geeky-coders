package com.example.offers;

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
    private List<Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
   // private Context context;
    public MyAdapter(ShowActivity activity, List<Model> mList){
        this.activity = activity;
        this.mList = mList;

    }

    public void updateData(int position){
        Model item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", item.getId());
        bundle.putString("sub", item.getSubject());
        bundle.putString("tut", item.getTutor());
        bundle.putString("amo", item.getAmount());
        bundle.putString("des", item.getDescription());

        Intent intent = new Intent(activity, MainActivity.class);
        //intent.putExtra("ITEM",item);
      //  Log.d("TAG", "updateData: "+item.getAmount());
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void deleteData(int position){
        Model item = mList.get(position);
        db.collection("Offers2").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Data not deleted", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent, false);
        return new MyViewHolder(v,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.subject.setText(mList.get(position).getSubject());
        holder.tutor.setText(mList.get(position).getTutor());
        holder.amount.setText(mList.get(position).getAmount());
        holder.desc.setText(mList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView subject,tutor,amount,desc;
        public MyViewHolder(@NonNull View itemView,ShowActivity act) {

            super(itemView);

            subject = itemView.findViewById(R.id.name);
            tutor = itemView.findViewById(R.id.tutor);
            amount = itemView.findViewById(R.id.Amount);
            desc = itemView.findViewById(R.id.description);
          //  itemView.setOnClickListener(new View.OnClickListener() {


        }


    }
}
