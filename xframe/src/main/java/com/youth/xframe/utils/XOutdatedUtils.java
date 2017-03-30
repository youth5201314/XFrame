package com.youth.xframe.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.youth.xframe.XFrame;

/**
 * 此类主要是用来放一些系统过时方法的处理
 */
public class XOutdatedUtils {

    private XOutdatedUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * setBackgroundDrawable过时方法处理
     *
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
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(XFrame.getContext(), id);
    }

    /**
     * getDrawable过时方法处理
     *
     * @param id 资源id
     * @param theme 指定主题
     * @return
     */
    public static Drawable getDrawable(@DrawableRes int id,
                                       @Nullable Resources.Theme theme) {
        return ResourcesCompat.getDrawable(XFrame.getResources(), id, theme);
    }

    /**
     * getColor过时方法处理
     *
     * @param id
     * @return
     */
    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(XFrame.getContext(), id);
    }

    /**
     * getColor过时方法处理
     *
     * @param id 资源id
     * @param theme 指定主题
     * @return
     */
    public static int getColor(@ColorRes int id, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColor(XFrame.getResources(), id, theme);
    }

    /**
     * getColorStateList过时方法处理
     *
     * @param id 资源id
     * @return
     */
    public static ColorStateList getColorStateList(@ColorRes int id) {
        return ContextCompat.getColorStateList(XFrame.getContext(), id);
    }

    /**
     * getColorStateList过时方法处理
     *
     * @param id 资源id
     * @param theme 指定主题
     * @return
     */
    public static ColorStateList getColorStateList(@ColorRes int id, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColorStateList(XFrame.getResources(), id, theme);
    }
}
