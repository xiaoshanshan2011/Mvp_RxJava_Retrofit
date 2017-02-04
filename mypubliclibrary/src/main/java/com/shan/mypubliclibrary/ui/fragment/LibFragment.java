package com.shan.mypubliclibrary.ui.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shan.mypubliclibrary.R;
import com.shan.mypubliclibrary.adapter.CommonAdapter;
import com.shan.mypubliclibrary.databinding.ListviewLayoutBinding;
import com.shan.mypubliclibrary.databinding.TitletarLayoutBinding;
import com.shan.mypubliclibrary.listener.BindListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈俊山 on 2016/8/30.
 *
 * @param <T> ViewDataBinding
 * @param <D> ListVIew Item数据类型
 */

public abstract class LibFragment<T extends ViewDataBinding, D> extends Fragment implements BindListener, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getName();
    protected ListviewLayoutBinding lvBinding;//当子类是列表的时候这个才可用
    protected T mBinding;//内容布局
    protected TitletarLayoutBinding titleBinding;//头部布局
    private LinearLayout linearLayout = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TitleBar
        titleBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.titletar_layout, null, false);
        LinearLayout.LayoutParams title_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //Content
        if (bindLayout() != 0) {
            if (linearLayout == null) {
                linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(titleBinding.getRoot(), title_params);

                mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), bindLayout(), container, false);
                LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                linearLayout.addView(mBinding.getRoot(), content_params);
                initOnCreate();
                initTitleBar();
                getDatas();
                bindDatas();
            }
        } else if (bindItemLayout() != 0) {
            if (linearLayout == null) {
                linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(titleBinding.getRoot(), title_params);

                lvBinding = DataBindingUtil.inflate(inflater, R.layout.listview_layout, container, false);
                lvBinding.refreshLayout.setEnabled(false);//关闭下拉刷新
                LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                linearLayout.addView(lvBinding.getRoot(), content_params);
                initOnCreate();
                initTitleBar();
                getDatas();
                bindDatas();
            }
        } else {
            return null;
        }

        ViewGroup parent = (ViewGroup) linearLayout.getParent();
        if (parent != null) {
            parent.removeView(linearLayout);
        }
        return linearLayout;
    }

    @Override
    public void initOnCreate() {

    }

    @Override
    public void getDatas() {

    }

    @Override
    public void bindDatas() {

    }

    @Override
    public int bindItemLayout() {
        return 0;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    /**
     * 获取ListView中的每一个Item
     *
     * @param binding
     * @param item
     */
    protected void getListVewItem(T binding, D item, int position) {
    }

    /**
     * 获取ListViewd Item的每个点击事件
     *
     * @param bean
     * @param position
     */
    protected void onItemClick(D bean, int position) {
    }

    protected CommonAdapter<T, D> adapter = null;

    public void setData(List<D> datas) {
        if (lvBinding == null) {
            return;
        }

        if (datas == null && lvBinding != null) {
            datas = new ArrayList<>();
        }

        if (adapter == null) {
            adapter = new CommonAdapter<T, D>(getActivity(), bindItemLayout(), datas) {

                @Override
                protected void getItem(T binding, D bean, int position) {
                    getListVewItem(binding, bean, position);
                }

                @Override
                protected void itemOnclick(D bean, int position) {
                    onItemClick(bean, position);
                }
            };
            lvBinding.listView.setAdapter(adapter);
        } else {
            adapter.updata(datas);
        }
    }

    /**
     * 初始化下拉刷新
     */
    private void initPullRefresh() {
        //关闭下拉刷新
        lvBinding.refreshLayout.setEnabled(false);
    }

    /**
     * 开启下拉刷新
     */
    public void showPullRefresh() {
        if (lvBinding == null) {
            return;
        }
        lvBinding.refreshLayout.setColorSchemeResources(
                R.color.light_sea_green,
                R.color.light_pink,
                R.color.light_sea_green,
                R.color.light_pink);
        lvBinding.refreshLayout.setEnabled(true);//开启下拉刷新
        lvBinding.refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (lvBinding != null && lvBinding.refreshLayout.isRefreshing()) {
            lvBinding.refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setTitleBarVisibility(int visibility) {
        titleBinding.getRoot().setVisibility(visibility);
    }

    public void setTitle(String text) {
        titleBinding.tvTitle.setText(text);
    }

    public void setTitleRightIcon(int iconRes) {
        titleBinding.btnRight.setImageResource(iconRes);
    }

    public void setTitleLeftIcon(int iconRes) {
        titleBinding.btnLeft.setImageResource(iconRes);
    }

    @Override
    public void initTitleBar() {
        titleBinding.btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLeft(view);
            }
        });
        titleBinding.btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRight(view);
            }
        });
    }

    /**
     * TitleBar左边的点击事件
     *
     * @param view
     */
    @Override
    public void onClickLeft(View view) {
        getActivity().finish();
    }

    /**
     * TitleBar右边的点击事件
     *
     * @param view
     */
    @Override
    public void onClickRight(View view) {

    }

    @Override
    public void closeRefresh() {
        if (lvBinding != null) {
            lvBinding.refreshLayout.setRefreshing(false);
        }
    }
}
