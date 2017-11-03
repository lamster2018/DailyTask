package com.example.lahm.dailytask.netspeed;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.netspeed
 * Created by lahm on 2017/11/3 12:27 .
 * <p>
 */
public class ExponentialGeometricAverage {
    private int mCount;
    private final int mCutover;
    private final double mDecayConstant;
    private double mValue = -1.0d;

    public ExponentialGeometricAverage(double decayConstant) {
        int i;
        this.mDecayConstant = decayConstant;
        if (decayConstant == 0.0d) {
            i = Integer.MAX_VALUE;
        } else {
            i = (int) Math.ceil(1.0d / decayConstant);
        }
        this.mCutover = i;
    }

    public void addMeasurement(double measurement) {
        double keepConstant = 1.0d - this.mDecayConstant;
        if (this.mCount > this.mCutover) {
            this.mValue = Math.exp((Math.log(this.mValue) * keepConstant) + (this.mDecayConstant * Math.log(measurement)));
        } else if (this.mCount > 0) {
            double retained = (((double) this.mCount) * keepConstant) / (((double) this.mCount) + 1.0d);
            this.mValue = Math.exp((Math.log(this.mValue) * retained) + (Math.log(measurement) * (1.0d - retained)));
        } else {
            this.mValue = measurement;
        }
        this.mCount++;
    }

    public double getAverage() {
        return this.mValue;
    }

    public void reset() {
        this.mValue = -1.0d;
        this.mCount = 0;
    }
}
