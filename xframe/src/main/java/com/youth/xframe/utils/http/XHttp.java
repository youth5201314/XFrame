package com.youth.xframe.utils.http;


import android.os.Handler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class XHttp implements IHttpEngine {

    private static IHttpEngine http;
    public static Handler handler = new Handler();

    public static void init(IHttpEngine httpEngine){
        http=httpEngine;
    }

    public static XHttp obtain(){
        return new XHttp();
    }

    /**
     * 获取实体类的类型
     * @param obj
     * @return
     */
    public static Class<?> analysisClassInfo(Object obj){
        Type genType=obj.getClass().getGenericSuperclass();
        Type[] params=((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    @Override
    public void get(String url, Map<String, Object> params, HttpCallBack callBack) {
        http.get(url,params,callBack);
    }

    @Override
    public void post(String url, Map<String, Object> params, HttpCallBack callBack) {
        http.post(url,params,callBack);
    }
}
