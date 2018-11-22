package com.example.hp.test;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private WebView webview;
    private Dialog dialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = findViewById(R.id.web_view);
        swipeRefreshLayout = findViewById(R.id.swipeContainer);


        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(this);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(false);
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
////                Utility.log("Error call");
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                if(!isLoad) {
//                    isLoad = true;
//                    showDialog();
//                }
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                dismissDialog();
//            }
//        });
//        webview.loadUrl("http://webxodec.com/newalienstore/login");
//
//        }
//
//    protected void showDialog() {
//        dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
//        dialog.setContentView(R.layout.dialog_loader);
//        dialog.setCancelable(false);
//        dialog.show();
//    }
//
//    protected void dismissDialog() {
//        if (dialog != null)
//            dialog.dismiss();
//    }

        webviewLoad();
    }

    private void webviewLoad() {
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                Utility.log("Error call");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(!isLoad) {
                    isLoad = true;
                    showDialog();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefreshLayout.setRefreshing(false);
                dismissDialog();

            }
        });
        webview.loadUrl("http://webxodec.com/newalienstore/login");

    }

    protected void showDialog() {
        dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        dialog.setContentView(R.layout.dialog_loader);
        dialog.setCancelable(false);
        dialog.show();
    }

    protected void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();

    }

    @Override
    public void onRefresh() {
        webviewLoad();
    }
}
