package com.example.lahm.dailytask.netspeed;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.netspeed
 * Created by lahm on 2017/11/3 12:23 .
 * <p>
 */

public class ConnectionClassManager {
    static final long BANDWIDTH_LOWER_BOUND = 10;
    private static final int BYTES_TO_BITS = 8;
    private static final double DEFAULT_DECAY_CONSTANT = 0.05d;
    static final int DEFAULT_GOOD_BANDWIDTH = 2000;
    static final long DEFAULT_HYSTERESIS_PERCENT = 20;
    static final int DEFAULT_MODERATE_BANDWIDTH = 550;
    static final int DEFAULT_POOR_BANDWIDTH = 150;
    static final double DEFAULT_SAMPLES_TO_QUALITY_CHANGE = 5.0d;
    private static final double HYSTERESIS_BOTTOM_MULTIPLIER = 0.8d;
    private static final double HYSTERESIS_TOP_MULTIPLIER = 1.25d;
    private AtomicReference<ConnectionQuality> mCurrentBandwidthConnectionQuality;
    private ExponentialGeometricAverage mDownloadBandwidth;
    private volatile boolean mInitiateStateChange;
    private ArrayList<ConnectionClassStateChangeListener> mListenerList;
    private AtomicReference<ConnectionQuality> mNextBandwidthConnectionQuality;
    private int mSampleCounter;

    private static class ConnectionClassManagerHolder {
        public static final ConnectionClassManager instance = new ConnectionClassManager();

        private ConnectionClassManagerHolder() {
        }
    }

    public interface ConnectionClassStateChangeListener {
        void onBandwidthStateChange(ConnectionQuality connectionQuality, double d);
    }

    public static ConnectionClassManager getInstance() {
        return ConnectionClassManagerHolder.instance;
    }

    private ConnectionClassManager() {
        this.mDownloadBandwidth = new ExponentialGeometricAverage(DEFAULT_DECAY_CONSTANT);
        this.mInitiateStateChange = false;
        this.mCurrentBandwidthConnectionQuality = new AtomicReference(ConnectionQuality.UNKNOWN);
        this.mListenerList = new ArrayList();
    }

    public synchronized void addBandwidth(long bytes, long timeInMs) {
        if (timeInMs != 0 && ((((double) bytes) * 1.0d) / ((double) timeInMs)) * 8.0d >= 10.0d) {
            this.mDownloadBandwidth.addMeasurement(((((double) bytes) * 1.0d) / ((double) timeInMs)) * 8.0d);
            if (this.mInitiateStateChange) {
                this.mSampleCounter++;
                if (getCurrentBandwidthQuality() != this.mNextBandwidthConnectionQuality.get()) {
                    this.mInitiateStateChange = false;
                    this.mSampleCounter = 1;
                }
                if (((double) this.mSampleCounter) >= DEFAULT_SAMPLES_TO_QUALITY_CHANGE && significantlyOutsideCurrentBand()) {
                    this.mInitiateStateChange = false;
                    this.mSampleCounter = 1;
                    this.mCurrentBandwidthConnectionQuality.set(this.mNextBandwidthConnectionQuality.get());
                    notifyListeners();
                }
            } else if (this.mCurrentBandwidthConnectionQuality.get() != getCurrentBandwidthQuality()) {
                this.mInitiateStateChange = true;
                this.mNextBandwidthConnectionQuality = new AtomicReference(getCurrentBandwidthQuality());
            }
        }
    }

    private boolean significantlyOutsideCurrentBand() {
        if (this.mDownloadBandwidth == null) {
            return false;
        }
        double bottomOfBand;
        double topOfBand;
        switch ((ConnectionQuality) this.mCurrentBandwidthConnectionQuality.get()) {
            case POOR:
                bottomOfBand = 0.0d;
                topOfBand = 150.0d;
                break;
            case MODERATE:
                bottomOfBand = 150.0d;
                topOfBand = 550.0d;
                break;
            case GOOD:
                bottomOfBand = 550.0d;
                topOfBand = 2000.0d;
                break;
            case EXCELLENT:
                bottomOfBand = 2000.0d;
                topOfBand = 3.4028234663852886E38d;
                break;
            default:
                return true;
        }
        double average = this.mDownloadBandwidth.getAverage();
        if (average > topOfBand) {
            if (average > HYSTERESIS_TOP_MULTIPLIER * topOfBand) {
                return true;
            }
            return false;
        } else if (average < HYSTERESIS_BOTTOM_MULTIPLIER * bottomOfBand) {
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        if (this.mDownloadBandwidth != null) {
            this.mDownloadBandwidth.reset();
        }
        this.mCurrentBandwidthConnectionQuality.set(ConnectionQuality.UNKNOWN);
    }

    public synchronized ConnectionQuality getCurrentBandwidthQuality() {
        ConnectionQuality connectionQuality;
        if (this.mDownloadBandwidth == null) {
            connectionQuality = ConnectionQuality.UNKNOWN;
        } else {
            connectionQuality = mapBandwidthQuality(this.mDownloadBandwidth.getAverage());
        }
        return connectionQuality;
    }

    private ConnectionQuality mapBandwidthQuality(double average) {
        if (average < 0.0d) {
            return ConnectionQuality.UNKNOWN;
        }
        if (average < 150.0d) {
            return ConnectionQuality.POOR;
        }
        if (average < 550.0d) {
            return ConnectionQuality.MODERATE;
        }
        if (average < 2000.0d) {
            return ConnectionQuality.GOOD;
        }
        return ConnectionQuality.EXCELLENT;
    }

    public synchronized double getDownloadKBitsPerSecond() {
        double d;
        if (this.mDownloadBandwidth == null) {
            d = -1.0d;
        } else {
            d = this.mDownloadBandwidth.getAverage();
        }
        return d;
    }

    public ConnectionQuality register(ConnectionClassStateChangeListener listener) {
        if (listener != null) {
            this.mListenerList.add(listener);
        }
        return (ConnectionQuality) this.mCurrentBandwidthConnectionQuality.get();
    }

    public void remove(ConnectionClassStateChangeListener listener) {
        if (listener != null) {
            this.mListenerList.remove(listener);
        }
    }

    private void notifyListeners() {
        int size = this.mListenerList.size();
        for (int i = 0; i < size; i++) {
            ((ConnectionClassStateChangeListener) this.mListenerList.get(i)).onBandwidthStateChange((ConnectionQuality) this.mCurrentBandwidthConnectionQuality.get(), this.mDownloadBandwidth.getAverage());
        }
    }
}
