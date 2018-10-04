package com.example.jp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookSection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button uploadcontribute;
    FirebaseAuth firebaseauth;
    Button Signout;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseauth.getCurrentUser();
        if (user == null) {

            Toast.makeText(this, "Please Sign in", Toast.LENGTH_SHORT).show();
            Intent neww = new Intent(BookSection.this, MainActivity.class);
            startActivity(neww);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseauth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_book_section);
        Spinner spinner = findViewById(R.id.sem);
        Button req = (Button) findViewById(R.id.Req);
        Signout = (Button) findViewById(R.id.signout);
        uploadcontribute = (Button) findViewById(R.id.Contribute);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        uploadcontribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookSection.this,NotesUpload.class);
                startActivity(intent);
            }
        });
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookSection.this,SecondActivity.class);
                startActivity(intent);

            }
        });
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next1;
                next1 = new Intent(BookSection.this,Request_books.class);
                startActivity(next1);
            }
        });

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
        final String semm = parent.getSelectedItem().toString();
        switch (position) {
            case 1:

                spinner1.setAdapter(adapter1);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(),ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(BookSection.this, ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(), ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(), ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(), ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(), ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(), ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
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
                        String text = parent.getItemAtPosition(position).toString();
                        if (!text.equals("--select the subject--")) {
                            Intent intent = new Intent(getApplicationContext(), ViewFiles.class);
                            intent.putExtra("getData1", parent.getSelectedItem().toString());
                            intent.putExtra("getData", semm);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
