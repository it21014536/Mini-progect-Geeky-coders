package com.example.project_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class page4 extends AppCompatActivity {
    private Spinner spinner;
    private Button register;

   EditText firstname,lastname,email,subject;

    FirebaseFirestore db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        firstname =findViewById(R.id.firstName);
        lastname=findViewById(R.id.lastName);
        email=findViewById(R.id.email);
        subject=findViewById(R.id.subject22);
       // spinner=findViewById(R.id.gender);
       db = FirebaseFirestore.getInstance();
       spinner=findViewById(R.id.gender);



       ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);

         adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);


        register = findViewById(R.id.btn10);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Firstname=firstname.getText().toString();
                String Lastname=lastname.getText().toString();
                String Email=email.getText().toString();
                String Subject=subject.getText().toString();
               String gender=spinner.getSelectedItem().toString();


                Map<String,Object> Register = new HashMap<>();
                Register.put("FirstName",Firstname);
                Register.put("LastName",Lastname);
                Register.put("email",Email);
                Register.put("Subject",Subject);
               Register.put("gender",gender);


               db.collection("Register").add(Register).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


              //Intent intent= new  Intent(page4.this,page5.class);
                //startActivity(intent);




            }
        });

    }
}