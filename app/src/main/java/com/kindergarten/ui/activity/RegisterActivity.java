package com.kindergarten.ui.activity;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kindergarten.R;
import com.kindergarten.ui.activity.base.BaseActivity;
import com.kindergarten.entity.User;
import com.kindergarten.presenter.base.BasePresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by rui on 2017/2/15 0015
 */
public class RegisterActivity extends BaseActivity {


    @BindView(R.id.iv_avatar)
    LinearLayout ivAvatar;
    @BindView(R.id.ed_username)
    MaterialEditText edUsername;
    @BindView(R.id.ed_validate)
    MaterialEditText edValidate;
    @BindView(R.id.btn_validate)
    Button btnValidate;
    @BindView(R.id.ed_password)
    MaterialEditText edPassword;
    @BindView(R.id.ed_password_again)
    MaterialEditText edPasswordAgain;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }


    @OnClick(R.id.btn_confirm)
    public void onClick() {
        BmobUser bu = new BmobUser();
        bu.setUsername("sendi");
        bu.setPassword("123456");
        bu.setEmail("18133673033@163.com");
//注意：不能用save方法进行注册
        bu.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "注册成功:" + s.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setTitle() {

    }
}
