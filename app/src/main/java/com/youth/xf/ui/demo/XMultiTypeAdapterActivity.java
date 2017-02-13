package com.youth.xf.ui.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xf.bean.News;
import com.youth.xf.data.NewDataSource;
import com.youth.xf.ui.adapter.StickyHeaderAdapter;
import com.youth.xf.view.GlideCircleTransform;
import com.youth.xframe.XFrame;
import com.youth.xframe.adapter.XRecyclerViewAdapter;
import com.youth.xframe.adapter.XViewHolder;
import com.youth.xframe.adapter.decoration.DividerDecoration;
import com.youth.xframe.adapter.decoration.SpaceDecoration;
import com.youth.xframe.adapter.decoration.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XMultiTypeAdapterActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_xrecycler_view_adapter;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView() {
        SwipeRefreshLayout mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeLayout.setEnabled(false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerDecoration(Color.parseColor("#f2f2f2"),15));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final MultiTypeAdapter adapter = new MultiTypeAdapter(recyclerView, NewDataSource.getNewsList());
        recyclerView.setAdapter(adapter);

        // StickyHeader
        StickyHeaderDecoration decoration = new StickyHeaderDecoration(new StickyHeaderAdapter(this));
        decoration.setIncludeHeader(false);
        recyclerView.addItemDecoration(decoration);

        adapter.setOnItemClickListener(new XRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //修改局部item的数据，点击那个修改哪个
                int random = new Random().nextInt(9);
                News news=NewDataSource.getNewsList().get(random);
                adapter.replace(position,news);
            }
        });
    }

    class MultiTypeAdapter extends XRecyclerViewAdapter<News> {

        public MultiTypeAdapter(@NonNull RecyclerView mRecyclerView, List<News> dataLists) {
            super(mRecyclerView, dataLists);
        }

        @Override
        public int getItemLayoutResId(News data, int position) {
            int layoutResId = -1;
            switch (data.getNewsType()){
                case News.TYPE_NONE_PICTURE:
                    layoutResId = R.layout.dome_item;
                    break;
                case News.TYPE_SINGLE_PICTURE:
                    layoutResId = R.layout.dome_image_item;
                    break;
            }
            return layoutResId;
        }

        @Override
        public void bindData(XViewHolder holder, News data, int position) {
            switch (data.getNewsType()){
                case News.TYPE_NONE_PICTURE:
                    holder.setText(R.id.text, data.getTitle());
                    break;
                case News.TYPE_SINGLE_PICTURE:
                    holder.setText(R.id.newTitle, data.getTitle())
                            .setText(R.id.newAuthor,data.getAuthor())
                            .setText(R.id.newTime,data.getTime());
                    ImageView view=holder.getView(R.id.newImage);
                    Glide.with(XFrame.getContext())
                            .load(data.getImageUrl())
                            .crossFade()
                            .transform(new GlideCircleTransform(XFrame.getContext()))
                            .into(view);
                    break;
            }
        }
    }
}
