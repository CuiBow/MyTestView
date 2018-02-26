package com.example.cuibowen.animation.HenCodeVIew;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by cuibowen on 2017/10/12.
 */

public class MyProgressView extends View {
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF arcRectF = new RectF();
    private int progress;
    private float radius=dpToPixel(100);

    public MyProgressView(Context context) {
        this(context,null);
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgresses(int progress){

        this.progress=progress;


        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX=getWidth()/2;
        float centerY=getHeight()/2;


        mPaint.setColor(Color.parseColor("#E91E63"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(dpToPixel(12));
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        arcRectF.set(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        canvas.drawArc(arcRectF,135,progress*2.7f,false,mPaint);

        mPaint.setTextSize(dpToPixel(40));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(dpToPixel(40));
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawText(progress+"%",centerX,centerY-(mPaint.ascent()+mPaint.descent())/2,mPaint);
    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
