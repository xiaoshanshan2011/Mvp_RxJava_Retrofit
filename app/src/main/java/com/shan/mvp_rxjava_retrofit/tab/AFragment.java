package com.shan.mvp_rxjava_retrofit.tab;

import android.view.View;
import android.widget.ImageView;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.bean.MovieBean;
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

    private ImageView imageView;

    @Override
    public void loadHeadView(String url) {
        if (imageView == null) {
            imageView = new ImageView(getActivity());
            lvBinding.listView.addHeaderView(imageView);
        }
        ImageCacheUtils.loadImage(getActivity(), url, imageView);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        aPresenter.getMovieData();
    }
}
