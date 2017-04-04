package com.example.lahm.dailytask.MVP.view;

import com.example.lahm.dailytask.MVP.bean.User;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.MVP.view
 * Created by lahm on 2017/4/4 下午8:56 .
 * <p>
 * https://github.com/lamster2018
 * MVP：view层，实际就是告诉view有多少个业务
 */

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
