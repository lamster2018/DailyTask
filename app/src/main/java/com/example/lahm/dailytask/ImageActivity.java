package com.example.lahm.dailytask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        textView = (TextView) findViewById(R.id.tv);
        imageView = (ImageView) findViewById(R.id.iv);

//        imageView.setImageBitmap();//实际调用setImageDrawable
//        imageView.setImageDrawable();
//        imageView.setImageResource(R.mipmap.giantimg);
//
//        imageView.setBackgroundResource(R.mipmap.giantimg);//实际调用setBackground
//        imageView.setBackground();//实际调用setBackgroundDrawable
//        imageView.setBackgroundDrawable(ImageUtil.setBackground(this, R.mipmap.giantimg));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textView.setCompoundDrawables(null, null, null, null);
    }
}
