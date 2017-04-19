package com.kindergarten.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.kindergarten.R;
import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.util.ToastUtil;
import com.kindergarten.view.base.BaseView;
import com.kindergarten.widgets.dialog.LoadingDialog;
import com.kindergarten.widgets.SwipeWindowHelper;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by rui on 2017/2/15 0015
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    private SwipeWindowHelper mSwipeWindowHelper;
    protected P mvpPresenter;
    private ToastUtil toastUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("b9a1744f24738607af631d5bf0a4dc6a")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024*1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
        mvpPresenter = createPresenter();
        if (getActivityViewByView() != null) {
            setContentView(getActivityViewByView());
        } else {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        setTitle();
        init();
    }

    /**
     * 获得布局id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化presenter
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 如果通过代码创建View，则使用此方法
     *
     * @return
     */
    protected View getActivityViewByView() {
        return null;
    }

    /**
     * 初始化
     */
    public abstract void init();


    /**
     * 弹出加载框
     */
    @Override
    public void showProgressBar(String... msg) {
        if (msg.length != 0)
            LoadingDialog.show(this, msg[0]);
        else
            LoadingDialog.show(this);
    }

    /**
     * 关闭加载框
     */
    @Override
    public void closeProgressBar() {
        LoadingDialog.dismiss();
    }

    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    /**
     * 是否支持页面跳转动画
     *
     * @return
     */
    protected abstract boolean toggleOverridePendingTransition();

    /**
     * 是否支持滑动返回
     * 如果Activity不需要滑动返回，则从写此方法，return false;
     *
     * @return
     */
    protected boolean supportSlideBack() {
        return true;
    }


    /**
     * 手势滑动返回
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!supportSlideBack()) {
            return super.dispatchTouchEvent(ev);
        }

        if (mSwipeWindowHelper == null) {
            mSwipeWindowHelper = new SwipeWindowHelper(getWindow());
        }
        return mSwipeWindowHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish() {
        super.finish();
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    public void doBack(View view) {
        super.finish();
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    /**
     * 异常处理
     *
     * @param message
     */
    @Override
    public void error(String message) {
        showToast(message);
    }

    /**
     * 空页面处理
     */
    @Override
    public void empty() {
    }

    /**
     * 网络错误页面
     */
    @Override
    public void netError() {
    }

    /**
     * 吐司
     *
     * @param message
     */
    public void showToast(CharSequence message) {
        ToastUtil.Short(message);
    }
}
