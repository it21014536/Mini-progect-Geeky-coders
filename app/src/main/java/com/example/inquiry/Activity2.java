package com.example.inquiry;

import  android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class Activity2 extends AppCompatActivity {

    private EditText edit_title, edit_mess;
    private Button send_btn,req_btn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        edit_title = findViewById(R.id.edit_title);
        edit_mess = findViewById(R.id.edit_mess);
        send_btn = findViewById(R.id.send_btn2);
        req_btn = findViewById(R.id.req_btn);

        db = FirebaseFirestore.getInstance();
        req_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity2.this,Showfeedback.class));
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = edit_title.getText().toString();
                String message = edit_mess.getText().toString();
                String id = UUID.randomUUID().toString();

                saveToFireStore(id,Name,message);

            }
        });
    }

    private void saveToFireStore(String id, String Name, String message){

        if (!Name.isEmpty() &&  !message.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("Name" , Name);
            map.put("message" , message);




            db.collection("Feedback").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Activity2.this, "Thanks for your valuable feedback!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Activity2.this, "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
    }
}
