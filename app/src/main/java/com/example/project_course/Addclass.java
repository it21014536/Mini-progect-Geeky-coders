package com.example.project_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Addclass extends AppCompatActivity {
    EditText Title,conducted ,fee,Time,Phone,message;
   private Button Addclass;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);

        Title=findViewById(R.id.Title1);
        conducted=findViewById(R.id.condusted);
        fee=findViewById(R.id.fee);
        Time=findViewById(R.id.Time);
        Phone=findViewById(R.id.Phone);
        message=findViewById(R.id.message);

        Addclass=findViewById((R.id.button4));

        db = FirebaseFirestore.getInstance();

        Addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Title1=Title.getText().toString();
                String conducted1=conducted.getText().toString();
                Integer fee1=Integer.parseInt(fee.getText().toString());
                Integer  Time1=Integer.parseInt(Time.getText().toString());
                Integer phone1=Integer.parseInt(Phone.getText().toString());
                String message1=message.getText().toString();


                Map<String,Object> classes = new HashMap<>();
                classes.put("FirstName",Title1);
                classes.put("conducted",conducted1);
                classes.put("fee",fee1);
                classes.put("Time",Time1);
                classes.put("Phone",phone1);
                classes.put("Message",message1);

                db.collection("Classes").add(classes).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                   }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                   public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

                    }
                });


                Intent intent= new  Intent(Addclass.this,page1.class);
               startActivity(intent);




            }
        });


    }
}