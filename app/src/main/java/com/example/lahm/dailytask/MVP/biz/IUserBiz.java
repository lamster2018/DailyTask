package com.example.lahm.dailytask.MVP.biz;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.MVP.biz
 * Created by lahm on 2017/4/4 下午8:48 .
 * <p>
 * https://github.com/lamster2018
 * MVP：还是属于mode，但是是业务模型，这里业务肯定是登录业务，
 * 待会可以加个注销unregister业务
 * 只管声明有的业务，不管这个业务如何实现
 */

public interface IUserBiz {
    void login(String userName,
               String pwd,
               OnLoginListener onLoginListener);
}
