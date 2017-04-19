package com.kindergarten.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.util.Log;

import com.kindergarten.ui.activity.MainActivity;
import com.kindergarten.util.DisplayUtil;
import com.kindergarten.util.ToastUtil;
import com.kindergarten.widgets.ActivityLifecycleHelper;
import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/15 0015
 */
public class MyApplication extends MultiDexApplication {
    private ActivityLifecycleHelper mActivityLifecycleHelper;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        registerActivityLifecycleCallbacks(mActivityLifecycleHelper = new ActivityLifecycleHelper());
        //bug初始化
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .recoverEnabled(true)
                .callback(new MyCrashCallback())
                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
//                .skip(TestActivity.class)
                .init(this);
        //获得屏幕尺寸
        initDisplayOpinion();
        //初始化xutils3
        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志
        //初始化Toast
        ToastUtil.builder(getApplicationContext());

    }

    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }

    static final class MyCrashCallback implements RecoveryCallback {
        @Override
        public void stackTrace(String exceptionMessage) {
            Log.e("zxy", "exceptionMessage:" + exceptionMessage);
        }

        @Override
        public void cause(String cause) {
            Log.e("zxy", "cause:" + cause);
        }

        @Override
        public void exception(String exceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {
            Log.e("zxy", "exceptionClassName:" + exceptionType);
            Log.e("zxy", "throwClassName:" + throwClassName);
            Log.e("zxy", "throwMethodName:" + throwMethodName);
            Log.e("zxy", "throwLineNumber:" + throwLineNumber);
        }

        @Override
        public void throwable(Throwable throwable) {

        }
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }

    public static Context getContext() {
        return context;
    }


}
