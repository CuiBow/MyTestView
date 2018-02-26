package com.example.cuibowen.animation.homeView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.cuibowen.animation.R;


/**
 * Created by cuibowen on 2017/12/19.
 */

public class ProgressView extends View {
    private final static int  COMMUNITY_TYPE=0;
    private final static int  HOSPITAL_TYPE=1;
    private static final int STROKE_WIDTH = 15;
    private static final int BIG_SIZE = 30;
    private static final int SMALL_SIZE = 15;
    private Context mContext;
    private int type;
    private int centerX;
    private int centerY;
    private int radius;
    private int backColor;
    private int strokeWidth;
    private float progress;
    private int bigSize;
    private int smallSize;
    private RectF mRectF;
    private RectF smallRectf;
    private Paint backPaint;
    private Paint progressPaint;
    private Paint smallProgressPaint;
    private Paint textBigPaint;
    private Paint textSmallPaint;

    private int[] greenProgressColor = new int[]{
            Color.parseColor("#2bf1c7"),
            Color.parseColor("#18a98a")
    };

    private int[] blueProgressColor = new int[]{
            Color.parseColor("#2bf1ef"),
            Color.parseColor("#40a0f1")
    };
    private int[] greenTextColor = new int[]{
            Color.parseColor("#26dcb5"),
            Color.parseColor("#1ab191")
    };
    private int[] blueTextColor = new int[]{
            Color.parseColor("#2fe1ef"),
            Color.parseColor("#37c4f0")
    };

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressView);
        type = a.getInt(R.styleable.ProgressView_colorType, COMMUNITY_TYPE);
        backColor=a.getColor(R.styleable.ProgressView_backColor,getResources().getColor(R.color.color_eaeaea));
        strokeWidth = a.getDimensionPixelSize(
                R.styleable.ProgressView_strokeWidth, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                STROKE_WIDTH, getResources()
                                        .getDisplayMetrics()));// 默认为10dp

        bigSize = a.getDimensionPixelSize(
                R.styleable.ProgressView_bigSize, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                BIG_SIZE, getResources()
                                        .getDisplayMetrics()));// 默认为10dp
        smallSize = a.getDimensionPixelSize(
                R.styleable.ProgressView_smallSize, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                SMALL_SIZE, getResources()
                                        .getDisplayMetrics()));// 默认为10dp
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }
    private void initPaint(){
        backPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        backPaint.setAntiAlias(true);
        backPaint.setColor(backColor);
        backPaint.setStyle(Paint.Style.STROKE);
        backPaint.setStrokeWidth(STROKE_WIDTH);
        backPaint.setStrokeCap(Paint.Cap.ROUND);

        textBigPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        textBigPaint.setAntiAlias(true);
        textBigPaint.setTextAlign(Paint.Align.CENTER);
        textBigPaint.setTextSize(bigSize);


        textSmallPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        textSmallPaint.setAntiAlias(true);
        textSmallPaint.setTextAlign(Paint.Align.CENTER);
        textSmallPaint.setTextSize(smallSize);

        int[] pointX={0,centerX*2};
        int pointY=centerY*2;
        Shader shader;
        switch (type){
            case COMMUNITY_TYPE:
                shader =new LinearGradient(pointX[0],pointY ,pointX[1],pointY ,greenProgressColor[0],
                        greenProgressColor[1], Shader.TileMode.CLAMP);
                textBigPaint.setColor(greenTextColor[0]);
                textSmallPaint.setColor(greenTextColor[1]);
                break;
            case HOSPITAL_TYPE:
                shader =new LinearGradient(pointX[0],pointY,pointX[1],pointY,blueProgressColor[0],
                        blueProgressColor[1], Shader.TileMode.CLAMP);
                textBigPaint.setColor(blueTextColor[0]);
                textSmallPaint.setColor(blueTextColor[1]);
                break;
            default:
                shader =new LinearGradient(pointX[0],pointY,pointX[1],pointY,greenProgressColor[0],
                        greenProgressColor[1], Shader.TileMode.CLAMP);
                textBigPaint.setColor(greenTextColor[0]);
                textSmallPaint.setColor(greenTextColor[1]);
                break;
        }
        progressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(STROKE_WIDTH);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setShader(shader);


        smallProgressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        smallProgressPaint.setAntiAlias(true);
        smallProgressPaint.setStyle(Paint.Style.STROKE);
        smallProgressPaint.setStrokeWidth(5);
        smallProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        smallProgressPaint.setShader(shader);
        smallProgressPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=centerX;
        radius=centerX-strokeWidth;
        mRectF=new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        smallRectf=new RectF(centerX-radius+(STROKE_WIDTH-8),centerY-radius+(STROKE_WIDTH-8),centerX+radius-(STROKE_WIDTH-8),centerY+radius-(STROKE_WIDTH-8));
        initPaint();
    }
    public void setSchedule(float progress){
        if (progress<0){
            progress=0;
        }
        if (progress>100){
            progress=100;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"progress",0,progress);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackArc(canvas);
        drawProgress(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        int newProgress= (int) progress;
        float top=textBigPaint.ascent();
        float botton=textBigPaint.descent();
        float height=  (top+botton)/2;

        canvas.drawText(newProgress + "%", centerX, centerY, textBigPaint);
        canvas.drawText("报销",centerX,centerY-height-(textSmallPaint.ascent()+textSmallPaint.descent()),textSmallPaint);
    }

    private void drawProgress(Canvas canvas) {
        canvas.drawArc(smallRectf,
                135,
                progress*2.7f,
                false,
                smallProgressPaint);
        canvas.drawArc(mRectF,
                135,
                progress*2.7f,
                false,
                progressPaint);

    }
    private void drawBackArc(Canvas canvas) {
        canvas.drawArc(mRectF,135,270,false,backPaint);
    }

}
