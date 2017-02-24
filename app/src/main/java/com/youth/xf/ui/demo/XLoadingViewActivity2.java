package com.youth.xf.ui.demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xframe.widget.loadingview.XLoadingView;
import com.youth.xframe.widget.XToast;

public class XLoadingViewActivity2 extends BaseActivity {

    XLoadingView xLoadingView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xloading_view2;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        //第二种方法：
        xLoadingView = (XLoadingView) findViewById(R.id.xloading_view);
        xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToast.success("重新加载");
                xLoadingView.showLoading();
            }
        });
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
