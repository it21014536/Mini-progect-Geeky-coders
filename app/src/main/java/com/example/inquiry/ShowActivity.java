package com.example.inquiry;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private com.example.inquiry.MyAdapter adapter;
    private List<com.example.inquiry.Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db= FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new com.example.inquiry.MyAdapter(this,list);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper touchHelper = new ItemTouchHelper(new com.example.inquiry.TouchHelper(adapter));
        touchHelper.attachToRecyclerView(recyclerView);
        showData();

    }

    public void showData(){

        db.collection("Inquiry").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        for(DocumentSnapshot snapshot :task.getResult()){


                            com.example.inquiry.Model model = new com.example.inquiry.Model(snapshot.getString("id") , snapshot.getString("Name") , snapshot.getString("Email"),snapshot.getString("phone")
                            ,snapshot.getString("subject"),snapshot.getString("message"));
                            list.add(model);

                        }

                        adapter.notifyDataSetChanged();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowActivity.this, "Oops ... something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

