package com.shan.mvp_rxjava_retrofit.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mypubliclibrary.utils.ToastUtils;

/**
 * Created by root on 16-11-22.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.toast("1111111111111");
            }
        });
    }
}
