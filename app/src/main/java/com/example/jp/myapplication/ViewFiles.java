package com.example.jp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewFiles extends AppCompatActivity {

    RecyclerView recyclerview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploaded_files);

        String value= getIntent().getStringExtra("getData");
        String value1= getIntent().getStringExtra("getData1");

        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Links/"+value+"/"+value1);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String filename=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                ((MyAdapter)recyclerview.getAdapter()).update(filename,url);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerview = findViewById(R.id.UploadedFiles);

        recyclerview.setLayoutManager(new LinearLayoutManager(ViewFiles.this));
        MyAdapter myAdapter=new MyAdapter(recyclerview,ViewFiles.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerview.setAdapter(myAdapter);
    }
}