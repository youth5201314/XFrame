package com.youth.xf.http;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youth.xframe.utils.http.HttpCallBack;
import com.youth.xframe.utils.http.IHttpEngine;
import com.youth.xframe.utils.http.XHttp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
/**
 * Volley简单实现，你可修改，这只是案例
 */
public class VolleyHttpEngine implements IHttpEngine {

    private static RequestQueue mQueue = null;

    public VolleyHttpEngine(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, final Map<String, Object> params, final HttpCallBack callBack) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //我这里使用的是fastjson，你也可以用gson，jackjson等
                callBack.onSuccess(JSON.parseObject(response,XHttp.analysisClassInfo(callBack)));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error.toString());
            }
        }){
            //传参
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> requestParams=new HashMap<>();
                if(null!=params) {
                    for (String key : params.keySet()) {
                        requestParams.put(key, (String) params.get(key));
                    }
                }
                return requestParams;
            }
            //解决乱码
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = null;
                try {
                    str = new String(response.data, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(str,HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        mQueue.add(request);
    }

    @Override
    public void post(String url, final Map<String, Object> params, final HttpCallBack callBack) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //我这里使用的是fastjson，你也可以用gson，jackjson等
                callBack.onSuccess(JSON.parseObject(response, XHttp.analysisClassInfo(callBack)));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error.toString());
            }
        }){
            //传参
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> requestParams=new HashMap<>();
                if(null!=params) {
                    for (String key : params.keySet()) {
                        requestParams.put(key, (String) params.get(key));
                    }
                }
                return requestParams;
            }
            //解决乱码
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = null;
                try {
                    str = new String(response.data, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(str,HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        mQueue.add(request);
    }
}
