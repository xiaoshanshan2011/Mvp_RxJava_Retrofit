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

public class BezierView02 extends View implements View.OnTouchListener {
    private PointF start, end, control1, control2;
    private Paint mPaint;
    private int mWidth, mHeight;

    public BezierView02(Context context) {
        super(context);
        initPaint();
    }

    public BezierView02(Context context, AttributeSet attrs) {
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
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
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

        control1.x = w / 2 - 200;
        control1.y = h - 100;

        control2.x = w / 2 + 200;
        control2.y = h - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
        canvas.drawLine(end.x, end.y, control2.x, control2.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        //path.quadTo(control1.x, control1.y, end.x, end.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        control1.x = motionEvent.getX();
        control1.y = motionEvent.getY();
        invalidate();
        return true;
    }
}
