package com.shan.mvp_rxjava_retrofit.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.bean.TestBean;
import com.shan.mvp_rxjava_retrofit.databinding.ItemBinding;
import com.shan.mypubliclibrary.adapter.CommonAdapter;
import com.shan.mypubliclibrary.net.CancelRequestListener;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class AFragment extends Fragment implements CancelRequestListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            list.add(new TestBean("ssssss" + i));
        }

        ListView listView = (ListView) view.findViewById(R.id.lisView);
        listView.setAdapter(new CommonAdapter<ItemBinding, TestBean>(getActivity(), R.layout.item, list) {
            @Override
            protected void getItem(ItemBinding binding, TestBean bean, int position) {
                binding.textView4.setText(bean.getName());
            }
        });

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        ImageCacheUtils.loadImage(getActivity(), "http://t1.niutuku.com/960/38/38-82994.jpg", 300, 300, imageView);
        return view;
    }

    @Override
    public void cancelRequest() {

    }
}
