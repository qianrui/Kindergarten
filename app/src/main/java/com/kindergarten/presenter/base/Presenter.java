package com.kindergarten.presenter.base;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
