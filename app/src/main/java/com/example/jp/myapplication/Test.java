package com.example.jp.myapplication;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test extends AppCompatActivity {

    PDFView pdfview;
    URL url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        pdfview = (PDFView)findViewById(R.id.pdfview);
        String value1= getIntent().getStringExtra("data");
        try {
           url = new URL(value1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new RetrievePDFStream().execute(value1);
    }
    class RetrievePDFStream extends AsyncTask<String,Void,InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputstream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlconne = (HttpURLConnection) url.openConnection();
                if (urlconne.getResponseCode() == 200) {
                    inputstream = new BufferedInputStream(urlconne.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            return inputstream;
        }

        @Override
        protected void onPostExecute(InputStream inputstream) {
            pdfview.fromStream(inputstream).load();
        }
    }
}
