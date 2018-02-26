package com.example.cuibowen.animation.BezierView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.cuibowen.animation.utils.BezierEvaluator;

/**
 * Created by cuibowen on 2017/7/24.
 */

public class ShopPathView extends View  implements View.OnClickListener{

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;
    private int mFlagX;
    private int mFlagY;
    private int circleX;
    private int circleY;
    private int circleWidth=20;

    private Path mPath;

    private Paint circlePaint;
    private Paint linePaint;

    private ValueAnimator animator;

    public ShopPathView(Context context) {
        this(context,null);
    }

    public ShopPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShopPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mStartX=100;
        mStartY=100;
        mEndX=500;
        mEndY=600;
        mFlagX=500;
        mFlagY=0;

        circleX=mStartX;
        circleY=mStartY;

        linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(5);
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);


        circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.RED);


        mPath=new Path();

        setOnClickListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(mStartX,mStartY);
        mPath.quadTo(mFlagX,mFlagY,mEndX,mEndY);

        canvas.drawPath(mPath,linePaint);

        canvas.drawCircle(circleX,circleY,circleWidth,circlePaint);
    }

    @Override
    public void onClick(View view) {
        BezierEvaluator evaluator=new BezierEvaluator(new PointF(mFlagX,mFlagY));
        animator=ValueAnimator.ofObject(evaluator,new PointF(mStartX,mStartY),new PointF(mEndX,mEndY));
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF= (PointF) valueAnimator.getAnimatedValue();
                circleX= (int) pointF.x;
                circleY= (int) pointF.y;
                invalidate();
            }
        });
        animator.start();
    }
}
