package com.youth.xf.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xframe.widget.loadingview.XLoadingView;
import com.youth.xframe.widget.XToast;

public class XLoadingViewActivity extends BaseActivity {


    XLoadingView xLoadingView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xloading_view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        //包裹并替换内容元素：这种方法不一定兼容各种场景，如果不行请用第二种方法
        xLoadingView = XLoadingView.wrap(this);
        xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToast.success("重新加载");
                xLoadingView.showLoading();
            }
        });
    }

    public void show(View view){
        //第二种使用方法
        startActivity(new Intent(this,XLoadingViewActivity2.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_empty:
                xLoadingView.showEmpty();
                return true;
            case R.id.action_loading:
                xLoadingView.showLoading();
                return true;
            case R.id.action_content:
                xLoadingView.showContent();
                return true;
            case R.id.action_error:
                xLoadingView.showError();
                return true;
            case R.id.action_network_error:
                xLoadingView.showNoNetwork();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loading, menu);
        return true;
    }

}
