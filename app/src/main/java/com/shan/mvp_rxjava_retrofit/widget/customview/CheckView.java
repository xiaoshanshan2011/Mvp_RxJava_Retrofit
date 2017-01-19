package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;

/**
 * Created by 陈俊山 on 17-1-10.
 */

public class CheckView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private Bitmap mBitmap = null;
    private int bmWidth = 0;
    private int bmHeight = 0;

    public CheckView(Context context) {
        super(context);
        initPaint();
        initBitmap(context);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initBitmap(context);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    int srcWidth = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null)
            return;
        canvas.translate(mWidth / 2, mHeight / 2);

        int r = Math.max(bmWidth, bmHeight) * 4 / 5;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(0, 0, r, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(0, 0, r, mPaint);

        Rect src = new Rect(0, 0, currentWidth, bmHeight);

        if (currentWidth < bmWidth / 2) {
            srcWidth = -(bmWidth / 2 - currentWidth);
        } else if (currentWidth == bmWidth / 2) {
            srcWidth = 0;
        } else if (currentWidth > bmWidth / 2) {
            srcWidth = currentWidth - bmWidth / 2;
        }
        Rect dst = new Rect(-(bmWidth / 2), -(bmHeight / 2), srcWidth, bmHeight / 2);
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    /**
     * 异步处理图片
     *
     * @param context
     */
    private void initBitmap(final Context context) {
        new AsyncTask<Integer, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Integer... integers) {
                return BitmapFactory.decodeResource(context.getResources(), integers[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                mBitmap = bitmap;
                bmWidth = mBitmap.getWidth();
                bmHeight = mBitmap.getHeight();
                invalidate();
            }
        }.execute(R.mipmap.ic_test03);
    }

    public void start() {
        mHandler.removeMessages(STOP);
        mHandler.sendEmptyMessageDelayed(START, 50);
    }

    public void stop() {
        mHandler.removeMessages(START);
        mHandler.sendEmptyMessageDelayed(STOP, 50);
    }

    private int currentWidth = 0;//当前宽度
    private final int START = 1;
    private final int STOP = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    if (currentWidth < bmWidth) {
                        currentWidth += 10;
                        invalidate();
                        mHandler.sendEmptyMessageDelayed(START, 50);
                    } else {
                        if (listener != null)
                            listener.startFinish();
                    }
                    break;
                case STOP:
                    if (currentWidth > 0) {
                        currentWidth -= 10;
                        invalidate();
                        mHandler.sendEmptyMessageDelayed(STOP, 50);
                    } else {
                        srcWidth = 0;
                        if (listener != null)
                            listener.stopFinish();
                    }
                    break;
            }
        }
    };

    private OnCheckViewListener listener = null;

    public void setOnCheckViewListener(OnCheckViewListener listener) {
        this.listener = listener;
    }

    public interface OnCheckViewListener {
        void startFinish();

        void stopFinish();
    }
}
