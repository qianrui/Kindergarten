package com.kindergarten.view.base;

/**
 * Created by Rui on 2017/4/19 0019.
 */
public interface BaseView {
    void setTitle();

    void error(String message);

    void netError();

    void empty();

    void showProgressBar(String ...msg);

    void closeProgressBar();
}
