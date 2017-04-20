package com.kindergarten.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.kindergarten.R;
import com.kindergarten.presenter.base.BasePresenter;
import com.kindergarten.ui.activity.base.BaseActivity;
import com.kindergarten.ui.fragment.DynamicFragment;
import com.kindergarten.ui.fragment.MessageFragment;
import com.kindergarten.ui.fragment.MoreFragment;
import com.kindergarten.ui.fragment.NewsFragment;
import com.kindergarten.widgets.bottom.BottomBar;
import com.kindergarten.widgets.bottom.BottomBarTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    List<Fragment> mFragments;
    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;


    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;


    DynamicFragment dynamicFragment;
    NewsFragment newsFragment;
    MessageFragment messageFragment;
    MoreFragment moreFragment;


    @BindView(R.id.line)
    View line;
    private long nowTime;
    private long oldTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void init() {
        setContainer(mBottomBar);
        initFragment();

        mBottomBar
                .addItem(new BottomBarTab(getApplicationContext(), R.mipmap.ic_tab_qbb6_nor, R.mipmap.ic_tab_qbb6_sel_7acf38, getResources().getString(R.string.dynamic)))
                .addItem(new BottomBarTab(getApplicationContext(), R.mipmap.ic_tab_parent_nor, R.mipmap.ic_tab_parent_sel_7acf38, getResources().getString(R.string.news)))
                .addItem(new BottomBarTab(getApplicationContext(), R.mipmap.ic_tab_msg_nor, R.mipmap.ic_tab_msg_sel_7acf38, getResources().getString(R.string.message)))
                .addItem(new BottomBarTab(getApplicationContext(), R.mipmap.ic_tab_more_nor, R.mipmap.ic_tab_more_sel_7acf38, getResources().getString(R.string.more)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
//                if (position == 1 || position == 2) {
//                    if (!AuthorityUtils.isAuthentication()) {
//                        mBottomBar.setCurrentItem(prePosition);
//                        return;
//                    }
//                }
                FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
                hideAllFragment(fTransaction);
                fTransaction.show(mFragments.get(position));
                fTransaction.commitAllowingStateLoss();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
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


    private void initFragment() {
        mFragments = new ArrayList<>();
        dynamicFragment = new DynamicFragment();
        newsFragment = new NewsFragment();
        messageFragment = new MessageFragment();
        moreFragment = new MoreFragment();
        mFragments.add(dynamicFragment);
        mFragments.add(newsFragment);
        mFragments.add(messageFragment);
        mFragments.add(moreFragment);
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        fTransaction.add(R.id.fl_tab_container, mFragments.get(0));
        fTransaction.add(R.id.fl_tab_container, mFragments.get(1));
        fTransaction.add(R.id.fl_tab_container, mFragments.get(2));
        fTransaction.add(R.id.fl_tab_container, mFragments.get(3));
        hideAllFragment(fTransaction);
        fTransaction.show(mFragments.get(0));
        fTransaction.commit();

    }


    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (dynamicFragment != null) fragmentTransaction.hide(dynamicFragment);
        if (newsFragment != null) fragmentTransaction.hide(newsFragment);
        if (messageFragment != null) fragmentTransaction.hide(messageFragment);
        if (moreFragment != null) fragmentTransaction.hide(moreFragment);
    }

    @Override
    public void setTitle() {
    }

    @Override
    protected boolean supportSlideBack() {
        return false;
    }

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            nowTime = System.currentTimeMillis();
            if (nowTime - oldTime < 3000) {
                this.finish();
            } else {
                showToast("再按一次退出程序");
                oldTime = nowTime;
            }
        }
        return true;
    }

    public BottomBar getmBottomBar() {
        return mBottomBar;
    }

    public View getLine() {
        return line;
    }

}

