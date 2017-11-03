package com.example.lahm.dailytask.netspeed;

import android.net.TrafficStats;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.netspeed
 * Created by lahm on 2017/11/3 12:26 .
 * <p>
 */

public class DeviceBandwidthSampler {
    private static long sPreviousBytes = -1;
    private final ConnectionClassManager mConnectionClassManager;
    private SamplingHandler mHandler;
    private long mLastTimeReading;
    private AtomicInteger mSamplingCounter;
    private HandlerThread mThread;

    private static class DeviceBandwidthSamplerHolder {
        public static final DeviceBandwidthSampler instance = new DeviceBandwidthSampler(ConnectionClassManager.getInstance());

        private DeviceBandwidthSamplerHolder() {
        }
    }

    private class SamplingHandler extends Handler {
        private static final int MSG_START = 1;
        static final long SAMPLE_TIME = 1000;

        public SamplingHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    DeviceBandwidthSampler.this.addSample();
                    sendEmptyMessageDelayed(1, SAMPLE_TIME);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown what=" + msg.what);
            }
        }

        public void startSamplingThread() {
            sendEmptyMessage(1);
        }

        public void stopSamplingThread() {
            removeMessages(1);
        }
    }

    public static DeviceBandwidthSampler getInstance() {
        return DeviceBandwidthSamplerHolder.instance;
    }

    private DeviceBandwidthSampler(ConnectionClassManager connectionClassManager) {
        this.mConnectionClassManager = connectionClassManager;
        this.mSamplingCounter = new AtomicInteger();
        this.mThread = new HandlerThread("ParseThread");
        this.mThread.start();
        this.mHandler = new SamplingHandler(this.mThread.getLooper());
    }

    public void startSampling() {
        if (this.mSamplingCounter.getAndIncrement() == 0) {
            this.mHandler.startSamplingThread();
            this.mLastTimeReading = SystemClock.elapsedRealtime();
        }
    }

    public void stopSampling() {
        if (this.mSamplingCounter.decrementAndGet() == 0) {
            this.mHandler.stopSamplingThread();
            addFinalSample();
        }
    }

    protected void addSample() {
        long newBytes = TrafficStats.getTotalRxBytes();
        long byteDiff = newBytes - sPreviousBytes;
        if (sPreviousBytes >= 0) {
            synchronized (this) {
                long curTimeReading = SystemClock.elapsedRealtime();
                this.mConnectionClassManager.addBandwidth(byteDiff, curTimeReading - this.mLastTimeReading);
                this.mLastTimeReading = curTimeReading;
            }
        }
        sPreviousBytes = newBytes;
    }

    protected void addFinalSample() {
        addSample();
        sPreviousBytes = -1;
    }

    public boolean isSampling() {
        return this.mSamplingCounter.get() != 0;
    }
}
