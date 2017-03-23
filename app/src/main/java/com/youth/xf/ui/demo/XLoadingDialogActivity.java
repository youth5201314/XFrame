package com.youth.xf.ui.demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xframe.widget.XLoadingDialog;

public class XLoadingDialogActivity extends BaseActivity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            XLoadingDialog.with(getApplicationContext()).dismiss();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_xloading_dialog;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    public void show(View view) {
        switch (view.getId()){
            case R.id.loading1:
                XLoadingDialog.with(this).show();
                break;
            case R.id.loading2:
                Toast.makeText(this,"3秒后自动取消",Toast.LENGTH_SHORT).show();
                XLoadingDialog.with(this)
                        .setBackgroundColor(Color.parseColor("#aa000000"))
                        .setMessageColor(Color.WHITE)
                        .setCanceled(false)
                        .show();
                handler.sendEmptyMessageDelayed(1,3000);
                break;
            case R.id.loading3:
                XLoadingDialog.with(this)
                        .setOrientation(XLoadingDialog.HORIZONTAL)
                        .setMessage("我正在加载中...")
                        .show();
                break;
            case R.id.loading4:
                XLoadingDialog.with(this)
                        .setCanceled(false)
                        .setOrientation(XLoadingDialog.HORIZONTAL)
                        .setBackgroundColor(Color.parseColor("#aa000000"))
                        .setMessageColor(Color.WHITE)
                        .setMessage("我正在加载中...")
                        .show();
                break;
        }


    }
}
