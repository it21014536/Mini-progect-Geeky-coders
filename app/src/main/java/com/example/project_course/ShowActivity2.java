package com.example.project_course;

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

public class ShowActivity2 extends AppCompatActivity {
    private RecyclerView  recyclerview;
    private FirebaseFirestore db;
    private Myadapter2 myadapter2;
    private List<model2> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activity2);

        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        myadapter2 = new Myadapter2(this,list);
        recyclerview.setAdapter(myadapter2);

        ItemTouchHelper touchHelper= new ItemTouchHelper(new Touchhelper2(myadapter2));
        touchHelper.attachToRecyclerView(recyclerview);

        showData();//dude doesnt have this nowww
    }
    public void showData(){

        db.collection("Allclasses").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot snapshot :task.getResult()){

                            model2 model = new model2(snapshot.getString("id"),snapshot.getString("Title"),snapshot.getString("Subject"),snapshot.getString("Message"),snapshot.getString("Fee"));
                            list.add(model);
                        }

                        myadapter2.notifyDataSetChanged();
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowActivity2.this,"Opps something went wrong",Toast.LENGTH_LONG).show();
                    }
                });

    }
}