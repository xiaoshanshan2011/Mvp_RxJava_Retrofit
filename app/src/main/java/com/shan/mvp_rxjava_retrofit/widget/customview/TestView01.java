package com.shan.mvp_rxjava_retrofit.widget.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;

/**
 * 自定义View
 * Created by 陈俊山 on 17-1-9.
 */

public class TestView01 extends View {
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;

    public TestView01(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5f);
        init(getContext());
    }

    public TestView01(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 测量View大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 确定View大小
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 确定子View布局位置
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制内容
     *
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.translate(mWidth / 2, mHeight / 2);

        /*绘制点*/
        /*canvas.drawPoint(200, 200, mPaint);
        float[] points = {
                500, 500,
                500, 600,
                500, 700};
        canvas.drawPoints(points, mPaint);*/

        /*绘制直线*/
        /*canvas.drawLine(300, 300, 500, 600, mPaint);
        float[] points = {
                100, 200, 200, 200,
                100, 300, 200, 300};
        canvas.drawLines(points, mPaint);*/

        /*绘制矩形*/
        /*// 第一种
        canvas.drawRect(100,100,800,400,mPaint);
        // 第二种
        Rect rect = new Rect(100,100,800,400);
        canvas.drawRect(rect,mPaint);
        // 第三种
        RectF rectF = new RectF(100,100,800,400);
        canvas.drawRect(rectF,mPaint);*/

        /*绘制圆角矩形*/
        /*RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawRoundRect(rectF, 150, 150, mPaint);*/

        /*绘制椭圆*/
        /*RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawOval(rectF, mPaint);*/

        /*绘制圆*/
        /*canvas.drawCircle(500,500,300,mPaint);*/

        /*绘制圆弧*/
        /*RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawArc(rectF, 0, 90, false, mPaint);
        RectF rectF2 = new RectF(100, 600, 800, 900);
        canvas.drawArc(rectF2, 0, 90, true, mPaint);*/

        /*绘制圆环*/
        /*mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(500, 500, 200, mPaint);*/

        /*布尔运算示例*/
        /*mPaint.setColor(Color.WHITE);
        canvas.drawCircle(0, 0, 200, mPaint);

        RectF rectF2 = new RectF(-200, -200, 200, 200);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();
        path1.addArc(rectF2, 90, 180);
        path2.addCircle(0, -100, 100, Path.Direction.CW);
        path3.addCircle(0, 100, 100, Path.Direction.CW);

        path4.op(path1, path2, Path.Op.UNION);
        path4.op(path3, Path.Op.DIFFERENCE);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path4, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(0, -100, 15, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 100, 15, mPaint);*/

        /*PathMeasure的使用*/
        /*Path path = new Path();

        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure measure1 = new PathMeasure(path, false);
        PathMeasure measure2 = new PathMeasure(path, true);

        Log.e("TAG", "forceClosed=false---->" + measure1.getLength());
        Log.e("TAG", "forceClosed=true----->" + measure2.getLength());

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);*/

        //测试

        Path path = new Path();                                 // 创建 Path

        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.01;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势

        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头

        invalidate();

    }


    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 2;       // 缩放图片
        //mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_arrow, options);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_arrow);
        mMatrix = new Matrix();
    }
}
