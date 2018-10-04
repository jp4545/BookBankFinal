package com.example.jp.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DownloadFiles extends AppCompatActivity {

    Button downloadpdf;

    FirebaseStorage firebasestorage;
    FirebaseDatabase firebasedatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_files);

        downloadpdf = (Button)findViewById(R.id.DownloadPdf);
        startActivity(new Intent(DownloadFiles.this,ViewFiles.class));
            }
        }