package com.youth.xframe.widget;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;

public class XColorDrawable extends Drawable {
    private Paint mPaint;
    private int color;
    private RectF rectF;

    public XColorDrawable() {
        mPaint = new Paint();
        // 是否抗锯齿
        mPaint.setAntiAlias(true);
    }

    public XColorDrawable(int color) {
        this.color = color;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public void setColor(@ColorInt int color) {
        this.color = color;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        mPaint.setColor(color);
        canvas.drawRoundRect(rectF, 20, 20, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
