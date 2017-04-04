package com.example.lahm.dailytask.MVP.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lahm.dailytask.BaseActivity;
import com.example.lahm.dailytask.MVP.bean.User;
import com.example.lahm.dailytask.MVP.presenter.UserLoginPresenter;
import com.example.lahm.dailytask.R;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.MVP.view
 * Created by lahm on 2017/4/4 下午8:56 .
 * <p>
 * https://github.com/lamster2018
 * MVP：view，真正操作UI的类，通过实现IUserLoginView接口，完成对UI的操作
 * 至于什么时候操作，Activity已经把自己的引用this，传给了mUserLoginPresenter，
 * 在presenter里去调用
 */

public class UserLoginActivity extends BaseActivity
        implements IUserLoginView {
    private EditText mEtUsername, mEtPassword;
    private Button mBtnLogin, mBtnClear;
    private ProgressBar mPbLoading;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initViews();
    }

    private void initViews() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_password);

        mBtnLogin = (Button) findViewById(R.id.id_btn_login);
        mBtnClear = (Button) findViewById(R.id.id_btn_clear);

        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtUsername.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() + " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }
}
