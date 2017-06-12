package com.youth.xframe.utils.http;

import java.util.Map;

public interface IHttpEngine {

    void get(String url, Map<String,Object> params,HttpCallBack callBack);

    void post(String url, Map<String,Object> params,HttpCallBack callBack);
}
