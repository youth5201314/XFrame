package com.youth.xf;

import com.youth.xframe.base.XApplication;
import com.youth.xframe.XFrame;

public class App extends XApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        XFrame.initXLog().setTag("Test");
    }
}
