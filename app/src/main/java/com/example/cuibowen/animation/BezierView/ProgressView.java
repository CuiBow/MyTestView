package com.example.cuibowen.animation.BezierView;


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
 * Created by cuibowen on 2017/11/14.
 */

public class ProgressView extends View {

    private int centerX;
    private int centerY;

    private Paint textPaint;
    private Paint arcPaint;

    private int ringProgress;

    private int radius=140;

    public int getRingProgress() {
        return ringProgress;
    }

    public void setRingProgress(int ringProgress) {
        this.ringProgress = ringProgress;
        postInvalidate();
    }

    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(70);
        textPaint.setTextAlign(Paint.Align.CENTER);

        arcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.parseColor("#E91E63"));
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setStrokeWidth(dpToPixel(10));

//        ObjectAnimator mRingAnimator = ObjectAnimator.ofInt(this, "ringProgress", 0, 100);
//        mRingAnimator.setDuration(2000);
//        mRingAnimator.setInterpolator(new FastOutSlowInInterpolator());
//        mRingAnimator.start();



    }

    private float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float top=textPaint.ascent();
        float botton=textPaint.descent();
        float height=  (top+botton)/2;

        canvas.drawText(ringProgress + "%", centerX, centerY - height, textPaint);

        canvas.drawArc(new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius), 135, ringProgress*2.7f, false, arcPaint);
    }

    public void setPrgoressInt(int progress){
        int centerProgress;
        if (progress>90&&progress<=100){
            centerProgress=progress;
        }else{
            centerProgress=progress+10;
        }
        Keyframe keyframe1 = Keyframe.ofInt(0, 0);
        Keyframe keyframe2 = Keyframe.ofInt(0.5f, centerProgress);
        Keyframe keyframe3 = Keyframe.ofInt(1, progress);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("ringProgress", keyframe1, keyframe2, keyframe3);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, holder);
        animator.setDuration(2000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;
    }
}
