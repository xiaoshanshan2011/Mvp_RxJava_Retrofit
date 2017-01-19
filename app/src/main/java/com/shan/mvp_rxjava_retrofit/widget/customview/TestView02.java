package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 陈俊山 on 17-1-11.
 */

public class TestView02 extends View {
    private Paint mPaint;
    private int mWidth, mHeight;

    public TestView02(Context context) {
        super(context);
    }

    public TestView02(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
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
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.addRect(-400, -400, 400, 400, Path.Direction.CCW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, mPaint);
    }
}
