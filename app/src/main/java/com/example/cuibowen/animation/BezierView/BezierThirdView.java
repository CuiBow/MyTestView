package com.example.cuibowen.animation.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cuibowen on 2017/7/20.
 */

public class BezierThirdView extends View
{
    private float mStartX;
    private float mStartY;

    private float mFlagOneX;
    private float mFlagOneY;

    private float mFlagTwoX;
    private float mFlagTwoY;

    private float mEndX;
    private float mEndY;

    private Paint linePaint;
    private Paint pointPaint;
    private Paint bezierPaint;

    private Path mPath;
    private boolean isSecondPoint=false;


    public BezierThirdView(Context context) {
        this(context,null);
    }

    public BezierThirdView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierThirdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        bezierPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setAntiAlias(true);
        bezierPaint.setStyle(Paint.Style.STROKE);
        bezierPaint.setStrokeWidth(1);

        linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.GREEN);

        pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(10);
        pointPaint.setColor(Color.RED);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPath=new Path();
        mStartX=w/4;
        mStartY=h/2;

        mEndX=w/2;
        mEndY=h/2;

        mFlagOneX=w/3-100;
        mFlagOneY=h/3;

        mFlagTwoX=w/3+100;
        mFlagTwoY=h/3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();

        mPath.moveTo(mStartX,mStartY);
        mPath.cubicTo(mFlagOneX,mFlagOneY,mFlagTwoX,mFlagTwoY,mEndX,mEndY);
        canvas.drawPath(mPath,bezierPaint);

        canvas.drawLine(mStartX,mStartY,mFlagOneX,mFlagOneY,linePaint);
        canvas.drawLine(mFlagOneX,mFlagOneY,mFlagTwoX,mFlagTwoY,linePaint);
        canvas.drawLine(mFlagTwoX,mFlagTwoY,mEndX,mEndY,linePaint);


        canvas.drawPoint(mStartX,mStartY,pointPaint);
        canvas.drawPoint(mFlagOneX,mFlagOneY,pointPaint);
        canvas.drawPoint(mFlagTwoX,mFlagTwoY,pointPaint);
        canvas.drawPoint(mEndX,mEndY,pointPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()&MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_MOVE:
                mFlagOneX=event.getX(0);
                mFlagOneY=event.getY(0);
                if (isSecondPoint)
                {
                    mFlagTwoX=event.getX(1);
                    mFlagTwoY=event.getY(1);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isSecondPoint=false;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                isSecondPoint=true;
                break;
        }

        return true;
    }
}
