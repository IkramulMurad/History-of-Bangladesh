package com.sinbadsoftware.historyofbangladesh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class ContentActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("OnCreate", "ContentActivity created!");
        super.onCreate(savedInstanceState);

        //get html view file name from previous activity and set on view
        String htmlFileName = getIntent().getStringExtra("htmlView");
        String htmlUrl = "file:///android_asset/html/" + htmlFileName;
        setView(htmlUrl);
    }

    private void setView(String htmlUrl) {
        Log.i("Asset URL", htmlUrl);

        WebView webView = new WebView(this);
        webView.loadUrl(htmlUrl);
        setContentView(webView);
    }
}
