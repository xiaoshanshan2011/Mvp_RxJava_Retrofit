package com.shan.sharelibrary;

import android.app.Activity;
import android.content.Context;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 分享工具
 * Created by chenjunshan on 17-2-7.
 */

public class ShareUtils {

    /**
     * 在Application中初始化分享
     *
     * @param context
     */
    public static void initShare(Context context) {
        UMShareAPI.get(context);

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        Config.REDIRECT_URL = "您新浪后台的回调地址";
    }

    public static void startShare(Activity activity, ShareBean shareBean, UMShareListener umShareListener) {
        new ShareAction(activity)
                .withText(shareBean.getText())
                .withTitle(shareBean.getTitle())
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QZONE)
                .setCallback(umShareListener)
                .open();
    }
}
