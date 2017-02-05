package com.shan.mypubliclibrary.ui.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
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
import com.shan.mypubliclibrary.manager.StatusBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈俊山 on 4/7/2559.
 */
public abstract class LibActivity<T extends ViewDataBinding, D> extends FragmentActivity implements BindListener, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getName();
    protected T mBinding;//绑定布局文件并执行常用方法
    protected ListviewLayoutBinding lvBinding;//当子类是列表的时候这个才可用
    protected TitletarLayoutBinding titleBinding;//头部布局

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横竖屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBar.showStatusBar(this, R.color.color_0062fd);
        //实例化一个线性布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //Title部分
        titleBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.titletar_layout, null, false);
        LinearLayout.LayoutParams title_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(titleBinding.getRoot(), title_params);
        //Content部分
        if (bindLayout() != 0) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), bindLayout(), null, false);
            LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayout.addView(mBinding.getRoot(), content_params);
        } else if (bindItemLayout() != 0) {
            lvBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.listview_layout, null, false);
            lvBinding.refreshLayout.setEnabled(false);//关闭下拉刷新
            LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayout.addView(lvBinding.getRoot(), content_params);
        }
        setContentView(linearLayout);
        initOnCreate();
        initTitleBar();
        getDatas();
        bindDatas();
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
            adapter = new CommonAdapter<T, D>(this, bindItemLayout(), datas) {

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
     * 开启下拉刷新
     */
    public void showPullRefresh() {
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
    protected void onPause() {
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
        finish();
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
