package com.example.inquiry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivityfb extends AppCompatActivity {

    private EditText edit_title, edit_email, edit_phone, edit_subject, edit_mess;
    private TextView tv1,tv2;
    private Button send_btn,req_btn;
    private FirebaseFirestore db;

    //update----------------------------
    private String uName,uEmail,uPhone,uSubject,uMessage,uId;
    private com.example.inquiry.Model model ;
//---------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfb);
        model = (com.example.inquiry.Model) getIntent().getSerializableExtra("item");
        edit_title = findViewById(R.id.edit_title);
        edit_email = findViewById(R.id.edit_email);
        edit_phone = findViewById(R.id.edit_phone);
        edit_subject = findViewById(R.id.edit_subject);
        edit_mess = findViewById(R.id.edit_mess);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        send_btn = findViewById(R.id.send_btn);
        req_btn = findViewById(R.id.req_btn);

        db = FirebaseFirestore.getInstance();

        //-------update------------------
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            send_btn.setText("Update");

            uId = bundle.getString("uId");
            uName= bundle.getString("uName");
            uEmail = bundle.getString(" uEmail");
            uPhone = bundle.getString(" uPhone");
            uSubject= bundle.getString("uSubject");
            uMessage= bundle.getString("uMessage");

            edit_title.setText(uName);
            edit_email.setText(model.getEmail());
            edit_phone.setText(model.getPhone());
            edit_subject.setText(uSubject);
            edit_mess.setText(uMessage);
        }else{
            send_btn.setText("Send");
        }

//----------------------------------------------------------

        req_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivityfb.this , ShowActivity.class));

            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = edit_title.getText().toString();
                String Email = edit_email.getText().toString();
                String phone = edit_phone.getText().toString();
                String subject = edit_subject.getText().toString();
                String message = edit_mess.getText().toString();


                //------------update- ---------------------

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 !=null){

                    String id = uId;
                    updateToFirestore(id,Name,Email,phone,subject,message);

                }else{

                    String id = UUID.randomUUID().toString();
                saveToFireStore(id,Name,Email,phone,subject,message);
            }

            }
        });
    }

    //----------------update-------------------------

    private void updateToFirestore(String id, String Name, String Email, String phone, String subject, String message){

        db.collection("Inquiry").document(model.getId()).update("Name", Name,"Email",Email,"phone", phone,"subject",subject,"message",message)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivityfb.this, "Data Updated!!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivityfb.this, "Error : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivityfb.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    //-------------------------------------------------------------------------------------------

    private void saveToFireStore(String id, String Name, String Email, String phone, String subject, String message){

        if (!Name.isEmpty() && !Email.isEmpty()&& !message.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("Name" , Name);
            map.put("Email" , Email);
            map.put("phone" , phone);
            map.put("subject" , subject);
            map.put("message" , message);




            db.collection("Inquiry").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivityfb.this, "Sent!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivityfb.this, "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
    }
    }
