package com.shan.mvp_rxjava_retrofit.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentDBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.sharelibrary.ShareBean;
import com.shan.sharelibrary.ShareUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class DFragment extends BaseFragment<FragmentDBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_d;
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("个人");
        titleBinding.btnLeft.setVisibility(View.GONE);
        titleBinding.btnRight.setVisibility(View.VISIBLE);
        titleBinding.btnRight.setImageResource(R.mipmap.ic_setting);

        titleBinding.btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtils.startShare(getActivity(), new ShareBean("我是Text","我是Title"), new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(getActivity(), share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(getActivity(), share_media + " 分享失败啦", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Toast.makeText(getActivity(), share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
