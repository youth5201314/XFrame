package com.youth.xf.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments,String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position % titles.length];

    }
}