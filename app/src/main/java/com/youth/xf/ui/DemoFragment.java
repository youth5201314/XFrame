package com.youth.xf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youth.xf.BaseFragment;
import com.youth.xf.R;
import com.youth.xf.ui.demo.XCacheActivity;
import com.youth.xf.ui.demo.XHttpActivity;
import com.youth.xf.ui.demo.XLoadingDialogActivity;
import com.youth.xf.ui.demo.XLoadingViewActivity;
import com.youth.xf.ui.demo.XLogDemoActivity;
import com.youth.xf.ui.demo.XPermissionDemoActivity;
import com.youth.xf.ui.demo.XRecyclerViewAdapterActivity;
import com.youth.xf.ui.demo.XToastActivity;
import com.youth.xframe.utils.XEncryptUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.NoScrollListView;
import com.youth.xframe.widget.XToast;


public class DemoFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    NoScrollListView listView;
    String[] demo;

    @Override
    public int getLayoutId() {
        return R.layout.demo_fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        demo=getResources().getStringArray(R.array.demo_list);
    }

    @Override
    public void initView() {
        listView= (NoScrollListView) getView().findViewById(R.id.demoList);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        switch (position){
            case 0:
                intent.setClass(getActivity(),XLogDemoActivity.class);
                break;
            case 1:
                intent.setClass(getActivity(),XPermissionDemoActivity.class);
                break;
            case 2:
                intent.setClass(getActivity(),XRecyclerViewAdapterActivity.class);
                break;
            case 3:
                intent.setClass(getActivity(),XLoadingDialogActivity.class);
                break;
            case 4:
                intent.setClass(getActivity(),XToastActivity.class);
                break;
            case 5:
                intent.setClass(getActivity(),XLoadingViewActivity.class);
                break;
            case 6:
                intent.setClass(getActivity(),XCacheActivity.class);
                break;
            case 7:
                intent.setClass(getActivity(), XHttpActivity.class);
        }
        intent.putExtra("title",demo[position]);
        startActivity(intent);
    }

}