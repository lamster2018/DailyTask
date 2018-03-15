package com.example.lahm.dailytask.Hook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Util
 * Created by lahm on 2018/3/14 下午4:38 .
 */

public class HookUtil {
    private String TAG = "hook";
    private Class<?> proxyActivity;

    private Context context;

    public HookUtil(Class<?> proxyActivity, Context context) {
        this.proxyActivity = proxyActivity;
        this.context = context;
    }

    public void hookAms() {
        if (Build.VERSION.SDK_INT > 25) hookAmsAfterO();
        else HookAmsBeforeO();
    }

    private void HookAmsBeforeO() {
        try {
            Class<?> ActivityManagerNativeClss = Class.forName("android.app.ActivityManagerNative");
            Field defaultFiled = ActivityManagerNativeClss.getDeclaredField("gDefault");
            defaultFiled.setAccessible(true);
            Object defaultValue = defaultFiled.get(null);

            Class<?> SingletonClass = Class.forName("android.util.Singleton");
            Field mInstance = SingletonClass.getDeclaredField("mInstance");
            mInstance.setAccessible(true);
            Object iActivityManagerObject = mInstance.get(defaultValue);

            Class<?> IActivityManagerIntercept = Class.forName("android.app.IActivityManager");
            AmsInvocationHandler handler = new AmsInvocationHandler(iActivityManagerObject);

            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{IActivityManagerIntercept}, handler);

            //现在替换掉这个对象
            mInstance.set(defaultValue, proxy);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hookAmsAfterO() {
        try {
            //通过反射拿到Singleton<IActivityManager>的实例IActivityManager
            Class<?> ActivityManagerClazz = android.app.ActivityManager.class;
            Field IActivityManagerSingletonField = ActivityManagerClazz
                    .getDeclaredField("IActivityManagerSingleton");
            //该实例可访问
            IActivityManagerSingletonField.setAccessible(true);
            //return the value of the represented field in object--实际是拿的Singleton的实例
            Object defaultValue = IActivityManagerSingletonField.get(null);

            //反射SingleTon，在源码里找到完整类名,但我这里好像AS没找到这个源文件，不龟毛这个
//            Class<?> SingletonClass = android.util.Singleton.class;
            Class<?> SingletonClass = Class.forName("android.util.Singleton");
            Field mInstance = SingletonClass.getDeclaredField("mInstance");
            mInstance.setAccessible(true);
            //到这里才拿到真正的ActivityManager实例对象
            Object iActivityManagerObject = mInstance.get(defaultValue);


            //开始动态代理，用代理对象替换掉真实的ActivityManager，瞒天过海
            Class<?> IActivityManagerIntercept = Class.forName("android.app.IActivityManager");
//            Class<?> IActivityManagerIntercept = android.app.IActivityManager;

            AmsInvocationHandler handler = new AmsInvocationHandler(iActivityManagerObject);

            IBinder proxy = (IBinder) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{IActivityManagerIntercept}, handler);

            //现在用proxy替换掉defaultValue对象
            mInstance.set(defaultValue, proxy);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class AmsInvocationHandler implements InvocationHandler {

        private Object iActivityManagerObject;

        private AmsInvocationHandler(Object iActivityManagerObject) {
            this.iActivityManagerObject = iActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Log.i(TAG, method.getName());
            if ("startActivity".contains(method.getName())) {
                Log.e(TAG, "小弟到此一游！！！");
            }


            //O 以上， args[0] 是一个BindProxy类型
            Intent intent = null;
            int index = 0;
            for (int i = 0, len = args.length; i < len; i++) {
                Object arg = args[i];
                if (arg instanceof Intent) {
                    //说明找到了startActivity的Intent参数
                    intent = (Intent) args[i];
                    //这个意图是不能被启动的，因为Activity没有在清单文件中注册
                    index = i;
                }
            }

//            //伪造一个代理的Intent，代理Intent启动的是proxyActivity
            Intent proxyIntent = new Intent();
            ComponentName componentName = new ComponentName(context, proxyActivity);
            proxyIntent.setComponent(componentName);
            proxyIntent.putExtra("oldIntent", intent);
            args[index] = proxyIntent;//替换

            return method.invoke(iActivityManagerObject, args);
        }
    }


    private class ActivityThreadHandlerCallback implements Handler.Callback {

        private Handler handler;

        private ActivityThreadHandlerCallback(Handler handler) {
            this.handler = handler;
        }

        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG, "handleMessage");
            //替换之前的Intent
            if (msg.what == 100) {
                Log.i(TAG, "lauchActivity");
                handleLaunchActivity(msg);
            }

            handler.handleMessage(msg);
            return true;
        }
    }

    public void hookSystemHandler() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            //获取主线程对象
            Object activityThread = currentActivityThreadMethod.invoke(null);
            //获取mH字段
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);
            //获取Handler
            Handler handler = (Handler) mH.get(activityThread);
            //获取原始的mCallBack字段
            Field mCallBack = Handler.class.getDeclaredField("mCallback");
            mCallBack.setAccessible(true);
            //这里设置了我们自己实现了接口的CallBack对象
            mCallBack.set(handler, new ActivityThreadHandlerCallback(handler));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLaunchActivity(Message msg) {
        Object obj = msg.obj;//ActivityClientRecord
        try {
            Field intentField = obj.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent proxyIntent = (Intent) intentField.get(obj);
            Intent realIntent = proxyIntent.getParcelableExtra("oldIntent");
            if (realIntent != null) {
                proxyIntent.setComponent(realIntent.getComponent());
            }
        } catch (Exception e) {
            Log.i(TAG, "launchActivity failed");
        }

    }

}
