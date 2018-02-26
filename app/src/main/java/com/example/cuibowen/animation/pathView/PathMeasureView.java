package com.example.cuibowen.animation.pathView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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

public class PathMeasureView extends View {
    private Path mDst;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float length;
    private ValueAnimator mAnimator;
    private float mAnimatorValue;
    private Paint mPaint;


    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDst=new Path();
        mPath=new Path();
        mDst.addCircle(w/2,h/2,200, Path.Direction.CW);

        mPathMeasure=new PathMeasure();
        mPathMeasure.setPath(mDst,true);

        length=mPathMeasure.getLength();

        mAnimator=ValueAnimator.ofFloat(0,1);
        mAnimator.setDuration(1000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue= (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.lineTo(0,0);

        float end=mAnimatorValue*length;
        float start = (float) (end - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * length));
        //float start = 0;

        mPathMeasure.getSegment(start,end,mPath,true);
        canvas.drawPath(mPath,mPaint);

    }
}
