package com.youth.xf.http;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.youth.xframe.utils.http.HttpCallBack;
import com.youth.xframe.utils.http.IHttpEngine;
import com.youth.xframe.utils.http.XHttp;


import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * AsyncHttp简单实现，你可修改，这只是案例
 */
public class AsyncHttpEngine implements IHttpEngine {
    private AsyncHttpClient client;

    public AsyncHttpEngine() {
        client = new AsyncHttpClient();
    }

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        RequestParams requestParams = new RequestParams();
        if(null!=params) {
            for (String key : params.keySet()) {
                requestParams.put(key, params.get(key));
            }
        }
        client.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                Class<?> cls= XHttp.analysisClassInfo(callBack);
                //我这里使用的是fastjson，你也可以用gson，jackjson等
                callBack.onSuccess(JSON.parseObject(result,cls));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onFailed(error.toString());
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> params, final HttpCallBack callBack) {
        RequestParams requestParams = new RequestParams();
        if(null!=params) {
            for (String key : params.keySet()) {
                requestParams.put(key, params.get(key));
            }
        }
        client.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                Class<?> cls= XHttp.analysisClassInfo(callBack);
                //我这里使用的是fastjson，你也可以用gson，jackjson等
                callBack.onSuccess(JSON.parseObject(result,cls));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onFailed(error.toString());
            }
        });
    }
}
