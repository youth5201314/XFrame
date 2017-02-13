package com.youth.xframe.base;

import android.content.Context;
import android.widget.ImageView;


/**
 * 框架全局图片加载配置
 * 
 * @author 王兴春
 * @email 1028729086@qq.com
 * @time 2017/1/10 10:16
 */
public interface XImageLoader<T> {
    void load(Context context, ImageView imageView, T imageUrl);
}
