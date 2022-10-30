package com.example.inquiry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class Activity3 extends AppCompatActivity {

    private RatingBar rb;
    private Button send_btn2;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

         rb = findViewById(R.id.rb);
        send_btn2 = findViewById(R.id.send_btn2);

        db = FirebaseFirestore.getInstance();

        send_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               rb.getRating();
                String id = UUID.randomUUID().toString();
                saveToFireStore(id);


            }
        });
    }

    private void saveToFireStore(String id){


            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);




            db.collection("Ratings").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Activity3.this, "Thanks for sharing your rating with us!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Activity3.this, "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }


