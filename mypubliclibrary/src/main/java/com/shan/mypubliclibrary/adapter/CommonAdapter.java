package com.shan.mypubliclibrary.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 陈俊山 on 2016/3/15.
 * 适用于ListView和GridView
 *
 * @param <T> ViewDataBinding
 * @param <D> Item数据类型
 */
public abstract class CommonAdapter<T extends ViewDataBinding, D> extends BaseAdapter {
    private Context context;
    private List<D> datas;//数据源
    private int res;//布局文件

    public CommonAdapter(Context context, int res, List<D> datas) {
        this.context = context;
        this.datas = datas;
        this.res = res;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public D getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        T binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), res, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (T) convertView.getTag();
        }
        getItem(binding, datas.get(position), position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclick(datas.get(position), position);
            }
        });
        return convertView;
    }

    protected abstract void getItem(T binding, D bean, int position);

    protected abstract void itemOnclick(D bean, int position);

    public void updata(List<D> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}
