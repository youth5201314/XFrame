package com.youth.xf.http;

import com.alibaba.fastjson.JSON;
import com.youth.xframe.utils.http.HttpCallBack;
import com.youth.xframe.utils.http.IHttpEngine;
import com.youth.xframe.utils.http.XHttp;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHttp简单实现，你可修改，这只是案例
 *
 */
public class OKHttpEngine implements IHttpEngine {

    private OkHttpClient client = null;

    public OKHttpEngine() {
        client = new OkHttpClient();
    }

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    XHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //我这里使用的是fastjson，你也可以用gson，jackjson等
                            callBack.onSuccess(JSON.parseObject(result,XHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    XHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(response.message().toString());
                        }
                    });
                }

            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> params, final HttpCallBack callBack) {
        FormBody.Builder body = new FormBody.Builder();
        if(null!=params) {
            for (String key:params.keySet()) {
                body.add(key, (String) params.get(key));
            }
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    XHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //我这里使用的是fastjson，你也可以用gson，jackjson等
                            callBack.onSuccess(JSON.parseObject(result,XHttp.analysisClassInfo(callBack)));
                        }
                    });
                } else {
                    XHttp.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(response.message().toString());
                        }
                    });
                }

            }
        });
    }

}
