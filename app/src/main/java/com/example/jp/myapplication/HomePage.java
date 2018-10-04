package com.example.jp.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button chooseFile, uploadfile;
    EditText notification;
    EditText sem,sub;
    TextView sell;
    String Semester,Subject;
    FirebaseStorage firebasestorage;
    FirebaseDatabase firebasedatabase;

    Uri pdfurl;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        chooseFile = (Button) findViewById(R.id.ChooseFile);
        uploadfile = (Button) findViewById(R.id.UploadFile);
        notification = (EditText) findViewById(R.id.Notification);
        sell=(TextView) findViewById(R.id.select1);
        firebasedatabase = FirebaseDatabase.getInstance();
        firebasestorage = FirebaseStorage.getInstance();
        Spinner spinner= findViewById(R.id.sem);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.semester,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(HomePage.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectpdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(HomePage.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);

                }

            }


        });

        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfurl!=null)
                    uploadfile(pdfurl);
                else
                    Toast.makeText(HomePage.this,"Please select a file",Toast.LENGTH_SHORT).show();

            }

            // @Override

        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner1 = findViewById(R.id.sub);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem3, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem4, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem5, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem6, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem7, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sem8, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         Semester = parent.getSelectedItem().toString();
        switch (position) {
            case 1:
                spinner1.setAdapter(adapter1);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 2:
                spinner1.setAdapter(adapter2);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 3:
                spinner1.setAdapter(adapter3);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 4:
                spinner1.setAdapter(adapter4);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 5:
                spinner1.setAdapter(adapter5);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 6:
                spinner1.setAdapter(adapter6);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 7:
                spinner1.setAdapter(adapter7);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 8:
                spinner1.setAdapter(adapter8);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Subject = parent.getSelectedItem().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;

        }
    }

    private void uploadfile(Uri pdfurl) {
        final String filename;
        String filename1;
        filename1=notification.getText().toString().trim();
        filename=notification.getText().toString().trim();
        if (TextUtils.isEmpty(filename)) {
            Toast.makeText(this, "Please enter the filename", Toast.LENGTH_SHORT).show();
            return;
        }
        final String filename2=filename;
        final String filename3=filename1;
        if (Semester.equals("--select the semester--") ||  Subject.equals("--select the subject--") || TextUtils.isEmpty(Semester) || TextUtils.isEmpty(Subject)) {
            Toast.makeText(this, "Please choose the semester and subject", Toast.LENGTH_SHORT).show();
            return;
        }
        progressdialog = new ProgressDialog(this);
        progressdialog.setProgressStyle(progressdialog.STYLE_HORIZONTAL);
        progressdialog.setTitle("Uploading ...");
        progressdialog.setProgressStyle(0);
        progressdialog.show();
        final StorageReference storagereference = firebasestorage.getReference();
        storagereference.child("Books/"+Semester+"/"+Subject).child(filename).putFile(pdfurl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storagereference.child("Books/"+Semester+"/"+Subject).child(filename).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference databasereference = firebasedatabase.getReference();
                                databasereference.child("Links/"+Semester+"/"+Subject).child(filename).setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {

                                            progressdialog.dismiss();
                                            Toast.makeText(HomePage.this,"File successfully uploaded",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(HomePage.this,"File not successfully uploaded",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(HomePage.this,"url error",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomePage.this,"File  not successfully uploaded",Toast.LENGTH_SHORT).show();

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

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
            nm=nm.substring(0,nm.length()-4);
            sell.setText("Selected file is");
            notification.setText(nm);
        }
        else
        {
            Toast.makeText(HomePage.this,"Please select a file",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((requestCode == 9) && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectpdf();
        }
        else
        {
            Toast.makeText(HomePage.this,"Please Give permission",Toast.LENGTH_SHORT).show();
        }
    }
}

