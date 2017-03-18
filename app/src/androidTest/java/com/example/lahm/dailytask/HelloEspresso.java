package com.example.lahm.dailytask;

import android.support.test.espresso.action.EspressoKey;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import com.example.lahm.dailytask.File.FileActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 2017/3/16 下午12:30 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 * <p>
 * https://segmentfault.com/a/1190000004355178
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloEspresso {
    @Rule
    public ActivityTestRule mRule = new ActivityTestRule(MainActivity.class);
    private static int DELAY_TIME = 2000;

    @Test
    public void mainTest() {
        singleClickComponent(R.id.file_btn);
        onView(isRoot()).perform(swipeLeft());
        delay();
        onView(isRoot()).perform(swipeDown());
        delay();
        onView(isRoot()).perform(swipeUp());
        delay();
        onView(isRoot()).perform(swipeRight());
        delay();
        pressBackKey();
        pressHomeKey();
    }

    private void singleClickComponent(int id) {
        onView(withId(id)).perform(click());
        delay();
    }

    private void doubleClickComponent(int id) {
        onView(withId(id)).perform(doubleClick());
        delay();
    }

    private void longClickComponent(int id) {
        onView(withId(id)).perform(longClick());
        delay();
    }

    private void pressBackKey() {
        pressBack();
        delay();
    }

    private void pressHomeKey() {
        pressKey(KeyEvent.KEYCODE_HOME);
    }

    private void delay() {
        delay(DELAY_TIME);
    }

    private void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
