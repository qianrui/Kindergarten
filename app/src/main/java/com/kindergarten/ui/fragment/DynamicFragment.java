package com.kindergarten.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kindergarten.R;
import com.kindergarten.adapter.DynamicAdapter;
import com.kindergarten.entity.DynamicModel;
import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.ui.fragment.base.BaseFragment;
import com.kindergarten.widgets.StateLayout;
import com.kindergarten.widgets.layoutmanager.CustomLinearLayoutManager;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DynamicFragment extends BaseFragment {

    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.rv_dynamic)
    RecyclerView rvDynamic;
    @BindView(R.id.springview)
    SpringView springview;
    @BindView(R.id.statelayout)
    StateLayout statelayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private List<DynamicModel> datas = new ArrayList<>();
    private DynamicAdapter dynamicAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvTitle.setText("宝贝动态");
        for (int i = 0; i < 10; i++)
            datas.add(new DynamicModel("小花老师" + i));
        dynamicAdapter = new DynamicAdapter(getContext(), R.layout.item_dynamic, datas);
        rvDynamic.setLayoutManager(new CustomLinearLayoutManager(getContext()));
        rvDynamic.setAdapter(dynamicAdapter);
//        rvDynamic.addItemDecoration(new RecycleViewDivider(
//                getContext(), LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(getContext(), R.color.white)));

    }

    @Override
    protected void initRequest() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
