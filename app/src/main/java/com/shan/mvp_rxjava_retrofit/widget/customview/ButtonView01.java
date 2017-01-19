package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 陈俊山 on 17-1-10.
 */

public class ButtonView01 extends View implements View.OnTouchListener {
    private Paint mPaint;
    private int mWidth, mHeight;

    public ButtonView01(Context context) {
        super(context);
        initPaint();
    }

    public ButtonView01(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        setOnTouchListener(this);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(com.shan.mypubliclibrary.R.color.light_sea_red));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    float r = 100;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, r, mPaint);
    }

    private final int START = 0;
    private final int STOP = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    if (r < 130) {
                        r += 3;
                        invalidate();
                        mHandler.sendEmptyMessageDelayed(START, 10);
                    }
                    break;
                case STOP:
                    if (r > 100) {
                        r -= 3;
                        invalidate();
                        mHandler.sendEmptyMessageDelayed(STOP, 10);
                    }
                    break;
            }
        }
    };

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(STOP);
                mHandler.sendEmptyMessageDelayed(START, 20);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.removeMessages(START);
                mHandler.sendEmptyMessageDelayed(STOP, 20);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        return true;
    }
}
