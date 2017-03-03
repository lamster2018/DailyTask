package com.example.lahm.dailytask.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lahm.dailytask.MyBroadcastReceiver;
import com.example.lahm.dailytask.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "service";
    private Button start_service, bind_service;
    private TextView service_result;

    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
        initListener();
        initReceiver();
    }

    private void initView() {
        start_service = (Button) findViewById(R.id.startService);
        bind_service = (Button) findViewById(R.id.bindService);
        service_result = (TextView) findViewById(R.id.service_result);
    }

    private void initListener() {
        start_service.setOnClickListener(this);
        bind_service.setOnClickListener(this);
    }

    private void initReceiver() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        registerReceiver(myBroadcastReceiver, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                Intent service_action_intent = new Intent();
                service_action_intent.setAction("com.abcd.MyService");
                service_action_intent.setPackage("com.example.lahm.dailytask");
                startService(service_action_intent);
                break;
            case R.id.bindService:
                Intent service_class_intent = new Intent(this, MyBindService.class);
                bindService(service_class_intent, sc, Context.BIND_AUTO_CREATE);
                break;
        }
    }

    private MyBindService myBindService;
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //这里的service实际就是自定义的iBinder，里面是实现我们的方法，拿到真正的myBinderService类，在调用后者方法
            Log.i(TAG, "onServiceConnected: ");
            myBindService = ((MyBindService.MyBinder) service).getMyService();
            ((MyBindService.MyBinder) service).ccccc();
            myBindService.abcdefg();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        intent.putExtra("msg", "hello receiver.");
//        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册广播，一定要反注册，否则报错--has leaked IntentReceiver
        unregisterReceiver(myBroadcastReceiver);
        unbindService(sc);
    }
}
