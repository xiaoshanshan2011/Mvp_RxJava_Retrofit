package com.shan.mypubliclibrary.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 为了防止Handler造成内存泄露，所以使用静态内部类和弱引用实现Handler的创建
 * Created by chenjunshan on 17-2-8.
 */

public class HandlerTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Message message = Message.obtain();
        message.what = 1;
        mMyHandler.sendMessage(message);
    }

    /********************************Handler静态内部类**********************************/

    private MyHandler mMyHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Context> mReference;

        public MyHandler(Context context) {
            mReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerTestActivity testActivity = (HandlerTestActivity) mReference.get();
            if (testActivity != null) {

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除消息队列中所有消息和所有的Runnable
        mMyHandler.removeCallbacksAndMessages(null);
    }
}
