package com.youth.xframe.widget.webview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.youth.xframe.utils.XNetworkUtils;

/**
 * WebView 简单封装
 需要先加入权限
 <uses-permission android:name="android.permission.INTERNET"/>
 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 */
public class XWebView extends WebView {
    private Context mContext;

    public XWebView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public XWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        WebSettings mWebSettings = getSettings();
        mWebSettings.setSupportZoom(true);
        // 网页内容的宽度是否可大于WebView控件的宽度
        mWebSettings.setLoadWithOverviewMode(false);
        // 设置此属性，可任意比例缩放。
        mWebSettings.setUseWideViewPort(true);
        // 排版适应屏幕
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        mWebSettings.setJavaScriptEnabled(true);
        // WebView是否支持多个窗口。
        mWebSettings.setSupportMultipleWindows(true);
        //html中的_bank标签就是新建窗口打开，有时会打不开，需要添加下面的代码并复写 WebChromeClient的onCreateWindow方法
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //自动加载图片
        if(Build.VERSION.SDK_INT >= 19) {
            mWebSettings.setLoadsImagesAutomatically(true);
        } else {
            mWebSettings.setLoadsImagesAutomatically(false);
        }
        //缓存数据
        saveData(mWebSettings);
        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);
    }


    /**
     * HTML5数据存储
     */
    private void saveData(WebSettings mWebSettings) {
        // 设置缓存模式
        if (XNetworkUtils.ping()) {
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }
        // 使用localStorage则必须打开
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        // 启动应用缓存
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setAppCachePath(mContext.getCacheDir().getAbsolutePath());
    }

    WebViewClient webViewClient = new WebViewClient() {
        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //在网络情况较差的情况下,等页面finish后再发起图片加载,减少loading时间
            if(!getSettings().getLoadsImagesAutomatically()) {
                getSettings().setLoadsImagesAutomatically(true);
            }
        }

    };

    WebChromeClient webChromeClient = new WebChromeClient() {

        //--start--HTML5定位--
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        //--end--HTML5定位--

        //--start--多窗口的问题--
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();
            return true;
        }
        //--end--多窗口的问题--
    };

    /**
     * XWebview退出时，进行销毁操作
     */
    public void destroy() {
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        clearHistory();
        ((ViewGroup) getParent()).removeView(this);
        destroy();
    }

    private long mOldTime;
    public boolean back(Activity activity,int keyCode, KeyEvent event, String mUrl){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mOldTime < 1500) {
                clearHistory();
                loadUrl(mUrl);
            } else if (canGoBack()) {
                goBack();
            } else {
                activity.finish();
            }
            mOldTime = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
