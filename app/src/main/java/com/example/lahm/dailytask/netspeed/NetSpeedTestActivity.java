package com.example.lahm.dailytask.netspeed;

import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lahm.dailytask.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 2017/11/3 12:21 .
 * <p>
 * Copyright (c) 2016—2017 https://www.lizhiweike.com all rights reserved.
 */
public class NetSpeedTestActivity extends AppCompatActivity implements OnClickListener {
    private Button btStartTest;
    private ConnectivityManager connectivityManager;
    private double currentNetSpeed = 0.0d;
    private ConnectionQuality mConnectionClass = ConnectionQuality.UNKNOWN;
    private ConnectionClassManager mConnectionClassManager;
    private DeviceBandwidthSampler mDeviceBandwidthSampler;
    private ConnectionChangedListener mListener;
    private ProgressBar mRunningBar;
    private int mTries = 0;
    private String mURL = "http://pinduoduoimg.yangkeduo.com/android/pinduoduo_latest.apk";
    private long startTimeStamp;
    private long totalTime;
    private TextView tvNetModel;
    private TextView tvNetSpeed;
    private TextView tvNetWidth;
    private TextView tvTotalTime;

    private class ConnectionChangedListener implements ConnectionClassManager.ConnectionClassStateChangeListener {
        private ConnectionChangedListener() {
        }

        public void onBandwidthStateChange(ConnectionQuality bandwidthState, double netSpeed) {
            NetSpeedTestActivity.this.mConnectionClass = bandwidthState;
            NetSpeedTestActivity.this.currentNetSpeed = netSpeed;
            NetSpeedTestActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    NetSpeedTestActivity.this.tvNetSpeed.setText("平均带宽速度：=" + String.valueOf(NetSpeedTestActivity.this.clip(NetSpeedTestActivity.this.currentNetSpeed)) + "kbps");
                    NetSpeedTestActivity.this.tvNetWidth.setText("平均带宽状态：=" + NetSpeedTestActivity.this.mConnectionClass.toString());
                }
            });
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Void> {
        private DownloadImage() {
        }

        protected void onPreExecute() {
            NetSpeedTestActivity.this.mDeviceBandwidthSampler.startSampling();
            NetSpeedTestActivity.this.mRunningBar.setVisibility(View.VISIBLE);
            NetSpeedTestActivity.this.startTimeStamp = SystemClock.elapsedRealtime();
        }

        protected Void doInBackground(String... url) {
            InputStream input;
            try {
                URLConnection connection = new URL(url[0]).openConnection();
                connection.setUseCaches(false);
                connection.connect();
                input = connection.getInputStream();
                do {
                } while (input.read(new byte[1024]) != -1);
                input.close();
            } catch (IOException e) {
//                LogUtils.d("Error while downloading image.");
            } catch (Throwable th) {
//                input.close();
            }
            return null;
        }

        protected void onPostExecute(Void v) {
            NetSpeedTestActivity.this.mDeviceBandwidthSampler.stopSampling();
            NetSpeedTestActivity.this.totalTime = (SystemClock.elapsedRealtime() - NetSpeedTestActivity.this.startTimeStamp) / 1000;
            NetSpeedTestActivity.this.tvTotalTime.setText("用时：=" + NetSpeedTestActivity.this.totalTime + "s");
            if (NetSpeedTestActivity.this.mConnectionClass == ConnectionQuality.UNKNOWN && NetSpeedTestActivity.this.mTries < 10) {
                NetSpeedTestActivity.this.mTries = NetSpeedTestActivity.this.mTries + 1;
                new DownloadImage().execute(new String[]{NetSpeedTestActivity.this.mURL});
            }
            if (!NetSpeedTestActivity.this.mDeviceBandwidthSampler.isSampling()) {
                NetSpeedTestActivity.this.mRunningBar.setVisibility(View.GONE);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_net_sppeed_test_activity_main);
        this.connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        this.mConnectionClassManager = ConnectionClassManager.getInstance();
        this.mDeviceBandwidthSampler = DeviceBandwidthSampler.getInstance();
        this.mListener = new ConnectionChangedListener();
        initView();
        initClickListener();
    }

    protected void onPause() {
        super.onPause();
        this.mConnectionClassManager.remove(this.mListener);
    }

    protected void onResume() {
        super.onResume();
        this.mConnectionClassManager.register(this.mListener);
    }

    private void initClickListener() {
        this.btStartTest.setOnClickListener(this);
    }

    public static void start(Context context, String info) {
        context.startActivity(new Intent(context, NetSpeedTestActivity.class));
    }

    private void initView() {
        this.tvNetModel = (TextView) findViewById(R.id.tv__debug_net_model);
        this.tvNetSpeed = (TextView) findViewById(R.id.tv_debug_net_speed);
        this.tvNetWidth = (TextView) findViewById(R.id.tv_debug_net_width);
        this.tvTotalTime = (TextView) findViewById(R.id.tv_debug_total_time);
        setNetconnctModel();
        initData();
        this.btStartTest = (Button) findViewById(R.id.bt_debug_start_test_net);
        this.mRunningBar = (ProgressBar) findViewById(R.id.runningBar);
    }

    public void initData() {
        this.tvNetWidth.setText("平均带宽状态：=" + ConnectionQuality.UNKNOWN.toString());
        this.tvNetSpeed.setText("平均带宽速度：=" + String.valueOf(0) + "kbps");
        this.tvTotalTime.setText("用时：=0s");
    }

    public void setNetconnctModel() {
        NetworkInfo currentModel = getNetType();
        if (currentModel != null) {
            this.tvNetModel.setText("当前连接模式：=" + currentModel.getTypeName());
        } else {
            this.tvNetModel.setText("UnKnow");
        }
    }

    public String clip(double speed) {
        return new DecimalFormat("0.##").format(speed);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.bt_debug_start_test_net) {
            setNetconnctModel();
            initData();
            this.mConnectionClassManager.reset();
            new DownloadImage().execute(new String[]{this.mURL});
        }
    }

    private NetworkInfo getNetType() {
        NetworkInfo mobNetInfo = this.connectivityManager.getNetworkInfo(0);
        NetworkInfo wifiNetInfo = this.connectivityManager.getNetworkInfo(1);
        NetworkInfo avtivityInfo = this.connectivityManager.getActiveNetworkInfo();
        if (mobNetInfo.isConnected()) {
            return mobNetInfo;
        }
        if (wifiNetInfo.isConnected()) {
            return wifiNetInfo;
        }
        return avtivityInfo;
    }
}
