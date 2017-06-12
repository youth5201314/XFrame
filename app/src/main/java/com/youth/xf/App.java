package com.youth.xf;

import com.youth.xf.http.AsyncHttpEngine;
import com.youth.xf.http.OKHttpEngine;
import com.youth.xframe.base.XApplication;
import com.youth.xframe.XFrame;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

public class App extends XApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);

        XFrame.initXLog();
        XFrame.initXLoadingView()
                .setErrorViewResId(R.layout._loading_layout_error);

        /**
             初始化网络请求的引擎,在这里可以一行代码切换，避免更换网络框架麻烦的问题
             提供三种常见框架的简单案例：（你也可以按照例子自己实现）
             AsyncHttpEngine、OKHttpEngine、VolleyHttpEngine
         */
        XFrame.initXHttp(new AsyncHttpEngine());
    }
}
