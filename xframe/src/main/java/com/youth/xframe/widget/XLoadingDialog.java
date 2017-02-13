package com.youth.xframe.widget;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.youth.xframe.R;
import com.youth.xframe.XFrame;

public class XLoadingDialog extends Dialog {
    private static XLoadingDialog dialog;
    private Context context;
    private TextView xTextView;

    public XLoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xloading_dialog);
        xTextView = (TextView) findViewById(R.id.loading_message);
    }

    public static XLoadingDialog with(Context context){
        if (dialog==null){
            dialog=new XLoadingDialog(context);
        }
        return dialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (dialog!=null)
            dialog=null;
    }

    public XLoadingDialog setCanceled(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        setCancelable(cancel);
        return dialog;
    }

    public XLoadingDialog setMessage(String message) {
        if (xTextView != null && !TextUtils.isEmpty(message)) {
            xTextView.setText(message);
        }
        return this;
    }
}
