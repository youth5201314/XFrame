package com.youth.xf.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import com.youth.banner.Banner;
import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xf.loder.GlideImageLoader;
import com.youth.xf.ui.adapter.MyFragmentPagerAdapter;
import com.youth.xf.view.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    static final int REFRESH_COMPLETE = 0X1112;
    private List<Fragment> fragments;
    private String[] titles = new String[]{"Demo", "API"};
    Banner banner;
    AppBarLayout appBarLayout;
    SwipeRefreshLayout mSwipeLayout;
    TabLayout tabLayout;
    MyFragmentPagerAdapter adapter;
    WrapContentHeightViewPager viewPager;
    static List<String> urls;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    String[] urls = getResources().getStringArray(R.array.url4);
                    List list = Arrays.asList(urls);
                    List arrayList = new ArrayList(list);
                    banner.update(arrayList);
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        String[] url=getResources().getStringArray(R.array.url);
        List list= Arrays.asList(url);
        urls=new ArrayList<String>(list);
        fragments=new ArrayList<>();
        fragments.add(new DemoFragment());
        fragments.add(new APIFragment());
    }

    @Override
    public void initView() {
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        banner = (Banner) findViewById(R.id.banner);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (WrapContentHeightViewPager) findViewById(R.id.viewPager);

        banner.setImages(urls).setImageLoader(new GlideImageLoader()).start();

        tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles));
        tabLayout.setupWithViewPager(viewPager);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.main_color);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    mSwipeLayout.setEnabled(true);
                } else {
                    mSwipeLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

}
