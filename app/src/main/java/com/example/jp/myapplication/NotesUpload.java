package com.example.jp.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NotesUpload extends AppCompatActivity {

    Button uploadnotes,uPloads;

    Uri pdfurl;
    EditText notification;
    FirebaseAuth firebaseauth;
    TextView sel;
    FirebaseStorage firebasestorage;
    FirebaseDatabase firebasedatabase;
    ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_upload);
        uploadnotes = (Button) findViewById(R.id.UploadnOtes);
        uPloads = (Button) findViewById(R.id.uPloadnOtes);
        notification = (EditText) findViewById(R.id.Notification);
        sel = (TextView) findViewById(R.id.select);
        progressdialog = new ProgressDialog(this);
        firebasedatabase = FirebaseDatabase.getInstance();
        firebasestorage = FirebaseStorage.getInstance();
        uploadnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectpdf();
            }
        });
        uPloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfurl!=null)
                    uploadfile(pdfurl);
                else
                    Toast.makeText(NotesUpload.this,"Please select a file",Toast.LENGTH_SHORT).show();

            }

            // @Override

        });
    }


    private void uploadfile(Uri pdfurl) {
        String filename;
        String filename1;
        filename1=notification.getText().toString().trim();
        filename=notification.getText().toString().trim();
        if (TextUtils.isEmpty(filename)) {
            Toast.makeText(this, "Please enter filename", Toast.LENGTH_SHORT).show();
            return;
        }
        progressdialog = new ProgressDialog(this);
        progressdialog.setProgressStyle(progressdialog.STYLE_HORIZONTAL);
        progressdialog.setTitle("Uploading ...");
        progressdialog.setProgressStyle(0);
        progressdialog.show();
        final String filename2=filename;
        final String filename3=filename1;
        final StorageReference storagereference = firebasestorage.getReference();
        storagereference.child("Notes").child(filename).putFile(pdfurl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storagereference.child("Notes").child(filename2).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference databasereference = firebasedatabase.getReference();
                                databasereference.child("Notes/Links").child(filename3).setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {

                                            progressdialog.dismiss();
                                            Toast.makeText(NotesUpload.this,"File successfully uploaded",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(NotesUpload.this,"File not successfully uploaded",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(NotesUpload.this,"url error",Toast.LENGTH_SHORT).show();
                            }
                        });
                        // String urls = storagereference.getDownloadUrl().toString();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NotesUpload.this,"File  not successfully uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int fileuploadprogress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressdialog.setMessage(fileuploadprogress + "% Uploaded");
            }
        });
    }

    private void selectpdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 86 && resultCode == RESULT_OK && data != null)
        {
            pdfurl = data.getData();
            String nm=data.getData().getLastPathSegment();
            for(String sam:nm.split("/"))
            {
                nm=sam;
            }
            sel.setText("Selected file is");
            notification.setText(nm);
        }
        else
        {
            Toast.makeText(NotesUpload.this,"Please select a file",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((requestCode == 9) && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            selectpdf();
        }
        else
        {
            Toast.makeText(NotesUpload.this,"Please Give permission",Toast.LENGTH_SHORT).show();
        }
    }
}
