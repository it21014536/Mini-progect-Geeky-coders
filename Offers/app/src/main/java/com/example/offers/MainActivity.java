package com.example.offers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button add, showButton;
    private EditText subject,tutor,amount,description;
    private FirebaseFirestore db;
    private String uSub, uTut, uAmo, uDes, uId;
    private TextView title;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subject = findViewById(R.id.name);
        tutor = findViewById(R.id.tutor);
        amount = findViewById(R.id.Amount);
        description = findViewById(R.id.description);
        add = findViewById(R.id.btn2);
        showButton = findViewById(R.id.btn3);
       // model = (Model) getIntent().getSerializableExtra("ITEM");
//        Log.d("TAG", "updateData: "+model.getAmount());
//        subject.setText(model.getSubject());
//        tutor.setText(model.getTutor());
//        amount.setText(model.getAmount());
//        description.setText(model.getDescription());
        title = findViewById(R.id.textView1);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            add.setText("Update");
            uId = bundle.getString("uId");
            uSub = bundle.getString("uSub");
            uTut = bundle.getString("uTut");
            uAmo = bundle.getString("uAmo");
            uDes = bundle.getString("uDes");


        }else{
            add.setText("Save");
        }

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub = subject.getText().toString();
                String tut = tutor.getText().toString();
                String amo = amount.getText().toString();
                String des = description.getText().toString();
                //String id = UUID.randomUUID().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                    String id = uId;
                    updateToFireStore(id,sub,tut,amo,des);

                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(id,sub,tut,amo,des);
                }

            }

            private void updateToFireStore(String id, String sub, String tut, String amo, String des) {
                db.collection("Offers2").document(id).update("sub", sub,"tut",tut,"amo", amo,"des",des)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Data Updated!!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Error : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                      }
                                 });
                             }
                         });
                      }


    private void saveToFireStore(String id, String sub, String tut, String amo, String des){

        if (!sub.isEmpty() && !tut.isEmpty()&& !amo.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("sub" , sub);
            map.put("tut" ,tut);
            map.put("amo" , amo);
            map.put("des" , des);

            db.collection("Offers2").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Sent!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
    }
}



