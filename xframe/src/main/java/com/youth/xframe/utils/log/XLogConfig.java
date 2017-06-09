package com.youth.xframe.utils.log;


import android.text.TextUtils;

import com.youth.xframe.XFrame;
import com.youth.xframe.utils.XPrintUtils;


public class XLogConfig {

    private boolean showThreadInfo = true;
    private boolean debug = XFrame.isDebug;
    private String tag = XFrame.tag;


    public XLogConfig setTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            this.tag = tag;
        }
        return this;
    }

    public XLogConfig setShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }


    public XLogConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }
}
