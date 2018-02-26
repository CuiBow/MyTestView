package com.example.cuibowen.animation.BezierView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/11/14.
 */

public class TestView extends View  implements View.OnClickListener{
    private Context context;
    //内圆的画笔
    private Paint mPaintCircle;
    //外层圆环的画笔
    private Paint mPaintRing;

    private static final int SCALE=6;

    //控件中心的X,Y坐标
    private int centerX;
    private int centerY;
    private int radius;
    private int ringProgress = 0;
    private int circleRadius = -1;

    //整个圆外切的矩形
    private RectF mRectF = new RectF();
    private AnimatorSet animatorSet=new AnimatorSet();

    public int getRingProgress() {
        return ringProgress;
    }

    public void setRingProgress(int ringProgress) {
        this.ringProgress = ringProgress;
        postInvalidate();
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        postInvalidate();
    }

    private float getRingStrokeWidth() {
        return mPaintRing.getStrokeWidth();
    }

    private void setRingStrokeWidth(float strokeWidth) {
        mPaintRing.setStrokeWidth(strokeWidth);
        postInvalidate();
    }

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    private void init(Context context){
        this.context=context;
        radius=dp2px(context,30);

        mPaintRing=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRing.setColor(context.getResources().getColor(R.color.colorAccent));
        mPaintRing.setAntiAlias(true);
        mPaintRing.setStyle(Paint.Style.STROKE);
        mPaintRing.setStrokeCap(Paint.Cap.ROUND);
        mPaintRing.setStrokeWidth(dp2px(context,2.5f));


        if (mPaintCircle == null) mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(context.getResources().getColor(R.color.colorAccent));

        setOnClickListener(this);

    }
    private void initAnimator(){

        ObjectAnimator mRingAnimator = ObjectAnimator.ofInt(this, "ringProgress", 0, 360);
        mRingAnimator.setDuration(500);
        mRingAnimator.setInterpolator(null);

        ObjectAnimator mCircleAnimator = ObjectAnimator.ofInt(this, "circleRadius", radius - 5, 0);
        mCircleAnimator.setInterpolator(new DecelerateInterpolator());
        mCircleAnimator.setDuration(300);

        ObjectAnimator mScaleAnimator = ObjectAnimator.ofFloat(this, "ringStrokeWidth", mPaintRing.getStrokeWidth(), mPaintRing.getStrokeWidth() * SCALE, mPaintRing.getStrokeWidth() / SCALE);
        mScaleAnimator.setInterpolator(null);
        mScaleAnimator.setDuration(300);

        animatorSet.playSequentially(mRingAnimator,mCircleAnimator,mScaleAnimator);

        animatorSet.start();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;
        int radiu=radius;
        mRectF.set(centerX - radiu, centerY - radiu, centerX + radiu, centerY + radiu);

        initAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆环
        drawRingView(canvas);
        //画满
        drawFullView(canvas);
        //收缩
        if (ringProgress == 360) {
            drawCircle(canvas);
        }
        //放大
        if (radius==0){
            canvas.drawArc(mRectF, 0, 360, false, mPaintRing);
        }
    }

    private void drawFullView(Canvas canvas) {
        mPaintCircle.setColor(context.getResources().getColor(R.color.colorAccent));
        canvas.drawCircle(centerX, centerY, ringProgress == 360 ? radius : 0, mPaintCircle);
    }

    private void drawCircle(Canvas canvas) {
        mPaintCircle.setColor(context.getResources().getColor(R.color.colorPrimary));
        canvas.drawCircle(centerX, centerY, circleRadius, mPaintCircle);
    }

    private void drawRingView(Canvas canvas) {

        canvas.drawArc(mRectF,90,ringProgress,false,mPaintRing);
    }

    static int dp2px(Context context, float dpValue) {
        if (context == null) return (int) (dpValue * 1.5f + 0.5f);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        ringProgress = 0;
        circleRadius = -1;
        animatorSet.start();
    }
}
