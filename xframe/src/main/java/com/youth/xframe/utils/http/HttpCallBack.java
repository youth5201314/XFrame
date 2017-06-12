package com.youth.xframe.utils.http;


public abstract class HttpCallBack<Result> {

    public abstract void onSuccess(Result result);

    public abstract void onFailed(String error);

}
