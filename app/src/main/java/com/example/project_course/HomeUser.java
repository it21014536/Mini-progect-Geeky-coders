package com.example.project_course;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeUser extends AppCompatActivity {
    private Button course;
    private Button addy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        course = findViewById(R.id.course);
        addy = findViewById(R.id.books);

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new  Intent(HomeUser.this,page1.class);
                startActivity(intent);
            }
        });


        addy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new  Intent(HomeUser.this,MainActivity22.class);
                startActivity(intent);
            }
        });
    }
}