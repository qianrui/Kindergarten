package com.kindergarten.presenter;

import android.text.TextUtils;

import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.util.ValidatorUtil;
import com.kindergarten.view.LoginView;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by 学祺 on 2017/3/28.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView mvpView) {
        attachView(mvpView);
    }

    public void login(String telephone, String password) {
        if (TextUtils.isEmpty(telephone)) {
            mvpView.error("手机号码不能为空！");
            return;
        }
        if (!ValidatorUtil.isMobile(telephone)) {
            mvpView.error("手机号码输入有误！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mvpView.error("密码不能为空！");
            return;
        }
        mvpView.showProgressBar("正在登陆请稍后");
        BmobUser bu2 = new BmobUser();
        bu2.setUsername("sendi");
        bu2.setPassword("123456");
        bu2.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    mvpView.login("登录成功");
                } else {
                    mvpView.error(e.getMessage());
                }
                mvpView.closeProgressBar();
            }
        });
    }

}


