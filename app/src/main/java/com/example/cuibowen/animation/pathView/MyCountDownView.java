package com.example.cuibowen.animation.pathView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by cuibowen on 2017/10/13.
 */

public class MyCountDownView extends View implements View.OnClickListener {
    private Paint arcPaint;
    private Paint circlePaint;
    private Paint textPaint;
    private Paint smallTextPaint;
    private float radius=dpToPixel(40);
    private int centerX;
    private int centerY;
    private float animatorValue;
    private float padding=dpToPixel(5);
    private String mText="跳过";
    private int mDurationTime=0;
    private RectF mRectf=new RectF();
    private ValueAnimator animator=ValueAnimator.ofFloat(0,1);
    private OnCountDownListener onCountDownListener;

    public void setOnCountDownListener(OnCountDownListener onCountDownListener) {
        this.onCountDownListener = onCountDownListener;
    }

    public MyCountDownView(Context context) {
        this(context,null);
    }

    public MyCountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        arcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(dpToPixel(5));
        arcPaint.setColor(Color.BLACK);


        circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.GRAY);

        textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(dpToPixel(15));
        textPaint.setColor(Color.WHITE);

        smallTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        smallTextPaint.setAntiAlias(true);
        smallTextPaint.setTextAlign(Paint.Align.CENTER);
        smallTextPaint.setTextSize(dpToPixel(12));
        smallTextPaint.setColor(Color.WHITE);

       setOnClickListener(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX=w/2;
        centerY=h/2;
        mRectf.set(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
    }
    public void setTime(int time){
       this.mDurationTime=time;
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(mDurationTime*1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorValue= (float) valueAnimator.getAnimatedValue();
                invalidate();

                if(onCountDownListener!=null){
                    if (mDurationTime-(int) (mDurationTime*animatorValue)==0)
                        onCountDownListener.onCountDownSuccessListener();
                }
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawCircle(centerX,centerY,radius-padding,circlePaint);

        canvas.drawText(mText,centerX,centerY-(textPaint.ascent()+textPaint.descent())/2,textPaint);

        String time=(mDurationTime-(int) (mDurationTime*animatorValue))+"秒";

        canvas.drawText(time,centerX,centerY-(textPaint.ascent()+textPaint.descent())/2+dpToPixel(20),smallTextPaint);

        canvas.drawArc(mRectf,-90, animatorValue*360,false,arcPaint);


    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    @Override
    public void onClick(View view) {
        if (onCountDownListener!=null){
            cancalTime();
            onCountDownListener.onCountDownClickListener();
        }
    }
    private void cancalTime(){
        animator.cancel();
        mDurationTime=0;
        animatorValue=0;
        invalidate();
    }

    public interface OnCountDownListener{
        void onCountDownClickListener();
        void onCountDownSuccessListener();
    }
}
