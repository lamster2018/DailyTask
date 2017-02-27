package com.example.lahm.dailytask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NDKActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib-c");
    }

    public native String cFunc(int n);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(cFunc(11111));
    }
}
