package com.youth.xf.ui.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xf.data.Weather;
import com.youth.xframe.utils.http.HttpCallBack;
import com.youth.xframe.utils.http.XHttp;
import com.youth.xframe.utils.log.XLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class XHttpActivity extends BaseActivity {

    TextView text;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xhttp;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        text = (TextView) findViewById(R.id.text);
    }

    public void onClick(View view) {
        request();
    }

    /**
     *
     */
    public void request() {
        //这里是公用接口，没有办法演示传参，谅解
        String url = "http://wthrcdn.etouch.cn/weather_mini?citykey=101010100";
        /**
         * 调用 网络请求 get方法
         * @param url 请求url
         * @param params 参数，没有可以传一个空集合 or null
         * @param HttpCallBack<T> 回调接口，T 为你想返回的类型（String or entity）
         */
        XHttp.obtain().get(url, null, new HttpCallBack<Weather>() {
            //成功返回你传入的返回类型
            @Override
            public void onSuccess(Weather weather) {
                text.setText(
                        weather.getData().getCity() + " \n温度：" +
                        weather.getData().getWendu() + "度 \n 提示：" +
                        weather.getData().getGanmao()
                );
            }

            @Override
            public void onFailed(String str) {
                text.setText(str);
            }
        });
    }

}
