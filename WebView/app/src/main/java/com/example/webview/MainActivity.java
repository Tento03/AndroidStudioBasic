package com.example.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView web;
    EditText link;
    Button beranda,go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web=findViewById(R.id.webview);
        beranda=findViewById(R.id.beranda);
        go=findViewById(R.id.go);
        link=findViewById(R.id.link);

        web.getSettings().setJavaScriptEnabled(true);
        String url="https://google.com";
        web.loadUrl(url);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl(link.getText().toString());
            }
        });

        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl("https://google.com");
            }
        });

        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                link.setText(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                getSupportActionBar().setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }
        });
    }
}