package com.example.inquiry;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {


    Button course,books,live,offers,support,tutors;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        course = v.findViewById(R.id.course);
        books = v.findViewById(R.id.books);
        live = v.findViewById(R.id.live);
        offers = v.findViewById(R.id.offers);
        support = v.findViewById(R.id.support);
        tutors = v.findViewById(R.id.tutors);


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivityfb.class);
                startActivity(intent);
            }
        });





        return v;
    }
}