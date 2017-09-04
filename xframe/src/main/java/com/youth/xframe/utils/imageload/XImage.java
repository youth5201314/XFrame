package com.youth.xframe.utils.imageload;


import android.content.Context;
import android.widget.ImageView;

public class XImage implements ImageLoader {

    private static ImageLoader imageLoader;
    private static XImage xImage;

    public static void init(ImageLoader loader) {
        imageLoader = loader;
    }

    public static XImage getInstance() {
        if (imageLoader==null){
            throw new NullPointerException("Call XFrame.initXImageLoader(ImageLoader loader) within your Application onCreate() method." +
                    "Or extends XApplication");
        }
        if (xImage == null) {
            xImage = new XImage();
        }
        return xImage;
    }

    @Override
    public void load(ImageView imageView, Object imageUrl) {
        imageLoader.load(imageView, imageUrl);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, int defaultImage) {
        imageLoader.load(imageView, imageUrl, defaultImage);
    }

    @Override
    public void load(ImageView imageView, Object imageUrl, Object transformation) {
        imageLoader.load(imageView, imageUrl, transformation);
    }
}
