package com.example.jp.myapplication;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private RecyclerView recyclerView;
    private Context context;
    PDFView pdfview;
    Uri uri;
    URL url;

    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    DownloadManager downloadManager;
    Context mcontext;


    public void update(String filename, String url) {
        items.add(filename);
        urls.add(url);
        notifyDataSetChanged();
    }

    public MyAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.NameofFile.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NameofFile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NameofFile = itemView.findViewById(R.id.FileName);
            pdfview = (PDFView)itemView.findViewById(R.id.pdfview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildLayoutPosition(v);
                    String uurl = urls.get(position).toString();
                    /*Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                    new RetrievePDFStream().execute(uurl);
                    //passvalue(uri);*/
                    Intent i=new Intent(context,Test.class);
                    i.putExtra("data",uurl);
                    context.startActivity(i);



                }


            });
        }

        class RetrievePDFStream extends AsyncTask<String, Void, InputStream> {

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
}

