package com.youth.xf;

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
    }
}
