package com.shan.mypubliclibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 图片三级缓存工具类(Glide)
 * Created by chenjunshan on 16-11-22.
 */

public class ImageCacheUtils {
    /**
     * 简单加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).into(imageView);
    }

    /**
     * 加载图片(设置加载中以及加载失败图片)
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView, int loadingImg, int errorImg) {
        Glide.with(context).load(imageUrl).placeholder(loadingImg).error(errorImg).into(imageView);
    }

    /**
     * 加载图片(设置先加载缩略图)
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param isthumbnail 是否加载缩略图
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView, boolean isthumbnail) {
        if (isthumbnail)
            Glide.with(context).load(imageUrl).thumbnail(0.1f).into(imageView);
        else
            Glide.with(context).load(imageUrl).into(imageView);
    }

    /**
     * 加载图片(设置加载尺寸)
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param width     设置加载图片的宽度
     * @param height    设置加载图片的高度
     */
    public static void loadImage(Context context, String imageUrl, int width, int height, ImageView imageView) {
        Glide.with(context).load(imageUrl).override(width, height).into(imageView);
    }
}
