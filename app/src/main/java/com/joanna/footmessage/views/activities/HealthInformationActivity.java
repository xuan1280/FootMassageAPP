package com.joanna.footmessage.views.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.joanna.footmessage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthInformationActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information);
        ButterKnife.bind(this);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        // TODO 增加網路連線判斷
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setContentView(webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://chingchengwan.github.io/g3health/");
    }
}
