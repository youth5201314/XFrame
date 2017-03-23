package com.youth.xframe.utils;


import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 短信的Uri共有一下几种：
 content://sms/inbox    收件箱
 content://sms/sent      已发送
 content://sms/draft    草稿
 content://sms/outbox    发件箱  (正在发送的信息)
 content://sms/failed    发送失败
 content://sms/queued    待发送列表  (比如开启飞行模式后，该短信就在待发送列表里)
 需要权限
 <uses-permission android:name="android.permission.READ_SMS" />
 */
public class XSmsObserver  extends ContentObserver {
    public static final String SMS_URI_INBOX = "content://sms/inbox";
    private Activity activity = null;
    private String smsContent = "";
    private SmsListener listener;
    private String like;
    public XSmsObserver(Activity activity, Handler handler,String like, SmsListener listener) {
        super(handler);
        this.activity = activity;
        this.listener = listener;
        this.like=like;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor cursor = null;
        // 读取收件箱中含有某关键词的短信
        ContentResolver contentResolver = activity.getContentResolver();
        cursor = contentResolver.query(Uri.parse(SMS_URI_INBOX), new String[] {
                        "_id", "address", "body", "read" }, "body like ? and read=?",
                new String[] { "%"+like+"%", "0" }, "date desc");
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                String smsBody = cursor.getString(cursor.getColumnIndex("body"));
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(smsBody.toString());
                smsContent = m.replaceAll("").trim().toString();
                if (!XEmptyUtils.isSpace(smsContent)) {
                    listener.onResult(smsContent);
                }

            }
        }
    }

    /*
     * 短信回调接口
     */
    public interface SmsListener {
        /**
         * 接受sms状态
         *
         * @Title: onResult
         */
        void onResult(String smsContent);
    }
}
