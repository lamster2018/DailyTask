package com.example.lahm.dailytask.MVP.biz;

import com.example.lahm.dailytask.MVP.bean.User;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.MVP.biz
 * Created by lahm on 2017/4/4 下午8:49 .
 * <p>
 * https://github.com/lamster2018
 * MVP:mode，属于业务mode
 * 具体实现了业务mode中，业务接口IUserBiz的login方法
 * 此处开启了一个线程延迟2秒
 * 通过回调，OnLoginListener，去完成后续的view操作
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String userName,
                      final String pwd,
                      final OnLoginListener onLoginListener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("zhy".equals(userName) && "123".equals(pwd)) {
                    User user = new User();
                    user.setUsername(userName);
                    user.setPassword(pwd);
                    onLoginListener.loginSuccess(user);
                } else {
                    onLoginListener.loginFailed();
                }
            }
        }.start();
    }
}
