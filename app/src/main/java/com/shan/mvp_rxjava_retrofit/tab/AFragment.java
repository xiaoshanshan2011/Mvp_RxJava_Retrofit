package com.shan.mvp_rxjava_retrofit.tab;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.bean.MovieBean;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentAHeadviewBinding;
import com.shan.mvp_rxjava_retrofit.databinding.ItemBinding;
import com.shan.mvp_rxjava_retrofit.presenter.APresenterImpl;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mvp_rxjava_retrofit.view.AView;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;
import com.shan.mypubliclibrary.utils.ToastUtils;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class AFragment extends BaseFragment<ItemBinding, MovieBean.ShowapiResBodyBean.DatalistBean> implements AView {
    private APresenterImpl aPresenter;

    @Override
    public int bindItemLayout() {
        return R.layout.item;
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();
        aPresenter = new APresenterImpl(this, getActivity());
        aPresenter.getMovieData();
        showPullRefresh();
        initHeadView();
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("框架之美");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
        titleBinding.btnRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void getListVewItem(ItemBinding binding, MovieBean.ShowapiResBodyBean.DatalistBean item, int position) {
        super.getListVewItem(binding, item, position);
        binding.textView4.setText(item.getMovieName());
    }

    @Override
    protected void onItemClick(MovieBean.ShowapiResBodyBean.DatalistBean bean, int position) {
        super.onItemClick(bean, position);
        ToastUtils.toast(bean.getMovieName() + ":" + position);
    }

    @Override
    public void onSuccess(MovieBean movieBean) {
        setData(movieBean.getShowapi_res_body().getDatalist());
        closeRefresh();
    }

    @Override
    public void onFailure(Throwable e) {
        ToastUtils.toast(e.getMessage());
        closeRefresh();
    }

    @Override
    public void loadHeadView(String url) {
        ImageCacheUtils.loadImage(getActivity(), url, mHeadviewBinding.imageView);
    }


    private FragmentAHeadviewBinding mHeadviewBinding;

    /**
     * 初始化头部View
     */
    private void initHeadView() {
        mHeadviewBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_headview, null, false);
        lvBinding.listView.addHeaderView(mHeadviewBinding.getRoot());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        aPresenter.getMovieData();
    }
}
