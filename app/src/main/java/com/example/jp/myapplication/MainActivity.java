package com.example.jp.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;
    private TextView signup;
    private Button admin;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.uname);
        password=(EditText)findViewById(R.id.passwd);
        login=(Button)findViewById(R.id.loginbt);
        signup=(TextView) findViewById(R.id.signupbt);
        admin=(Button)findViewById(R.id.admin);
        firebaseauth = FirebaseAuth.getInstance();
        progressdialog = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == login)
                {
                    userlogin();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == signup)
                {
                   //Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                    Intent next;
                    next = new Intent(MainActivity.this,Registration.class);
                    startActivity(next);
                }
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(MainActivity.this,Admin.class);
                startActivity(next);
            }
        });

    }


    private void userlogin()
    {
        final String username = name.getText().toString().trim();
        String Password = password.getText().toString().trim();


        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Password))
        {
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        progressdialog.setMessage("Signing please wait");
        progressdialog.show();
        firebaseauth.signInWithEmailAndPassword(username,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Intent nextact = new Intent(getApplicationContext(),BookSection.class);
                            startActivity(nextact);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
    }


}
