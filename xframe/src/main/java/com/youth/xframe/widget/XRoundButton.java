package com.youth.xframe.widget;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.youth.xframe.R;
import com.youth.xframe.utils.XOutdatedUtils;


public final class XRoundButton extends TextView {

    public XRoundButton(Context context) {
        this(context, null);
    }

    public XRoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRoundButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XRoundButton);

        float pressedRatio = a.getFloat(R.styleable.XRoundButton_btnPressedRatio, 0.80f);
        int cornerRadius = a.getLayoutDimension(R.styleable.XRoundButton_btnCornerRadius, 0);

        ColorStateList solidColor = a.getColorStateList(R.styleable.XRoundButton_btnSolidColor);
        int strokeColor = a.getColor(R.styleable.XRoundButton_btnStrokeColor, 0x0);
        int strokeWidth = a.getDimensionPixelSize(R.styleable.XRoundButton_btnStrokeWidth, 0);
        int strokeDashWidth = a.getDimensionPixelSize(R.styleable.XRoundButton_btnStrokeDashWidth, 0);
        int strokeDashGap = a.getDimensionPixelSize(R.styleable.XRoundButton_btnStrokeDashGap, 0);

        a.recycle();

        setSingleLine(true);
        setGravity(Gravity.CENTER);

        RoundDrawable rd = new RoundDrawable(cornerRadius == -1);
        rd.setCornerRadius(cornerRadius == -1 ? 0 : cornerRadius);
        rd.setStroke(strokeWidth, strokeColor, strokeDashWidth, strokeDashGap);

        if (solidColor == null) {
            solidColor = ColorStateList.valueOf(0);
        }
        if (solidColor.isStateful()) {
            rd.setSolidColors(solidColor);
        } else if (pressedRatio > 0.0001f) {
            rd.setSolidColors(csl(solidColor.getDefaultColor(), pressedRatio));
        } else {
            rd.setColor(solidColor.getDefaultColor());
        }
        XOutdatedUtils.setBackground(this,rd);
    }

    // 灰度
    int greyer(int color) {
        int blue = (color & 0x000000FF) >> 0;
        int green = (color & 0x0000FF00) >> 8;
        int red = (color & 0x00FF0000) >> 16;
        int grey = Math.round(red * 0.299f + green * 0.587f + blue * 0.114f);
        return Color.argb(0xff, grey, grey, grey);
    }

    // 明度
    int darker(int color, float ratio) {
        color = (color >> 24) == 0 ? 0x22808080 : color;
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= ratio;
        return Color.HSVToColor(color >> 24, hsv);
    }

    ColorStateList csl(int normal, float ratio) {
        //        int disabled = greyer(normal);
        int pressed = darker(normal, ratio);
        int[][] states = new int[][]{{android.R.attr.state_pressed}, {}};
        int[] colors = new int[]{pressed, normal};
        return new ColorStateList(states, colors);
    }

    private static class RoundDrawable extends GradientDrawable {
        private boolean mIsStadium = false;

        private ColorStateList mSolidColors;
        private int mFillColor;

        public RoundDrawable(boolean isStadium) {
            mIsStadium = isStadium;
        }

        public void setSolidColors(ColorStateList colors) {
            mSolidColors = colors;
            setColor(colors.getDefaultColor());
        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            if (mIsStadium) {
                RectF rect = new RectF(getBounds());
                setCornerRadius((rect.height() > rect.width() ? rect.width() : rect.height()) / 2);
            }
        }

        @Override
        public void setColor(int argb) {
            mFillColor = argb;
            super.setColor(argb);
        }

        @Override
        protected boolean onStateChange(int[] stateSet) {
            if (mSolidColors != null) {
                final int newColor = mSolidColors.getColorForState(stateSet, 0);
                if (mFillColor != newColor) {
                    setColor(newColor);
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean isStateful() {
            return super.isStateful() || (mSolidColors != null && mSolidColors.isStateful());
        }
    }
}
