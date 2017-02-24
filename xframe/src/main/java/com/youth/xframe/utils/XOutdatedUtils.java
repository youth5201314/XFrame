package com.youth.xframe.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.youth.xframe.XFrame;

/**
 * 此类主要是用来放一些系统过时方法的处理
 */
public class XOutdatedUtils {
    /**
     * setBackgroundDrawable过时方法处理
     * @param view
     * @param drawable
     */
    public static void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(drawable);
        else
            view.setBackgroundDrawable(drawable);
    }

    /**
     * getDrawable过时方法处理
     * @param id
     * @return
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return XFrame.getContext().getDrawable(id);
        else
            return XFrame.getResources().getDrawable(id);
    }

    /**
     * getColor过时方法处理
     * @param id
     * @return
     */
    public static int getColor(@ColorRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return XFrame.getContext().getColor(id);
        else
            return XFrame.getResources().getColor(id);
    }
}
