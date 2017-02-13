package com.youth.xframe.base;

import android.os.Bundle;


public interface ICallback {
    //返回布局文件id
    int getLayoutId();
    //初始化数据
    void initData(Bundle savedInstanceState);
    //初始化布局文件
    void initView();
}
