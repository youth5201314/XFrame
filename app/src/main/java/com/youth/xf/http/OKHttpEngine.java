package com.youth.xf.http;

import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.youth.xframe.XFrame;
import com.youth.xframe.utils.http.HttpCallBack;
import com.youth.xframe.utils.http.IHttpEngine;
import com.youth.xframe.utils.http.XHttp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.internal.Util.EMPTY_REQUEST;

/**
 * OKHttp简单实现，你可修改，这只是案例
 */
public class OKHttpEngine implements IHttpEngine {

    private OkHttpClient client = null;
    private int cacheSize = 10 * 1024 * 1024;

    public OKHttpEngine() {
        client = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(new Cache(XFrame.getContext().getCacheDir(), cacheSize))
                .build();
    }

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        Request request = new Request.Builder().url(url+getUrlParamsByMap(params)).build();
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
                            callBack.onSuccess(JSON.parseObject(result, XHttp.analysisClassInfo(callBack)));
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
        RequestBody body = EMPTY_REQUEST;
        if (null != params&&!params.isEmpty()) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    builder.addFormDataPart(key, file.getName(),
                            RequestBody.create(MediaType.parse("image/png"), file));//"application/octet-stream"
                }
                if (value instanceof ArrayList) {
                    ArrayList<Object> lists = (ArrayList) value;
                    for (Object obj : lists) {
                        if (obj instanceof File) {
                            File file = (File) obj;
                            builder.addFormDataPart(key, file.getName(),
                                    RequestBody.create(MediaType.parse("image/png"), file));
                        }
                    }
                } else {
                    builder.addFormDataPart(key, value.toString());
                }
            }
            body = builder.build();
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body)
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
                            callBack.onSuccess(JSON.parseObject(result, XHttp.analysisClassInfo(callBack)));
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

    private String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null||map.isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer("?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.append(entry.getKey());
            params.append("=");
            params.append(entry.getValue());
            params.append("&");
        }
        String str=params.toString();
        return str.substring(0,str.length()-1);
    }
}
