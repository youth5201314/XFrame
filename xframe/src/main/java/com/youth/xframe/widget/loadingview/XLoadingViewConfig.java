package com.youth.xframe.widget.loadingview;


import android.support.annotation.LayoutRes;

import com.youth.xframe.R;

public class XLoadingViewConfig {

    private int emptyViewResId = R.layout.xloading_empty_view;
    private int errorViewResId = R.layout.xloading_error_view;
    private int loadingViewResId = R.layout.xloading_loading_view;
    private int noNetworkViewResId = R.layout.xloading_no_network_view;

    public int getEmptyViewResId() {
        return emptyViewResId;
    }

    public XLoadingViewConfig setEmptyViewResId(@LayoutRes int emptyViewResId) {
        this.emptyViewResId = emptyViewResId;
        return this;
    }

    public int getErrorViewResId() {
        return errorViewResId;
    }

    public XLoadingViewConfig setErrorViewResId(@LayoutRes int errorViewResId) {
        this.errorViewResId = errorViewResId;
        return this;
    }

    public int getLoadingViewResId() {
        return loadingViewResId;
    }

    public XLoadingViewConfig setLoadingViewResId(@LayoutRes int loadingViewResId) {
        this.loadingViewResId = loadingViewResId;
        return this;
    }

    public int getNoNetworkViewResId() {
        return noNetworkViewResId;
    }

    public XLoadingViewConfig setNoNetworkViewResId(@LayoutRes int noNetworkViewResId) {
        this.noNetworkViewResId = noNetworkViewResId;
        return this;
    }
}
