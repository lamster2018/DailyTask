package com.example.lahm.dailytask.MVP.presenter;

import android.os.Handler;

import com.example.lahm.dailytask.MVP.bean.User;
import com.example.lahm.dailytask.MVP.biz.IUserBiz;
import com.example.lahm.dailytask.MVP.biz.OnLoginListener;
import com.example.lahm.dailytask.MVP.biz.UserBiz;
import com.example.lahm.dailytask.MVP.view.IUserLoginView;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.MVP.presenter
 * Created by lahm on 2017/4/4 下午9:06 .
 * <p>
 * https://github.com/lamster2018
 * MVP：presenter，mode和view的中转，
 * 可以看到，UserLoginPresenter构造器，传入了一个IUserLoginView接口，实际上就是实现该IUserLoginView接口的Activity
 * 因为只有activity才是真正去操作UI线程的类，UserLoginActivity实现了这个接口，把它的引用供present使用
 * 但是presenter绝对不会直接操作UI，这个类里连findView都没有呢
 * <p>
 * 构造器里其实可以传  UserBiz，如果传的话，又会变成 activity里（view）写入了业务（mode），又耦合了。
 * <p>
 * activity在view层接收事件，activity传给presenter，presenter告诉activity该调用哪些方法调用UI，
 * 也同时告诉mode，该使用哪个业务方法了。
 * <p>
 * 最后mode通过回调，返回给presenter，presenter再通知activity
 */

public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(),
                userLoginView.getPassword(),
                new OnLoginListener() {
                    @Override
                    public void loginSuccess(final User user) {
                        //需要在UI线程执行
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                userLoginView.toMainActivity(user);
                                userLoginView.hideLoading();
                            }
                        });

                    }

                    @Override
                    public void loginFailed() {
                        //需要在UI线程执行
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                userLoginView.showFailedError();
                                userLoginView.hideLoading();
                            }
                        });

                    }
                });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}