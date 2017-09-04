package com.youth.xf.loder;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.youth.xf.view.GlideCircleTransform;
import com.youth.xframe.XFrame;
import com.youth.xframe.utils.imageload.ImageLoader;


public class GlideImageLoader implements ImageLoader {

    private Context mContext;

    public static GlideCircleTransform circleTransform;

    public GlideImageLoader(Context context) {
        this.mContext=context;
        circleTransform=new GlideCircleTransform(mContext);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl) {
        Glide.with(mContext)
                .load(imageUrl)
                .crossFade()
                .into(imageView);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, int defaultImage) {
        Glide.with(mContext)
                .load(imageUrl)
                .crossFade()
                .placeholder(defaultImage)
                .into(imageView);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, Object transformation) {
        Glide.with(mContext)
                .load(imageUrl)
                .crossFade()
                .transform((BitmapTransformation) transformation)
                .into(imageView);
    }
}
