package com.example.lahm.dailytask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.example.lahm.dailytask.Singleton.HungrySingle;
import com.example.lahm.dailytask.Singleton.LazySingle;
import com.example.lahm.dailytask.Singleton.SingleDog;

public class WebActivity extends AppCompatActivity {
    private String TAG = "xxxxx";
    private RelativeLayout rootView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        rootView = (RelativeLayout) findViewById(R.id.activity_web);
        webView = new WebView(getApplicationContext());
        initWebView();
        webView.loadUrl("https://www.sina.com");
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
        rootView.addView(webView, lp);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebView() {
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient(this));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJSInterface(), "abcd");
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
    }

    @Override
    protected void onDestroy() {
        rootView.removeView(webView);
        webView.destroy();
        super.onDestroy();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.i(TAG, "shouldOverrideUrlLoading: ");
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(TAG, "onPageStarted: " + url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i(TAG, "onPageFinished: ");
            String call = "javascript:alert('alert里的message')";
//            view.loadUrl(call);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private Context context;

        public MyWebChromeClient(Context context) {
            this.context = context;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.i(TAG, "onJsAlert: ");
            if (context == null) return false;
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setCancelable(true)
                        .setTitle(url)
                        .setMessage(message)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "yes: ");
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "no: ");
                            }
                        })
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Log.i(TAG, "onDismiss: ");
                                //返回键，和确定，取消消失都会走
                            }
                        });
                builder.create().show();
                return true;
            }
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Log.i(TAG, "onJsConfirm: ");
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }
    }

    private class MyJSInterface {

        @JavascriptInterface
        public void onSumResult(int result) {// 写在前段的代码onSumResult，供我们去调用
            Log.i(TAG, "onSumResult result=" + result);
        }
    }
}
