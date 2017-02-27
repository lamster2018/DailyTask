package com.example.lahm.dailytask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NDKActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib-c");
    }

    public native long cFunc(int n, int count);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        TextView textView = (TextView) findViewById(R.id.textView);


        long time = System.currentTimeMillis();
//        textView.setText(String.valueOf(cFunc(1, 1000000000)));
//        textView.setText(String.valueOf(cal(1, 1000000000)));
        System.out.println(System.currentTimeMillis() - time);
    }

    /**
     * n+nn+nnn+....+nnnnnn
     *
     * @param n     数
     * @param count 累加次数
     * @return
     */
    private long cal(int n, int count) {
        long sum = 0;//总和
        int temp = 0;//下一个要自加的数
        int i = 0;
        while (i <= count) {
            temp = temp * 10 + n;
            sum = sum + temp;
            i++;
        }
        return sum;
    }
}
