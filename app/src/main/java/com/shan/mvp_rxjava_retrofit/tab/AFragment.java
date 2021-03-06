package com.shan.mvp_rxjava_retrofit.tab;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shan.amaplibrary.location.LocationListener;
import com.shan.mvp_rxjava_retrofit.MyApp;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.adapter.BannerAdapter;
import com.shan.mvp_rxjava_retrofit.bean.BannerBean;
import com.shan.mvp_rxjava_retrofit.bean.MainTypeBean;
import com.shan.mvp_rxjava_retrofit.bean.MovieBean;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentACommodityLayoutBinding;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentATypeItemLayoutBinding;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentATypeLayoutBinding;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentAViewpagerBinding;
import com.shan.mvp_rxjava_retrofit.databinding.ItemBinding;
import com.shan.mvp_rxjava_retrofit.presenter.APresenterImpl;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mvp_rxjava_retrofit.ui.fragment.SearchFragment;
import com.shan.mvp_rxjava_retrofit.view.AView;
import com.shan.mypubliclibrary.adapter.CommonAdapter;
import com.shan.mypubliclibrary.ui.activity.CommonActivity;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;
import com.shan.mypubliclibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.shan.mvp_rxjava_retrofit.R.layout.item;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class AFragment extends BaseFragment<ItemBinding, MovieBean.ShowapiResBodyBean.DatalistBean> implements AView, View.OnClickListener, LocationListener {
    private APresenterImpl aPresenter;

    @Override
    public int bindItemLayout() {
        return item;
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
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
        titleBinding.btnRight.setVisibility(View.VISIBLE);
        titleBinding.btnRight.setImageResource(R.mipmap.ic_search);
        titleBinding.tvLeft.setVisibility(View.VISIBLE);
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.ic_arrow_botton);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleBinding.tvLeft.setCompoundDrawablePadding(5);
        titleBinding.tvLeft.setCompoundDrawables(null, null, drawable, null);
        titleBinding.btnRight.setOnClickListener(this);
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
        List<MovieBean.ShowapiResBodyBean.DatalistBean> datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datalist.add(new MovieBean.ShowapiResBodyBean.DatalistBean("heheda"));
        }
        commodity(datalist);
        setData(datalist);
        closeRefresh();
    }

    @Override
    public void onFailure(Throwable e) {
        ToastUtils.toast(e.getMessage());
        closeRefresh();
    }

    private List<BannerBean> bannerItems;
    private String[] images = {
            "http://img7.soufunimg.com/zxb/2015_04/21/M09/07/E1/wKgEKlU2ApKIHrKIAARFV4BL0VQAAMUBQGEI3MABEVv973.jpg",
            "http://img004.hc360.cn/y1/M01/61/F5/wKhQc1RzAPqEVbUeAAAAANqj46A164.jpg",
            "http://pic.66zhuang.com/zxrj/pics/image/2015-03-26/0afc1db5f7b42f8d221ee52f4a31f495.jpg",
            "http://img0.pchouse.com.cn/pchouse/1408/06/789311_1.jpg"
    };

    @Override
    public void loadViewPager() {
        bannerItems = new ArrayList<>();
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

    private String[] typeImages = {
            "http://image2.cnpp.cn/upload/images/20150311/11075627589_800x550.jpg",
            "http://img.0731fdc.com/home/photo/prim/20160215_093006_600.jpg",
            "http://pic.qiantucdn.com/58pic/18/06/56/76w58PICtNC_1024.jpg",
            "http://image8.huangye88.com/2015/04/05/764fe006fae80583.jpg",
            "http://pic1.huitu.com/res/20110727/1370_20110727022653781200_1.jpg",
            "http://img.tuku.cn/file_big/201504/88361efc88bc44088a3cf69a01b58766.jpg",
            "http://pic.58pic.com/58pic/14/75/32/89Y58PIC3Kt_1024.jpg",
            "http://pic.58pic.com/58pic/14/75/32/89Y58PIC3Kt_1024.jpg"
    };
    private String[] names = {"瓷砖", "水泥", "沙子", "钢筋", "砖块", "花", "门窗", "更多"};

    @Override
    public void initType() {
        List<MainTypeBean> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new MainTypeBean(typeImages[i], names[i]));
        }

        mTypeLayoutBinding.baseGridView.setAdapter(new CommonAdapter<FragmentATypeItemLayoutBinding, MainTypeBean>(getActivity(), R.layout.fragment_a_type_item_layout, list) {
            @Override
            protected void getItem(FragmentATypeItemLayoutBinding binding, MainTypeBean bean, int position) {
                ImageCacheUtils.loadImage(getActivity(), bean.getImage(), 60, 60, binding.circleImageView);
                binding.tvName.setText(bean.getName());
            }

            @Override
            protected void itemOnclick(MainTypeBean bean, int position) {
                ToastUtils.toast("功能待实现...");
            }
        });
    }

    @Override
    public void commodity(List<MovieBean.ShowapiResBodyBean.DatalistBean> datalist) {
        mCommodityLayoutBinding.baseGridView.setAdapter(new CommonAdapter<ItemBinding, MovieBean.ShowapiResBodyBean.DatalistBean>(getActivity(), item, datalist) {
            @Override
            protected void getItem(ItemBinding binding, MovieBean.ShowapiResBodyBean.DatalistBean bean, int position) {
                binding.textView4.setText(bean.getMovieName());
            }

            @Override
            protected void itemOnclick(MovieBean.ShowapiResBodyBean.DatalistBean bean, int position) {

            }
        });
    }

    private FragmentAViewpagerBinding mViewpagerBinding;//头部ViewPager
    private FragmentATypeLayoutBinding mTypeLayoutBinding;//分类
    private FragmentACommodityLayoutBinding mCommodityLayoutBinding;//分类

    /**
     * 初始化头部View
     */
    private void initHeadView() {
        mViewpagerBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_viewpager, null, false);
        lvBinding.listView.addHeaderView(mViewpagerBinding.getRoot());

        mTypeLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_type_layout, null, false);
        lvBinding.listView.addHeaderView(mTypeLayoutBinding.getRoot());

        mCommodityLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_commodity_layout, null, false);
        lvBinding.listView.addHeaderView(mCommodityLayoutBinding.getRoot());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        aPresenter.getMovieData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_right:
                Intent intent = new Intent(getActivity(), CommonActivity.class);
                intent.putExtra(CommonActivity.FRAGMENT_CLASS, SearchFragment.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MyApp.getInstance().getLocationManager().startLocation(this);
    }

    @Override
    public void locationSuccess(AMapLocation location) {
        titleBinding.tvLeft.setText(location.getCity());
    }

    @Override
    public void locationFailure() {

    }
}
