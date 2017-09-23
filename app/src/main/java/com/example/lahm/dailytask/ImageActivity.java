package com.example.lahm.dailytask;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Drawable drawable = getResources().getDrawable(R.mipmap.rabbit);
        Log.i("ceshi", "dpi----" + Resources.getSystem().getDisplayMetrics().density);
        Log.i("ceshi", "densityDpi----" + Resources.getSystem().getDisplayMetrics().densityDpi);
        Log.i("ceshi", "IntrinsicWidth----" + drawable.getIntrinsicWidth());
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setCompoundDrawables(drawable, null, null, null);
//        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

//        ImageView imageView = (ImageView) findViewById(R.id.iv);

//        imageView.setImageBitmap();//实际调用setImageDrawable
//        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.giantimg));
//        imageView.setImageResource(R.mipmap.giantimg);

//        imageView.setBackgroundResource(R.mipmap.giantimg);//实际调用setBackground
//        imageView.setBackground();//实际调用setBackgroundDrawable
//        imageView.setBackgroundDrawable(ImageUtil.setBackground(this, R.mipmap.giantimg));


    }
}
