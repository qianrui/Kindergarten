package com.kindergarten.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kindergarten.R;


public class ToastUtil {

    private static Toast toast;
    private static LinearLayout toastView;


    //初始化Toast
    public static void builder(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

    }

    /**
     * 向Toast中添加自定义view
     *
     * @param view
     * @param postion
     * @return
     */
    public ToastUtil addView(View view, int postion) {
        toastView = (LinearLayout) toast.getView();
        toastView.addView(view, postion);

        return this;
    }

    /**
     * 设置Toast字体及背景颜色
     *
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static void setToastColor(int messageColor, int backgroundColor) {
        View view = toast.getView();
        if (view != null) {
            TextView message = ((TextView) view.findViewById(android.R.id.message));
            message.setBackgroundColor(backgroundColor);
            message.setTextColor(messageColor);
        }
    }

    /**
     * 设置Toast字体及背景
     *
     * @param messageColor
     * @param background
     * @return
     */
    public static void setToastBackground(int messageColor, int background) {
        View view = toast.getView();
        if (view != null) {
            view.setBackgroundColor(Color.TRANSPARENT);
            TextView message = ((TextView) view.findViewById(android.R.id.message));
            message.setPadding(32, 12, 32, 12);
            message.setTextSize(16);
            message.setBackgroundResource(background);
            message.setTextColor(messageColor);
        }
    }

    /**
     * 短时间显示Toast
     */
    public static void Short(CharSequence message) {
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        setToastBackground(Color.WHITE, R.drawable.shape_corners_toast);
        show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showToast(Context context, CharSequence message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
    }


    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public ToastUtil Indefinite(Context context, CharSequence message, int duration) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, duration);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return this;
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public ToastUtil Indefinite(Context context, int message, int duration) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, duration);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return this;
    }

    /**
     * 显示Toast
     *
     * @return
     */
    public static void show() {
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    /**
     * 获取Toast
     *
     * @return
     */
    public Toast getToast() {
        return toast;
    }
}