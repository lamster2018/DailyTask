package com.example.lahm.dailytask.MVP.biz;

import com.example.lahm.dailytask.MVP.bean.User;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.MVP.biz
 * Created by lahm on 2017/4/4 下午8:50 .
 * <p>
 * https://github.com/lamster2018
 * 只是个回调，因为开了多线程模拟网络登录，所以有个回调
 */

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
