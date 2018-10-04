package com.example.jp.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Request_books extends AppCompatActivity {
    private Button request;
    private EditText bookname;
    private EditText author;
    private EditText edition;
    private EditText publisher;
    FirebaseDatabase firebasedatabase;
    FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_books);
        bookname = (EditText) findViewById(R.id.BookName);
        author = (EditText) findViewById(R.id.AuthorName);
        edition = (EditText) findViewById(R.id.Edition);
        publisher = (EditText) findViewById(R.id.Publisher);
        request = (Button) findViewById(R.id.Request);
        firebasedatabase = FirebaseDatabase.getInstance();
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadrequest();
            }
        });


    }

    private void uploadrequest() {
        String Bookname = bookname.getText().toString().trim();
        String aUthor = author.getText().toString().trim();
        String eDition = edition.getText().toString().trim();
        String pUblisher = publisher.getText().toString().trim();
        if (TextUtils.isEmpty(Bookname)) {
            Toast.makeText(this, "Please enter BookName", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(aUthor)) {
            Toast.makeText(this, "Please enter Author", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(eDition)) {
            Toast.makeText(this, "Please enter Edition", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pUblisher)) {
            Toast.makeText(this, "Please enter Publisher", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestBooks req = new RequestBooks(Bookname,aUthor,eDition,pUblisher);
        DatabaseReference databasereference = firebasedatabase.getReference();

        databasereference.child("Request").setValue(req)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete())
                        {
                            Toast.makeText(getApplicationContext(), "Your request added..Will Soon process your request", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }
}
