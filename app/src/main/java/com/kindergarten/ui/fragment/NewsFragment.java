package com.kindergarten.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kindergarten.R;
import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.ui.fragment.base.BaseFragment;

import butterknife.BindView;


public class NewsFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvTitle.setText("育儿小报");
    }

    @Override
    protected void initRequest() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
