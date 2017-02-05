package com.shan.mvp_rxjava_retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.bean.BannerBean;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;


/**
 * Created by roocky on 03/09.
 * <p>
 * 图片轮播控件ConvenientBanner
 */
public class BannerAdapter implements Holder<BannerBean> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.viewpager_item_layout, null, false);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerBean data) {
        ImageCacheUtils.loadImage(context, data.getImage(), mImageView);
    }
}