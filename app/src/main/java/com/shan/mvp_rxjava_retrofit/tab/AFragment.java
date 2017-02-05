package com.shan.mvp_rxjava_retrofit.tab;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.adapter.BannerAdapter;
import com.shan.mvp_rxjava_retrofit.bean.BannerBean;
import com.shan.mvp_rxjava_retrofit.bean.MainTypeBean;
import com.shan.mvp_rxjava_retrofit.bean.MovieBean;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentATypeItemLayoutBinding;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentATypeLayoutBinding;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentAViewpagerBinding;
import com.shan.mvp_rxjava_retrofit.databinding.ItemBinding;
import com.shan.mvp_rxjava_retrofit.presenter.APresenterImpl;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mvp_rxjava_retrofit.view.AView;
import com.shan.mypubliclibrary.adapter.CommonAdapter;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;
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
        titleBinding.tvLeft.setText("深圳");
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.ic_arrow_botton);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleBinding.tvLeft.setCompoundDrawablePadding(5);
        titleBinding.tvLeft.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    protected void getListVewItem(ItemBinding binding, MovieBean.ShowapiResBodyBean.DatalistBean item, int position) {
        super.getListVewItem(binding, item, position);
        binding.textView4.setText(item.getMovieName());
        if (position % 2 == 0) {
            ImageCacheUtils.loadImage(getActivity(), "http://p5.qhmsg.com/dr/220__/t0161be18d99eab2224.jpg", binding.imageView);
        } else {
            ImageCacheUtils.loadImage(getActivity(), "http://www.2cto.com/uploadfile/2014/0725/20140725080303807.jpg", binding.imageView);
        }

    }

    @Override
    protected void onItemClick(MovieBean.ShowapiResBodyBean.DatalistBean bean, int position) {
        super.onItemClick(bean, position);
        ToastUtils.toast(bean.getMovieName() + ":" + position);
    }

    @Override
    public void onSuccess(MovieBean movieBean) {
        List<MovieBean.ShowapiResBodyBean.DatalistBean> datalist = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            datalist.add(new MovieBean.ShowapiResBodyBean.DatalistBean("heheda"));
        }

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
            "http://pic6.nipic.com/20100329/4485525_134844080884_2.jpg",
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
                ImageCacheUtils.loadImage(getActivity(), bean.getImage(), 80, 80, binding.circleImageView);
                binding.tvName.setText(bean.getName());
            }

            @Override
            protected void itemOnclick(MainTypeBean bean, int position) {
                ToastUtils.toast("功能待实现...");
            }
        });
    }

    private FragmentAViewpagerBinding mViewpagerBinding;
    private FragmentATypeLayoutBinding mTypeLayoutBinding;

    /**
     * 初始化头部View
     */
    private void initHeadView() {
        mViewpagerBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_viewpager, null, false);
        lvBinding.listView.addHeaderView(mViewpagerBinding.getRoot());

        mTypeLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_a_type_layout, null, false);
        lvBinding.listView.addHeaderView(mTypeLayoutBinding.getRoot());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        aPresenter.getMovieData();
    }
}
