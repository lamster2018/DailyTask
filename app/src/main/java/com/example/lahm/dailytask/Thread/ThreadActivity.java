package com.example.lahm.dailytask.Thread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.lahm.dailytask.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ThreadActivity extends AppCompatActivity {
    private String TAG = "xxxxxxx";
    private MyThreadExt myThreadExt;

    private StringBuffer sb = new StringBuffer();
    private TextView mResult;
    private WebView webView;

    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (mResult == null) break;
                    sb.append(msg.getData().getString("threadName"))
                            .append(" func finished at ")
                            .append(msg.getData().getLong("timeStamp"));
                    mResult.setText(sb.toString());
                    break;
                case 2:
                    if (mResult == null) break;
                    mResult.setText(sb.toString());
//                    Log.i(TAG, sb.toString());
                default:
                    break;
            }
        }
    };
    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            ThreadActivity activity = (ThreadActivity) reference.get();
            if (activity != null) {
                //deal ui
                activity.mResult.setText(msg.getData().getString("aaa"));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mResult = (TextView) findViewById(R.id.result);
        sb.append(Thread.currentThread().getName()).append("\n");
        webView = new WebView(getApplicationContext());
        loadWeb();
        myHandler.sendEmptyMessage(1);
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void loadWeb() {
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJSInterface(), "BRIDGE");

    }

    @Override
    protected void onResume() {
        super.onResume();
        myThreadExt = new MyThreadExt();
        MyThreadImp myThreadImp = new MyThreadImp();
        Thread thread = new Thread(myThreadImp);
//        thread.start();
//        myThreadExt.start();
//        getPage();
        MyTask myTask = new MyTask();
        myTask.execute("a");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        msgHandler.removeCallbacksAndMessages(null);
        myHandler.removeCallbacksAndMessages(null);
    }

    private class MyThreadImp implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + " start func waste time at "
                    + System.currentTimeMillis());
            getPage();
            someFunc("abc");
            Message msg1 = new Message();
            msg1.what = 1;
            Bundle bundleData = new Bundle();
            bundleData.putString("threadName", Thread.currentThread().getName());
            bundleData.putLong("timeStamp", System.currentTimeMillis());
            msg1.setData(bundleData);
//            msgHandler.sendMessage(msg1);
        }
    }

    private class MyThreadExt extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName()
                    + " start func waste time at "
                    + System.currentTimeMillis());
            try {
                // TODO: 17/2/6
                sleep(500);
                getPage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            someFunc("abc");
            Message msg1 = new Message();
            msg1.what = 1;
            Bundle bundleData = new Bundle();
            bundleData.putString("threadName", Thread.currentThread().getName());
            bundleData.putLong("timeStamp", System.currentTimeMillis());
            msg1.setData(bundleData);
//            msgHandler.sendMessage(msg1);
        }
    }

    private void getPage() {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL("http://w.zhibo.me:8088/getletv2017.php?s=cctv5jiaHD_1300");
            URLConnection urlConnection = url.openConnection();
            httpURLConnection = (HttpURLConnection) urlConnection;
            //只创建一个连接对象
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            httpURLConnection.setRequestProperty("referer", "http://www.5chajian.com/tv/cctv5jia.html");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);//default
            httpURLConnection.setDoOutput(false);//default,post need set true
            httpURLConnection.connect();
            //以上实际只建立tcp连接
            sb.append(Thread.currentThread().getName()).append("\n");
            if (httpURLConnection.getResponseCode() != 200) return;
            InputStream inputStream = httpURLConnection.getInputStream();
            //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine = null;
            while ((inputLine = bufferReader.readLine()) != null) {
                sb.append(inputLine).append("\n");
            }
            bufferReader.close();
            isr.close();
            inputStream.close();
            msgHandler.sendEmptyMessage(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
    }

    private String someFunc(String string) {
//        string = string.
        return string;
    }

    private class MyJSInterface {
        public void communicate() {

            MyTask myTask = new MyTask();
            myTask.execute("teamId");
        }
    }


    private class MyTask extends AsyncTask<String, Integer, String> {
        //paras1 for doInB,p2 for progress.p3 for result type onPostExecute
        @Override
        protected void onPreExecute() {
            super.onPreExecute();//before task start; UI
        }

        @Override
        protected String doInBackground(String... params) {//on a background thread
            Log.i(TAG, "doInBackground: " + Thread.currentThread().getName());
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);//finished task after doInBackground; UI
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);//update progress; UI
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);//cancel UI
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();// cancel UI
        }
    }


}
