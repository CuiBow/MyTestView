package com.example.cuibowen.animation.BezierView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/7/18.
 */

public class WaveBezierView extends View  {
    private Context mContext;
    private int mScreenWidth;
    private int mScreenHeight;
    private float mWaveCount;
    private float mWaveLenght;
    private float mOffset;
    private float currentProgress=0;
    private int width;//设置高
    private int height;//设置高
    private Path mPath;
    private Paint linePaint;
    private Paint mPaintCircle;//定义圆形的画笔
    private Canvas bitmapCanvas;//定义Bitmap的画布
    private Bitmap bgBitmap;
    private Bitmap bitmap;//定义Bitmap
    private  Rect mRectf;
    private ValueAnimator animator;
    ValueAnimator progressAnimator;

    public WaveBezierView(Context context) {
        this(context,null);
    }

    public WaveBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init();
    }
    private void init()
    {
        Resources resources = getResources();
        linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setStrokeWidth(8);
        linePaint.setColor(resources.getColor(R.color.color_ff6000));
        mWaveLenght=400;
        linePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mPaintCircle = new Paint();
        mPaintCircle.setFilterBitmap(true);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(resources.getColor(R.color.color_f5f5f5));
        bgBitmap = BitmapFactory.decodeResource(resources,
                R.mipmap.logo_300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath=new Path();
        mScreenWidth=w;
        mScreenHeight=h;
        mWaveCount= (float) Math.round(mScreenWidth/mWaveLenght+1.5);


        animator =ValueAnimator.ofFloat(0,mWaveLenght);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset= (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator=ValueAnimator.ofFloat(0,height);
        progressAnimator.setDuration(5000);
        progressAnimator.setRepeatCount(ValueAnimator.INFINITE);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentProgress= height-(float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
        progressAnimator.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        currentProgress=height;
        mRectf = new Rect(0,0,width,height);
        int scaleX=bgBitmap.getWidth()/width;
        int scaleY=bgBitmap.getHeight()/height;
        bgBitmap= Bitmap.createScaledBitmap(bgBitmap,bgBitmap.getWidth()*scaleX ,bgBitmap.getHeight() *scaleY, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmapCanvas.drawBitmap(bgBitmap,null,mRectf, mPaintCircle);
        mPath.reset();
        mPath.moveTo(-mWaveLenght,height/2);

        for (int i=0;i<mWaveCount;i++)
        {
            mPath.quadTo(-mWaveLenght*3/4+i*mWaveLenght+mOffset,currentProgress-20,-mWaveLenght/2+i*mWaveLenght+mOffset,currentProgress);
            mPath.quadTo(-mWaveLenght/4+i*mWaveLenght+mOffset,currentProgress+20,i*mWaveLenght+mOffset,currentProgress);
        }
        mPath.lineTo(width,height);
        mPath.lineTo(0, height);
        mPath.close();

        bitmapCanvas.drawPath(mPath,linePaint);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
    public void start(){

    }
    public void stop(){
        if (animator!=null&&progressAnimator!=null){
            animator.end();
            progressAnimator.end();
        }
    }
}
