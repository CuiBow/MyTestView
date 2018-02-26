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

public class PathPosView extends View {
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private ValueAnimator animator;
    private float [] pos;
    private float [] tan;
    private Paint mPaint;
    private float length;
    public PathPosView(Context context) {
        this(context,null);
    }

    public PathPosView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathPosView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPath=new Path();
        mPath.addCircle(w/2,h/2,300, Path.Direction.CW);

        mPathMeasure=new PathMeasure();
        mPathMeasure.setPath(mPath,true);
        length=mPathMeasure.getLength();
        pos=new float[2];
        tan=new float[2];

        animator=ValueAnimator.ofFloat(0,1);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue= (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPathMeasure.getPosTan(mAnimatorValue*length,pos,tan);

        canvas.drawPath(mPath,mPaint);
        canvas.drawCircle(pos[0],pos[1],20,mPaint);



    }
}
