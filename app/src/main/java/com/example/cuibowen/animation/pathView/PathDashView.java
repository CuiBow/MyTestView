package com.example.cuibowen.animation.pathView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by cuibowen on 2017/8/21.
 */

public class PathDashView extends View {
    private Path mPath;
    private Paint mPaint;
    private DashPathEffect mDash;
    private PathMeasure pathMeasure;
    private float length;
    private ValueAnimator mAnimator;
    private float mAnimatorValue;
    public PathDashView(Context context) {
        this(context,null);
    }

    public PathDashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathDashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPath=new Path();
        mPath.addCircle(w/2,h/2,300, Path.Direction.CW);
        pathMeasure=new PathMeasure();
        pathMeasure.setPath(mPath,true);

        length=pathMeasure.getLength();
        mAnimator=ValueAnimator.ofFloat(1,0);
        mAnimator.setDuration(4000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue= (float) valueAnimator.getAnimatedValue();
                mDash=new DashPathEffect(new float[]{length,length},length*mAnimatorValue);
                mPaint.setPathEffect(mDash);
                invalidate();
            }
        });
        mAnimator.start();
    }

    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath,mPaint);
    }
}
