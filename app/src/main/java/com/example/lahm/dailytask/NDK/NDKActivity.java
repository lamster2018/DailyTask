package com.example.lahm.dailytask.NDK;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lahm.dailytask.R;

public class NDKActivity extends AppCompatActivity {

    {
        new NDKUtil();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        TextView textView = (TextView) findViewById(R.id.textView);

    }

}
