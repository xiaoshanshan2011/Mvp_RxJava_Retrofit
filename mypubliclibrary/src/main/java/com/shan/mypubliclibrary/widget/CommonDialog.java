package com.shan.mypubliclibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.shan.mypubliclibrary.R;

/**
 * 公共弹出框
 * Created by 陈俊山 on 2016/3/24.
 */
public class CommonDialog {
    private Dialog dialog;
    private Activity activity;
    private int gravity;//弹出宽的位置，默认居中
    private WindowManager windowManager;
    private Display display;
    private float width;//弹出框的宽度,默认0.8，例如：1表示全屏，0.8表示为屏幕宽度的0.8倍
    private float height;//默认布局自动加载高度
    private int shape;//弹出框形状，默认方角
    private int resId;//布局id
    private int animResId;//动画id
    private ViewDataBinding binding;
    public static final int CIRCLE = R.drawable.dialog_circle_shape;//圆角
    public static final int SQUARE = R.drawable.dialog_square_shape;//方角
    public static final int DIALOG_IN_OUT = R.style.dialog_in_out;//向上弹起向下滑落
    public static final int DIALOG_LEFT_RIGHT = R.style.dialog_left_right;//从左弹出从右关闭


    private CommonDialog(Builder builder) {
        this.activity = (Activity) builder.context;
        this.gravity = builder.gravity;
        this.width = builder.width;
        this.height = builder.height;
        this.shape = builder.shape;
        this.resId = builder.resId;
        this.animResId = builder.animResId;
        windowManager = activity.getWindowManager();
        display = windowManager.getDefaultDisplay();
    }

    public static class Builder {
        private Context context;
        private int gravity = Gravity.CENTER;
        private float width = 0.8f;
        private float height = 0.0f;
        private int shape = SQUARE;
        private int resId;
        private int animResId = DIALOG_IN_OUT;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setGravity(int GRAVITY) {
            this.gravity = GRAVITY;
            return this;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public Builder setShape(int shape) {
            this.shape = shape;
            return this;
        }

        public Builder setResId(int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setAnimResId(int animResId) {
            this.animResId = animResId;
            return this;
        }

        public CommonDialog build() {
            return new CommonDialog(this);
        }

    }

    /**
     * 关闭dialog
     */
    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 显示Dialog
     *
     * @return
     */
    public ViewDataBinding show() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), resId, null, false);
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题
        dialog.setCanceledOnTouchOutside(true);//点击屏幕消失
        dialog.setContentView(binding.getRoot());
        Window dialogwWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogwWindow.getAttributes();
        lp.width = (int) (display.getWidth() * width);//设置Dialog宽度
        if (height != 0)
            lp.height = (int) (display.getHeight() * height);//设置Dialog高度
        dialogwWindow.setAttributes(lp);
        dialogwWindow.setGravity(gravity);//设置dialog显示位置
        dialogwWindow.setBackgroundDrawableResource(shape);//设置dialog背景风格
        dialogwWindow.setWindowAnimations(animResId);//设置动画效果
        dialog.show();
        return binding;
    }

}
