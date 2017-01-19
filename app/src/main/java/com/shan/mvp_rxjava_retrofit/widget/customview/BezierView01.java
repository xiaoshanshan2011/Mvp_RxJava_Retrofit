package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 陈俊山 on 17-1-10.
 */

public class BezierView01 extends View implements View.OnTouchListener {
    private PointF start, end, control;
    private Paint mPaint;
    private int mWidth, mHeight;

    public BezierView01(Context context) {
        super(context);
        initPaint();
    }

    public BezierView01(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        setOnTouchListener(this);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        start.x = w / 2 - 200;
        start.y = h / 2;

        end.x = w / 2 + 200;
        end.y = h / 2;

        control.x = w / 2;
        control.y = h - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        control.x = motionEvent.getX();
        control.y = motionEvent.getY();
        invalidate();
        return true;
    }
}
