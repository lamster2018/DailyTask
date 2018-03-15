package com.example.lahm.dailytask.Hook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.lahm.dailytask.BaseActivity;
import com.example.lahm.dailytask.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 2017/6/18 下午7:14 .
 * <p>
 * https://www.jianshu.com/p/69bfbda302df
 */

public class HookActivity extends BaseActivity {
    private String TAG = "hook";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        //get system clipboard service
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //set clipboard content
        cm.setPrimaryClip(ClipData.newPlainText("data", "fuck"));
        //get clipData
        ClipData clipData = cm.getPrimaryClip();
        String msg = clipData.getItemAt(0).getText().toString();
        Log.i(TAG, "onCreate-----: " + msg);


        HookUtil hookUtil = new HookUtil(ProxyActivity.class, this);
        hookUtil.hookSystemHandler();
        hookUtil.hookAms();


        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HookActivity.this, TargetActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            //reflect to get manager
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            //reflect to get method
            Method getService = serviceManager.getDeclaredMethod("getService", String.class);
            //invoke method to get real binder to finish hook
            IBinder rawBinder = (IBinder) getService.invoke(null, Context.CLIPBOARD_SERVICE);

            //to achieve active proxy
            IBinder hookBinder = (IBinder) Proxy.newProxyInstance(
                    serviceManager.getClassLoader(),
                    new Class<?>[]{IBinder.class},
                    new IClipboardBinderHandler(rawBinder));

            Field cacheField = serviceManager.getDeclaredField("sCache");
            cacheField.setAccessible(true);
            @SuppressWarnings({"unchecked"})
            Map<String, IBinder> cache = (Map<String, IBinder>) cacheField.get(null);
            cache.put(Context.CLIPBOARD_SERVICE, hookBinder);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    private class IClipboardBinderHandler implements InvocationHandler {
        private IBinder rawBinder;
        private Class<?> stub;
        private Class<?> iinterface;

        public IClipboardBinderHandler(IBinder rawBinder) {
            this.rawBinder = rawBinder;
            try {
                this.stub = Class.forName("android.content.IClipboard$Stub");
                this.iinterface = Class.forName("android.content.IClipboard");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //只invoke这个遍历方法,好插入我们自己的服务
            if ("queryLocalInterface".equals(method.getName())) {
                return Proxy.newProxyInstance(rawBinder.getClass().getClassLoader(),
                        new Class[]{this.iinterface},
                        new HookBinderInvocationHandler(rawBinder, stub));
            }
            return method.invoke(rawBinder, args);//其余的直接返回
        }
    }

    private class HookBinderInvocationHandler implements InvocationHandler {
        private IBinder secondBinder;

        public HookBinderInvocationHandler(IBinder rawBinder, Class<?> stub) {
            try {
                Method asInterfaceMethod = stub.getDeclaredMethod("asInterface", IBinder.class);
                secondBinder = (IBinder) asInterfaceMethod.invoke(null, rawBinder);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("getPrimaryMethod".equals(method.getName())) {
                new Throwable().printStackTrace();
                return ClipData.newPlainText(null, "you are hooked");
            }
            if ("hasPrimaryClip".equals(method.getName())) return true;
            return method.invoke(secondBinder, args);
        }
    }
}
