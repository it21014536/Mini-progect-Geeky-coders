package com.example.project_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class page1 extends AppCompatActivity {
    private Button Grade12;
    private Button Addclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        Grade12 = findViewById(R.id.btn5);
        Addclass=findViewById(R.id.btn6);

        Grade12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new  Intent(page1.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new  Intent(page1.this,ShowActivity2.class);
                startActivity(intent);
            }
        });

    }
}