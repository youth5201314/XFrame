package com.youth.xframe;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.youth.xframe.base.XImageLoader;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.XOutdatedUtils;
import com.youth.xframe.utils.log.XLogConfig;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;
import com.youth.xframe.widget.loadingview.XLoadingViewConfig;

public class XFrame {
    private static Context context;
    public static int screenHeight;
    public static int screenWidth;

    // #log
    public static String tag = "XFrame";
    public static boolean isDebug = true;

    private static XImageLoader imageLoader;

    public XFrame() {
    }

    public static void init(Context context) {
        XFrame.context = context;
        screenHeight = XDensityUtils.getScreenHeight();
        screenWidth = XDensityUtils.getScreenWidth();
    }

    public static XLogConfig initXLog() {
        return XLog.init();
    }

    public static XLoadingViewConfig initXLoadingView() {
        return XLoadingView.init();
    }

    public static void setImageLoader(XImageLoader imageLoader) {
        XFrame.imageLoader=imageLoader;
    }

    public static void load(ImageView imageView, Object imageUrl) {
        if (XFrame.imageLoader==null)
            throw new NullPointerException("Call XFrame.setImageLoader(XImageLoader imageLoader) within your Application onCreate() method." +
                    "Or extends XApplication");
        else
            XFrame.imageLoader.load(XFrame.context,imageView,imageUrl);
    }

    public static Context getContext() {
        synchronized (XFrame.class) {
            if (XFrame.context == null)
                throw new NullPointerException("Call XFrame.init(context) within your Application onCreate() method." +
                        "Or extends XApplication");
            return XFrame.context.getApplicationContext();
        }
    }

    public static Resources getResources() {
        return XFrame.getContext().getResources();
    }

    public static Resources.Theme getTheme() {
        return XFrame.getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return XFrame.getContext().getAssets();
    }

    public static Drawable getDrawable( @DrawableRes int id) {
        return XOutdatedUtils.getDrawable(id);
    }

    public static int getColor( @ColorRes int id) {
        return XOutdatedUtils.getColor(id);
    }

    public static Object getSystemService(String name){
        return XFrame.getContext().getSystemService(name);
    }

    public static Configuration getConfiguration() {
        return XFrame.getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return XFrame.getResources().getDisplayMetrics();
    }

}
