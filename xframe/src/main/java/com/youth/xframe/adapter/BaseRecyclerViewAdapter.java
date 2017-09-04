package com.youth.xframe.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> dataLists;

    public BaseRecyclerViewAdapter(){
        this(new ArrayList<T>());
    }

    public BaseRecyclerViewAdapter(@NonNull List<T> dataLists){
        this.dataLists = dataLists;
    }

    /**
     * 获取指定位置的数据
     * @param position
     * @return
     */
    public T getItem(int position){
        return dataLists.get(position);
    }

    /**
     * 获取数据集合
     * @return
     */
    public List<T> getDataLists(){
        return dataLists;
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（会清空以前的集合数据）
     * @param datas
     */
    public void setDataLists(List<T> datas) {
        dataLists.clear();
        if (datas != null && !datas.isEmpty()) {
            dataLists.addAll(datas);
        }
        notifyDataSetChanged();
    }
    /**
     * 添加数据条目
     *
     * @param data
     */
    public void add(T data) {
        add(dataLists.indexOf(data),data);
    }
    /**
     * 在指定位置添加数据条目
     *
     * @param position
     * @param data
     */
    public void add(int position, T data) {
        dataLists.add(position,data);
        notifyItemInserted(position);
    }
    /**
     * 添加数据条目集合
     *
     * @param datas
     */
    public void addAll(List<T> datas) {
        dataLists.addAll(datas);
        notifyDataSetChanged();
    }
    /**
     * 在指定位置添加数据条目集合
     *
     * @param position
     * @param datas
     */
    public void addAll(int position,List<T> datas) {
        dataLists.addAll(position,datas);
        notifyDataSetChanged();
    }
    /**
     * 删除指定索引数据条目
     *
     * @param position
     */
    public void remove(int position) {
        dataLists.remove(position);
        notifyItemRemoved(position);
    }
    /**
     * 删除指定数据条目
     *
     * @param data
     */
    public void remove(T data) {
        remove(dataLists.indexOf(data));
    }


    /**
     * 替换指定索引的数据条目
     *
     * @param position
     * @param newData
     */
    public void replace(int position, T newData) {
        dataLists.set(position, newData);
        notifyItemChanged(position);
    }

    /**
     * 替换指定数据条目
     *
     * @param oldData
     * @param newData
     */
    public void replace(T oldData, T newData) {
        replace(dataLists.indexOf(oldData), newData);
    }

    /**
     * 交换两个数据条目的位置
     *
     * @param fromPosition
     * @param toPosition
     */
    public void move(int fromPosition, int toPosition) {
        Collections.swap(dataLists, fromPosition, toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    /**
     * 清空
     */
    public void clear() {
        dataLists.clear();
        notifyDataSetChanged();
    }


}
