package com.kindergarten.ui.fragment.base;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kindergarten.R;
import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.util.ToastUtil;
import com.kindergarten.view.base.BaseView;
import com.kindergarten.widgets.dialog.LoadingDialog;
import android.support.v4.app.Fragment;
import java.io.File;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    private CompositeSubscription mCompositeSubscription;
    protected Unbinder bind;
    protected P mvpPresenter;
    protected boolean isFirst;
    protected File tempFile;
    private static final int PHOTO_REQUEST_CUT = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, view);
        mvpPresenter = createPresenter();
        initView(view, savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!isFirst) {
                initRequest();
                isFirst = true;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    /**
     * 吐司
     *
     * @param message
     */
    public void showToast(CharSequence message) {
        ToastUtil.Short(message);
    }

    /**
     * 弹出加载框
     */
    @Override
    public void showProgressBar(String... msg) {
        if (msg.length != 0)
            LoadingDialog.show(getActivity(), msg[0]);
        else
            LoadingDialog.show(getActivity());
    }

    /**
     * 关闭加载框
     */
    @Override
    public void closeProgressBar() {
        LoadingDialog.dismiss();
    }


    @Override
    public void setTitle() {

    }

    @Override
    public void error(String message) {
        showToast(message);
    }

    @Override
    public void empty() {
    }

    /**
     * 是否支持EventBus
     *
     * @return
     */
    protected boolean supportEvent() {
        return false;
    }

    /**
     * 网络错误页面
     */
    @Override
    public void netError() {
    }

    protected abstract int getLayoutId();


    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initRequest();

    protected abstract P createPresenter();

}
