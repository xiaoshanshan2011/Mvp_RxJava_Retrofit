package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.bean.PieBean;

import java.util.List;

/**
 * 饼状图
 * Created by 陈俊山 on 17-1-9.
 */

public class PieView extends View {
    private Paint mPaint;
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080, 0xFFE6B800, 0xFF7CFC00};
    private float mStartAngel = 0;
    private List<PieBean> mPieBeens;
    private int mWidth, mHeight;

    public PieView(Context context) {
        super(context);
        initPaint();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
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
        if (mPieBeens == null || mPieBeens.size() == 0)
            return;

        float currentStartAngle = mStartAngel;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);

        for (int i = 0; i < mPieBeens.size(); i++) {
            PieBean pieBean = mPieBeens.get(i);
            mPaint.setColor(pieBean.getColor());
            canvas.drawArc(rectF, currentStartAngle, pieBean.getAngle(), true, mPaint);
            currentStartAngle += pieBean.getAngle();
        }
    }

    public void setStartAngel(float startAngel) {
        mStartAngel = startAngel;
        invalidate();
    }

    public void setPieBeens(List<PieBean> pieBeens) {
        mPieBeens = pieBeens;
        initPieBeens(mPieBeens);
        invalidate();
    }

    private void initPieBeens(List<PieBean> pieBeens) {
        if (pieBeens == null || pieBeens.size() == 0)
            return;

        float sumvalue = 0;
        for (int i = 0; i < pieBeens.size(); i++) {
            PieBean pieBean = pieBeens.get(i);
            sumvalue += pieBean.getValue();
            int j = i % mColors.length;
            pieBean.setColor(mColors[j]);
        }

        for (int i = 0; i < pieBeens.size(); i++) {
            PieBean pieBean = pieBeens.get(i);
            float percentage = pieBean.getValue() / sumvalue;
            float angle = percentage * 360;
            pieBean.setAngle(angle);
        }
    }
}
