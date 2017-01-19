package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 陈俊山 on 17-1-10.
 */

public class TextView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;

    public TextView(Context context) {
        super(context);
        initPaint();
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(50);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("sdww", 200, 400, mPaint);
        canvas.drawText("123456", 1, 4, 200, 500, mPaint);

        char[] c = "GSFSGHDF".toCharArray();
        canvas.drawText(c, 1, 4, 200, 601, mPaint);

        float[] f = {
                400, 400,
                500, 500,
                600, 600,
                700, 700,
                800, 800,
                900, 900
        };
        canvas.drawPosText("123456", f, mPaint);
    }
}
