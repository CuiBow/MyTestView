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
 * Created by cuibowen on 2017/7/18.
 */

public class BezierSecondView extends View {
    private float startX;
    private float startY;

    private float mFlagX;
    private float mFlagY;

    private float endX;
    private float endY;


    private Paint bezierPaint;
    private Paint linePaint;
    private Paint pointPaint;
    private Path mPath;

    public BezierSecondView(Context context) {
        this(context,null);
    }

    public BezierSecondView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierSecondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
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

        startX=w/4;
        startY=h/2;

        endX=w/2;
        endY=h/2;

        mFlagX=w/3;
        mFlagY=h/3;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();

        mPath.moveTo(startX,startY);
        mPath.quadTo(mFlagX,mFlagY,endX,endY);
        canvas.drawPath(mPath,bezierPaint);

        canvas.drawLine(startX,startY,mFlagX,mFlagY,linePaint);
        canvas.drawLine(mFlagX,mFlagY,endX,endY,linePaint);


        canvas.drawPoint(startX,startY,pointPaint);
        canvas.drawPoint(mFlagX,mFlagY,pointPaint);
        canvas.drawPoint(endX,endY,pointPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                mFlagX=event.getX();
                mFlagY=event.getY();

                break;
        }
        invalidate();
        return true;
    }
}
