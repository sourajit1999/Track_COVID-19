package com.applex.trackcovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.FALSE;

public class TwitterActivity extends AppCompatActivity {
    private WebView webView;
    ProgressBar progressBar;
    Button who;
    Button moh;
    Button goi;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        progressBar = findViewById(R.id.progressbar);
        who = findViewById(R.id.whobtn);
        moh = findViewById(R.id.mohbtn);
        goi = findViewById(R.id.goibtn);
        webView = findViewById(R.id.web);
        webView.setVisibility(View.INVISIBLE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

        });

        webView.loadUrl("https://twitter.com/who?lang=en");

        who.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearHistory();
                webView.clearCache(true);
                webView.loadUrl("https://twitter.com/who?lang=en");
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);

                who.setBackgroundColor(getResources().getColor(R.color.blue));
                who.setTextColor(getResources().getColor(R.color.white));

                moh.setBackgroundColor(getResources().getColor(R.color.white));
                moh.setTextColor(getResources().getColor(R.color.blue));
                goi.setBackgroundColor(getResources().getColor(R.color.white));
                goi.setTextColor(getResources().getColor(R.color.blue));
            }

        });

        moh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearHistory();
                webView.clearCache(true);
                webView.loadUrl("https://twitter.com/mohfw_india?lang=en");
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);

                moh.setBackgroundColor(getResources().getColor(R.color.blue));
                moh.setTextColor(getResources().getColor(R.color.white));

                who.setBackgroundColor(getResources().getColor(R.color.white));
                who.setTextColor(getResources().getColor(R.color.blue));
                goi.setBackgroundColor(getResources().getColor(R.color.white));
                goi.setTextColor(getResources().getColor(R.color.blue));
            }
        });

        goi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearHistory();
                webView.clearCache(true);
                webView.loadUrl("https://twitter.com/mygovindia");
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);

                goi.setBackgroundColor(getResources().getColor(R.color.blue));
                goi.setTextColor(getResources().getColor(R.color.white));

                moh.setBackgroundColor(getResources().getColor(R.color.white));
                moh.setTextColor(getResources().getColor(R.color.blue));
                who.setBackgroundColor(getResources().getColor(R.color.white));
                who.setTextColor(getResources().getColor(R.color.blue));
            }
        });



    }

    @Override
    public void onBackPressed() {
//        if(webView.canGoBack()){
//            webView.goBack();
//        }
//        else {
//            super.onBackPressed();
//        }
        super.onBackPressed();

    }
    @Override
    protected void onStart() {
        super.onStart();
//
//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            startActivity(intent);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }


}

