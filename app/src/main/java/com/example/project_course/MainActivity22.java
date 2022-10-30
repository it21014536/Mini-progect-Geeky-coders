package com.example.project_course;

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

public class MainActivity22 extends AppCompatActivity {
    private  EditText Title,conducted,sub ,fee,Time,Phone,message;
    private Button Addclass,show;
    private FirebaseFirestore db;
    private  String uId,uTitle,uSubject,uFee,uDesc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);


        Title=findViewById(R.id.Title1);
        conducted=findViewById(R.id.condusted);
        sub=findViewById(R.id.subject22);
        fee=findViewById(R.id.fee);
        Time=findViewById(R.id.Time);
        Phone=findViewById(R.id.Phone);
        message=findViewById(R.id.message);

        Addclass=findViewById((R.id.button4));
        show=findViewById((R.id.show));
        db = FirebaseFirestore.getInstance();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            Addclass.setText("Update");
            uTitle=bundle.getString("uTitle");
            uId=bundle.getString("uId");
           // uconducted=bundle.getString("uconducted");
            uSubject=bundle.getString("uSubject");
            uFee=bundle.getString("uFee");
           // uTime=bundle.getString("uTime");
           // uPhone=bundle.getString("uPhone");
            uDesc=bundle.getString("uDesc");

            Title.setText(uTitle);
           // conducted.setText(uconducted);
            sub.setText(uSubject);
            fee.setText(uTitle);
           // Time.setText(uFee);
            //Phone.setText(uPhone);
            message.setText(uDesc);



        }else{
            Addclass.setText("save");
        }


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity22.this,ShowActivity2.class));
            }
        });

        Addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Title1=Title.getText().toString();
                String conducted1=conducted.getText().toString();
                String sub1=sub.getText().toString();
                String fee1=fee.getText().toString();
                String Time1=Time.getText().toString();
                String phone1=Phone.getText().toString();
                String message1=message.getText().toString();


//update
                Bundle bundle1=getIntent().getExtras();
                if(bundle1 !=null){
                    String id=uId;
                    updateToFireStore(id,Title1 ,sub1 ,fee1 ,message1);

                }else{
                    String id= UUID.randomUUID().toString();
                    saveToFireStore(id,Title1,conducted1,sub1,fee1,Time1,phone1,message1);
                }







               // Intent intent= new  Intent(Addclass.this,page1.class);
               // startActivity(intent);




            }
        });

    }

private void updateToFireStore(String id,String Title1 ,String sub1,String fee1 ,String message1){

        db.collection("Allclasses").document(id).update("Title",Title1,"Subject",sub1,"Fee",fee1,"Message",message1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity22.this,"Data updated successfully",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity22.this,"Error :"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity22.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void  saveToFireStore(String id,String Title1,String conducted1,String sub1,String fee1,String Time1,String phone1,String message1){
        if(!Title1.isEmpty()&& !conducted1.isEmpty()&& !sub1.isEmpty()&& !fee1.isEmpty()&& !Time1.isEmpty()&& !phone1.isEmpty()&& !message1.isEmpty())
        {
        HashMap<String,Object> map= new HashMap();
        map.put("id",id);
        map.put("Title",Title1);
        map.put("Conducted by",conducted1);
        map.put("Subject",sub1);
        map.put("Fee",fee1);
        map.put("Time",Time1);
        map.put("Phone",phone1);
        map.put("Message",message1);

        db.collection("Allclasses").document(id).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity22.this,"Class Added successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity22.this,"Class Not Added :(",Toast.LENGTH_LONG).show();
                    }
                });
        }else
            Toast.makeText(getApplicationContext(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
    }
}