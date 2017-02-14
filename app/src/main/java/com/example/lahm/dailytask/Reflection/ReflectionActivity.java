package com.example.lahm.dailytask.Reflection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.lahm.dailytask.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);

        try {
            Class<?> clazz = Class.forName("com.example.lahm.myapplication.Reflection.ReflectionModel");
            //ClassNotFoundException，class的静态方法获取ReflectionModel类的class对象
            //必须加上包名全称，不然报ClassNotFoundException

            Constructor constructorEmp = clazz.getConstructor(new Class[]{});
            //NoSuchMethodException, ReflectionModel有两个构造器，一个无参，一个 两参
            Constructor constructor = clazz.getConstructor(new Class[]{String.class, int.class});
            //获取构造器
            Object object = constructor.newInstance(new Object[]{"abc", 22});
            //IllegalAccessException,InstantiationException,InvocationTargetException
            //构造器生成实例对象

            Method setId = clazz.getMethod("setId", new Class[]{long.class});//setId方法是有参的
            setId.invoke(object, new Object[]{10});

            Method toString = clazz.getMethod("toString", new Class[]{});//toString方法无参
            String result = (String) toString.invoke(object, new Object[]{});//invoke反射调用

            TextView tv = (TextView) findViewById(R.id.result);
            tv.setText(result);

            //以上是public，以下private
            //如果想用反射修改访问控制检查的话，获取Method和Field对象的时候一定要用getDeclaredField和getDeclaredMethod。不要用getField和getMethod。
            //虽然这两个方法的参数都是相同的，
            // 但不同点在于getMethod和getField只能获得public修饰的属性和方法。
            // 而getDeclared可以获取任何类型的属性和方法，
            // 因为这个例子要调用私有的属性和方法，所以要用getDeclaredXX。
            Field privateParams = clazz.getDeclaredField("privateParams");
            //NoSuchFieldException
            privateParams.setAccessible(true);
            privateParams.set(object, "u change me");

            Method privateFunc = clazz.getDeclaredMethod("privateFunc", new Class[]{String.class});
            privateFunc.setAccessible(true);
            privateFunc.invoke(object, new Object[]{"invoke private func"});


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * try to solve menu.setIcon not working after android 4.0
     * <p>
     * http://blog.csdn.net/stevenhu_223/article/details/9705173
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            setMenuIconVisible(menu, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getMenuInflater().inflate(R.menu.abc, menu);//默认是ifRoom
        return true;
    }

    private void setMenuIconVisible(Menu menu, boolean visible) throws Exception {
        Class<?> menuClazz = Class.forName("android.support.v7.view.menu.MenuBuilder");
        //因为MenuBuilder是一个MenuBuilder为系统内部的框架类，无法调用
        //教程通过改方法
//        Method method = menuClazz.getMethod("setOptionalIconsVisible", new Class[]{boolean.class});
//        method.setAccessible(true);
//        method.invoke(menu, visible);//object 就是传进来要被使用的menu

        //我尝试改private属性mOptionalIconsVisible，默认是false，反射修改思路一样
        Field mOptionalIconsVisible = menuClazz.getDeclaredField("mOptionalIconsVisible");
        mOptionalIconsVisible.setAccessible(true);
        mOptionalIconsVisible.set(menu, visible);
    }
}
