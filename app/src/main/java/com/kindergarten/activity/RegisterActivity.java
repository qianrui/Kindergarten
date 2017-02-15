package com.kindergarten.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.kindergarten.R;
import com.kindergarten.activity.common.BaseActivity;

import butterknife.BindView;

/**
 * Created by rui on 2017/2/15 0015
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.ed_userName)
    EditText edUerName;
    @BindView(R.id.ed_password)
    EditText edPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViewsAndEvents() {

    }
}
