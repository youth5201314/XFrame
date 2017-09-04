package com.youth.xframe.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.xframe.R;
import com.youth.xframe.XFrame;
import com.youth.xframe.utils.XOutdatedUtils;

/**
 * 自定义Toast
 */
public class XToast {
    private static final @ColorInt int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    private static final @ColorInt int ERROR_COLOR = Color.parseColor("#D8524E");
    private static final @ColorInt int INFO_COLOR = Color.parseColor("#3278B5");
    private static final @ColorInt int SUCCESS_COLOR = Color.parseColor("#5BB75B");
    private static final @ColorInt int WARNING_COLOR = Color.parseColor("#FB9B4D");
    private static final @ColorInt int NORMAL_COLOR = Color.parseColor("#444344");

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    private static Context context= XFrame.getContext();

    /** 上次显示的内容 */
    private static String oldMsg ;
    /** 上次时间 */
    private static long oldTime = 0 ;
    /** Toast对象 */
    private static Toast mToast = null ;

    private XToast() {
    }

    public static Toast normal( @NonNull String message) {
        return normal( message, Toast.LENGTH_SHORT, null);
    }

    public static Toast normal( @NonNull String message, Drawable icon) {
        return normal( message, Toast.LENGTH_SHORT, icon);
    }

    public static Toast normal( @NonNull String message, int duration) {
        return normal( message, duration,null);
    }

    public static Toast normal( @NonNull String message, int duration,
                                            Drawable icon) {
        return custom( message, icon ,NORMAL_COLOR, duration);
    }

    public static Toast warning( @NonNull String message) {
        return warning( message, Toast.LENGTH_SHORT, true);
    }

    public static Toast warning( @NonNull String message, int duration) {
        return warning( message, duration, true);
    }

    public static Toast warning( @NonNull String message, int duration, boolean withIcon) {
        Drawable icon=null;
        if (withIcon){
            icon=XOutdatedUtils.getDrawable( R.drawable.xtoast_warning);
        }
        return custom( message,icon, WARNING_COLOR, duration);
    }

    public static Toast info( @NonNull String message) {
        return info( message, Toast.LENGTH_SHORT, true);
    }

    public static Toast info( @NonNull String message, int duration) {
        return info( message, duration, true);
    }

    public static Toast info( @NonNull String message, int duration, boolean withIcon) {
        Drawable icon=null;
        if (withIcon){
            icon=XOutdatedUtils.getDrawable( R.drawable.xtoast_info);
        }
        return custom( message,icon, INFO_COLOR, duration);
    }

    public static Toast success( @NonNull String message) {
        return success( message, Toast.LENGTH_SHORT, true);
    }

    public static Toast success( @NonNull String message, int duration) {
        return success( message, duration, true);
    }

    public static Toast success( @NonNull String message, int duration, boolean withIcon) {
        Drawable icon=null;
        if (withIcon){
            icon=XOutdatedUtils.getDrawable( R.drawable.xtoast_success);
        }
        return custom( message,icon, SUCCESS_COLOR, duration);
    }

    public static Toast error( @NonNull String message) {
        return error( message, Toast.LENGTH_SHORT, true);
    }

    public static Toast error( @NonNull String message, int duration) {
        return error( message, duration, true);
    }

    public static Toast error( @NonNull String message, int duration, boolean withIcon) {
        Drawable icon=null;
        if (withIcon){
            icon=XOutdatedUtils.getDrawable( R.drawable.xtoast_error);
        }
        return custom( message,icon, ERROR_COLOR, duration);
    }

    public static Toast custom(@NonNull String message, @ColorInt int tintColor) {
        return custom( message, null, DEFAULT_TEXT_COLOR, tintColor,Toast.LENGTH_SHORT);
    }

    public static Toast custom( @NonNull String message, Drawable icon, @ColorInt int tintColor) {
        return custom( message, icon, DEFAULT_TEXT_COLOR, tintColor,Toast.LENGTH_SHORT);
    }

    public static Toast custom(@NonNull String message, @ColorInt int tintColor,int duration) {
        return custom( message, null, DEFAULT_TEXT_COLOR, tintColor,duration);
    }

    public static Toast custom( @NonNull String message, Drawable icon, @ColorInt int tintColor,int duration) {
        return custom( message, icon, DEFAULT_TEXT_COLOR, tintColor,duration);
    }
    public static Toast custom( @NonNull String message, @DrawableRes int iconRes,
                                            @ColorInt int textColor, @ColorInt int tintColor, int duration) {
        return custom( message, XOutdatedUtils.getDrawable( iconRes), textColor,tintColor, duration);
    }

    /**
     * 自定义toast方法
     * @param message 提示消息文本
     * @param icon 提示消息的icon,传入null代表不显示
     * @param textColor 提示消息文本颜色
     * @param tintColor 提示背景颜色
     * @param duration 显示时长
     * @return
     */
    public static Toast custom( @NonNull String message, Drawable icon,
                                            @ColorInt int textColor, @ColorInt int tintColor, int duration) {
        View toastLayout = LayoutInflater.from(context).inflate(R.layout.xtoast_view, null);
        ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.xtoast_icon);
        TextView toastText = (TextView) toastLayout.findViewById(R.id.xtoast_text);

        Drawable drawableFrame= XOutdatedUtils.getDrawable(R.drawable.xtoast_frame);
        drawableFrame.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        XOutdatedUtils.setBackground(toastLayout, drawableFrame);

        if (icon == null){
            toastIcon.setVisibility(View.GONE);
        }else{
            XOutdatedUtils.setBackground(toastIcon, icon);
        }

        toastText.setTextColor(textColor);
        toastText.setText(message);
        toastText.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        if(mToast == null) {
            mToast = new Toast(context);
            mToast.setView(toastLayout);
            mToast.setDuration(duration);
            mToast.show();
            oldTime = System.currentTimeMillis() ;
        }else{
            if (message.equals(oldMsg)){
                if(System.currentTimeMillis() - oldTime > Toast.LENGTH_SHORT){
                    mToast.show();
                }
            }else{
                oldMsg=message;
                mToast.setView(toastLayout);
                mToast.setDuration(duration);
                mToast.show();
            }
        }

        oldTime = System.currentTimeMillis() ;
        return mToast;
    }
}
