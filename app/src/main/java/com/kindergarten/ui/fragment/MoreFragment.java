package com.kindergarten.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kindergarten.R;
import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.ui.fragment.base.BaseFragment;

import butterknife.BindView;


public class MoreFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvTitle.setText("更多");
    }

    @Override
    protected void initRequest() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
