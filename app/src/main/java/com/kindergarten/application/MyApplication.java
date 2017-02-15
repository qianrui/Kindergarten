package com.kindergarten.application;

import android.app.Application;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by Administrator on 2017/2/15 0015
 */
public class MyApplication extends Application {
    private static MyApplication myApplication=null;

    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        initBmob();

    }

    /**
     * 初始化Bmob:设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)
     */
    private void initBmob(){
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
    }
}
