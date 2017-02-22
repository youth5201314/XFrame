package com.youth.xf.ui.demo;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xf.ui.MainActivity;
import com.youth.xframe.utils.permission.XPermission;

import java.util.Calendar;

/**
 * 使用方法：
 以拨打电话为例
 1、首先AndroidManifest中配置必要的权限
 <uses-permission android:name="android.permission.CALL_PHONE"/>
 2、继承XActivity
 3、调用工具类方法
 XPermission.requestPermissions(Context context, int requestCode, String[] permissions, OnPermissionListener listener)
 这里主要注意这个Context必需是一个Activity
 如果在Activity中可以传this;
 如果在Fragment中传getActivity();
 如果在View中传getContext();
 */
public class XPermissionDemoActivity extends BaseActivity implements View.OnClickListener {
    private Button button1,button2,button3;
    @Override
    public int getLayoutId() {
        return R.layout.activity_xpermission_demo;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                doCallPhone();
                break;
            case R.id.button2:
                doCamera();
                break;
            case R.id.button3:
                sendPermission();
                break;

        }
    }

    /**
     * 拨打电话
     */
    private void doCallPhone() {
        XPermission.requestPermissions(this, 100, new String[]{Manifest.permission
                .CALL_PHONE}, new XPermission.OnPermissionListener() {
            //权限申请成功时调用
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:18682555854"));
                startActivity(intent);
            }
            //权限被用户禁止时调用
            @Override
            public void onPermissionDenied() {
                //给出友好提示，并且提示启动当前应用设置页面打开权限
                XPermission.showTipsDialog(XPermissionDemoActivity.this);
            }
        });
    }

    /**
     * 照相
     */
    private void doCamera() {
        XPermission.requestPermissions(this, 101, new String[]{Manifest.permission
                .CAMERA}, new XPermission.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }

            @Override
            public void onPermissionDenied() {
                XPermission.showTipsDialog(XPermissionDemoActivity.this);
            }
        });
    }
    /**
     * 多个权限
     */
    private void sendPermission() {
        XPermission.requestPermissions(this, 102, new String[]{
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.WRITE_CONTACTS
        }, new XPermission.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplication(),"申请成功！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied() {
                XPermission.showTipsDialog(XPermissionDemoActivity.this);
            }
        });
    }
}
