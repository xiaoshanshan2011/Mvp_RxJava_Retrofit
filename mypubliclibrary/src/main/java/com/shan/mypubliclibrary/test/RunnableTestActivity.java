package com.shan.mypubliclibrary.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by root on 17-2-8.
 */

public class RunnableTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        handler.post(new MyRunnable(this));
    }

    private static class MyRunnable implements Runnable {
        private WeakReference<Context> mWeakReference;

        public MyRunnable(Context context) {
            mWeakReference = new WeakReference<>(context);
        }

        @Override
        public void run() {
            RunnableTestActivity testActivity = (RunnableTestActivity) mWeakReference.get();
            if (testActivity != null) {

            }
        }
    }
}
