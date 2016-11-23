package com.shan.mvp_rxjava_retrofit.net;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.shan.mypubliclibrary.bean.BaseBean;
import com.shan.mypubliclibrary.utils.PDUtils;

import rx.Subscriber;

/**
 * 处理请求回调
 * Created by chenjunshan on 2016/8/28.
 */

public abstract class SubscriberCallBack<T extends BaseBean> extends Subscriber<T> {
    private PDUtils pdUtils = null;
    private boolean isCancel = true;//默认点击返回键是可以取消
    private CancelRequestListener cancelRequestListener;

    //不启动ProgressDialog
    public SubscriberCallBack() {
    }

    /**
     * 启动ProgressDialog，并注册取消请求监听
     *
     * @param context
     * @param cancelRequestListener 取消请求监听
     */
    public SubscriberCallBack(Context context, CancelRequestListener cancelRequestListener) {
        pdUtils = new PDUtils(context, isCancel);
        this.cancelRequestListener = cancelRequestListener;
        pdUtils.setOnKeyListener(new DialogOnKeyListener());
    }

    /**
     * 启动ProgressDialog，并注册取消请求监听
     *
     * @param context
     * @param cancelRequestListener 取消请求监听
     * @param isCancel              点击返回键是否可以取消
     */
    public SubscriberCallBack(Context context, CancelRequestListener cancelRequestListener, boolean isCancel) {
        this.isCancel = isCancel;
        this.cancelRequestListener = cancelRequestListener;
        pdUtils = new PDUtils(context, isCancel);
        pdUtils.setOnKeyListener(new DialogOnKeyListener());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (pdUtils != null && !pdUtils.isShowing()) {
            pdUtils.show();
        }
    }

    @Override
    public void onCompleted() {
        if (pdUtils != null && pdUtils.isShowing()) {
            pdUtils.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
        if (pdUtils != null && pdUtils.isShowing()) {
            pdUtils.dismiss();
        }
    }

    @Override
    public void onNext(T t) {
        if (t.getShowapi_res_code() != 0) {
            onError(new RuntimeException("哈哈哈，我错了"));
            return;
        }
        onSuccess(t);
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(Throwable e);

    private class DialogOnKeyListener implements DialogInterface.OnKeyListener {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == KeyEvent.KEYCODE_BACK) {
                if (!isCancel)
                    return true;
                onError(new RuntimeException("取消请求"));
                cancelRequestListener.cancelRequest();
            }
            return false;
        }
    }

}
