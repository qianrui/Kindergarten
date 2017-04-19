package com.kindergarten.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kindergarten.R;
import com.kindergarten.presenter.LoginPresenter;
import com.kindergarten.ui.activity.base.BaseActivity;
import com.kindergarten.util.AntiShake;
import com.kindergarten.util.DeviceUtil;
import com.kindergarten.view.LoginView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {



    @BindView(R.id.lin_avatar)
    LinearLayout linAvatar;
    @BindView(R.id.ed_username)
    MaterialEditText edUsername;
    @BindView(R.id.ed_password)
    MaterialEditText edPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView btnForgetPassword;
    @BindView(R.id.tv_register)
    TextView btnRegister;
    @BindView(R.id.lin_message)
    LinearLayout linMessage;
    private float mLogoY;
    private AnimatorSet mAnimatorSet;
    AntiShake util = new AntiShake();

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected boolean supportSlideBack() {
        return false;
    }

    @Override
    public void init() {
        linAvatar.postDelayed(() -> {
            if (mLogoY == 0) {
                mLogoY = ViewHelper.getY(linAvatar);
            }
            playLogoInAnim();
        }, 500);

        linMessage.postDelayed(() -> playInAnim(), 300);
//        RxBus.getDefault().toObservable(Boolean.class)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        if (aBoolean)
//                            if (!isFinishing()) {
//                                new Handler().postDelayed(() -> finish(), 1000);
//                            }
//                    }
//                });

    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    private void playLogoInAnim() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(linAvatar, "scaleX", 1.0f, 0.5f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(linAvatar, "scaleY", 1.0f, 0.5f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(linAvatar, "y", mLogoY, DeviceUtil.dip2px(this, 40));

        if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
            mAnimatorSet = null;
        }
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(anim1).with(anim2).with(anim3);
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
    }

    public void playInAnim() {
        linMessage.setVisibility(View.VISIBLE);

        android.animation.AnimatorSet mAnimatorSet;
        android.animation.ObjectAnimator anim3 = android.animation.ObjectAnimator.ofFloat(linMessage,
                "y", DeviceUtil.getScreenSize(this)[1], DeviceUtil.dip2px(this, 200));

        mAnimatorSet = new android.animation.AnimatorSet();
        mAnimatorSet.play(anim3);
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request
                (Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (!aBoolean) {
//                        DialogUtil.getInstance(this)
//                                .setPositiveVisible(true)
//                                .setMessage("请务必给予应用相应的权限")
//                                .setTitleColor(getResources().getColor(R.color.home_notice_text))
//                                .setPositiveDrawable(ContextCompat.getDrawable(this, R.drawable.selector_white_to_green))
//                                .setPositiveButton(R.string.confirm, (d, w) -> {
//
//                                    Intent localIntent = new Intent();
//                                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    if (Build.VERSION.SDK_INT >= 9) {
//                                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//                                        localIntent.setData(Uri.fromParts("package", getApplication().getPackageName(), null));
//                                    } else if (Build.VERSION.SDK_INT <= 8) {
//                                        localIntent.setAction(Intent.ACTION_VIEW);
//                                        localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//                                        localIntent.putExtra("com.android.settings.ApplicationPkgName", getApplicationContext().getPackageName());
//                                    }
//                                    startActivity(localIntent);
//                                    d.dismiss();
//                                    d = null;
//                                })
//                                .create().show();
                    }
                });
    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password, R.id.tv_register})
    public void onClick(View view) {
        if (util.check(view.getId())) return;
        switch (view.getId()) {
            case R.id.btn_login:
                mvpPresenter.login(edUsername.getText().toString(), edPassword.getText().toString());
                break;
            case R.id.tv_forget_password:
//                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void setTitle() {
    }

    @Override
    public void login(String msg) {
        showToast(msg);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id", getIntent().getIntExtra("id", -1));
        startActivity(intent);
        finish();
    }

    @Override
    public void netError() {
        super.netError();
        showToast("网络连接异常");
    }



}
