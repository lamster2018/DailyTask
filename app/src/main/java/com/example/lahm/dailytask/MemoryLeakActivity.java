package com.example.lahm.dailytask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * 内存泄露，这里模拟了，非静态匿名内部类持有外部引用，handler的例子，
 * 问题在，dump heap后，发现，
 * 有safeRunnable,safeHandler均持有MemoryLeakActivity的实例多个
 * 这样，并没发现能解决内存泄露的问题，参考的链接，见收藏夹/性能相关
 */
public class MemoryLeakActivity extends AppCompatActivity {
    private final static String TAG = "aaaaaa";
    private TextView tv;
    private Handler leakHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private SafeHandler safeHandler = new SafeHandler(this);

    static class SafeHandler extends Handler {
        private final WeakReference<Context> reference;

        public SafeHandler(Context context) {
            reference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MemoryLeakActivity activity = (MemoryLeakActivity) reference.get();
            if (activity == null) return;
            switch (msg.what) {
                default:
                    Log.i(TAG, "safeHandler: ");
                    break;
            }
        }
    }

    private Runnable leakRunnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(5000);
            Log.i(TAG, "leakRunnable: ");
        }
    };
    private SafeRunnable safeRunnable = new SafeRunnable();

    static class SafeRunnable implements Runnable {
        @Override
        public void run() {
            SystemClock.sleep(5000);
            Log.i(TAG, "SafeRunnable: ");
        }
    }

    static class SafeAsyncTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<Context> reference;
        private MemoryLeakActivity activity;

        public SafeAsyncTask(Context context) {
            reference = new WeakReference<Context>(context);
            activity = (MemoryLeakActivity) reference.get();
        }

        @Override
        protected void onPreExecute() {
            if (activity == null) return;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (activity == null) return;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            if (activity == null) return;
        }

        @Override
        protected void onCancelled(Void aVoid) {
            if (activity == null) return;
        }


        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(5000);
            Log.i(TAG, "doInBackground: ");
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
//        new Thread(safeRunnable).start();
//        new SafeAsyncTask(this).execute();
//        safeHandler.post(safeRunnable);
        // TODO: 17/2/17 still have question about use safe - handler/runnable/thread
//        testLeak();
    }

    private void testLeak() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
//        safeHandler.removeCallbacksAndMessages(null);
    }
}
