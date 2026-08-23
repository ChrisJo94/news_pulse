package com.example.news_app_2;

import static android.content.ContentValues.TAG;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class web_load extends AppCompatActivity {
    private WebView webView;
    private static final String TAG = "MainActivity";
    private ProgressBar pbar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_load);
        pbar4=findViewById(R.id.pbar4);

        String url=getIntent().getStringExtra("url");
//        String url="https://www.google.com";

        Log.i(TAG,"hello world");
        webView=findViewById(R.id.webView);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        pbar4.setVisibility(View.VISIBLE);
        if(url==null){
            url="https://www.google.com";
            webView.loadUrl(url);

        }
        else{
            pbar4.setVisibility(View.GONE);
            webView.loadUrl(url);
        }
//        webView.loadUrl(url);
//
//
//
//
//        webView.loadUrl(url);

//        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                if(webView.canGoBack()){
//                    webView.goBack();
//                }
//
//            }
//        });

    }

}