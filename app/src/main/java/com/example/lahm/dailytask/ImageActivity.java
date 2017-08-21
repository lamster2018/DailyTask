package com.example.lahm.dailytask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.lahm.dailytask.Util.ImageUtil;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
//        imageView.setImageBitmap();//实际调用setImageDrawable
//        imageView.setImageDrawable();
//        imageView.setImageResource(R.mipmap.giantimg);

        imageView.setBackgroundResource(R.mipmap.giantimg);//实际调用setBackground
//        imageView.setBackground();//实际调用setBackgroundDrawable
        imageView.setBackgroundDrawable(ImageUtil.setBackground(this, R.mipmap.giantimg));

    }
}
