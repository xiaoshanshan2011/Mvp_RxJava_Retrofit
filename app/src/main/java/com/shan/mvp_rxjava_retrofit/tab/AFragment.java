package com.shan.mvp_rxjava_retrofit.tab;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.adapter.BannerAdapter;
import com.shan.mvp_rxjava_retrofit.bean.BannerBean;
import com.shan.mvp_rxjava_retrofit.bean.MovieBean;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentAViewpagerBinding;
import com.shan.mvp_rxjava_retrofit.databinding.ItemBinding;
import com.shan.mvp_rxjava_retrofit.presenter.APresenterImpl;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mvp_rxjava_retrofit.view.AView;
import com.shan.mypubliclibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

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
        setTitle("建房网");
        titleBinding.btnLeft.setVisibility(View.GONE);
        titleBinding.btnRight.setImageResource(R.mipmap.ic_search);
        titleBinding.tvLeft.setVisibility(View.VISIBLE);
        titleBinding.tvLeft.setText("深圳");
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.ic_arrow_botton);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleBinding.tvLeft.setCompoundDrawablePadding(10);
        titleBinding.tvLeft.setCompoundDrawables(null, null, drawable, null);
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

    private String[] images = {
            "http://pic3.zhimg.com/da1fcaf6a02d1223d130d5b106e828b9.jpg",
            "http://p1.zhimg.com/dd/f1/ddf10a04227ea50fd59746dbcd13c728.jpg",
            "http://p3.zhimg.com/64/5c/645cde143c9a371005f3f749366cffad.jpg"
    };

    @Override
    public void loadViewPager() {


        List<BannerBean> bannerItems = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            bannerItems.add(new BannerBean("第" + i + "张", images[i]));
        }

        mViewpagerBinding.convenientBanner.setPages(new CBViewHolderCreator<BannerAdapter>() {
            @Override
            public BannerAdapter createHolder() {
                return new BannerAdapter();
            }
        }, bannerItems)
                .setPageIndicator(new int[]{R.mipmap.ic_point_nomal, R.mipmap.ic_point_focused})
                .startTurning(2000)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtils.toast("position" + position);
                    }
                });
    }

    private FragmentAViewpagerBinding mViewpagerBinding;

    /**
     * 初始化头部View
     */
    private void initHeadView() {
        mViewpagerBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_viewpager, null, false);
        lvBinding.listView.addHeaderView(mViewpagerBinding.getRoot());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        aPresenter.getMovieData();
    }
}
