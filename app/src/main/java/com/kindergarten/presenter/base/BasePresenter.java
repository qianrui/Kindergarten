package com.kindergarten.presenter.base;

import com.kindergarten.view.base.BaseView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rui on 2017/4/19 0019.
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {
    public V mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }


    @Override
    public void detachView() {
        this.mvpView = null;
    }
}
