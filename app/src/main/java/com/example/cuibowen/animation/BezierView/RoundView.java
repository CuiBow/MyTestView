package com.example.cuibowen.animation.BezierView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.cuibowen.animation.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cuibowen on 2017/11/14.
 */

public class RoundView extends View implements View.OnClickListener{
    private Bitmap button;
    private Paint buttonPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCirclePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private int x;
    private int y;
    private int padding=30;
    private int buttonWidth=130;
    private float length;

    private Path circlePath;
    private Path dashPath;
    private PathMeasure pathMeasure=new PathMeasure();
    private boolean isLeftOrRight=false;

    private float mAnimatorValue;

    private List<Point> pointList=new ArrayList<>();
    private int [] colors=new int[3];
    private boolean isClick=false;
    
    private ValueAnimator mAnimator= ValueAnimator.ofFloat(0,1);

    public RoundView(Context context) {
        this(context,null);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        colors= new int[]{getResources().getColor(R.color.pink_color), getResources().getColor(R.color.blue_color), getResources().getColor(R.color.yellow_color)};
    }

    private void init(){
        //按钮
        button = BitmapFactory.decodeResource(getResources(), R.mipmap.button);
        //按钮画笔
        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setAntiAlias(true);
        buttonPaint.setStrokeWidth(1);
        //圆圈画笔
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(0xff1aa98c);
        //小球画笔
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.WHITE);
        //线画笔
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(5);
        //点画笔
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);

        setOnClickListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isClick){
            drawCircle(canvas);
            drawButton(canvas);
            drawSmallCircle(canvas);
            drawSmallPath(canvas);
        }

       // drawPointCircle(canvas);
    }

    private void drawPointCircle(Canvas canvas) {

        for (int i=0;i<pointList.size();i++){
            int index = (int) (Math.random() * colors.length);
            int random = colors[index];
            mPointPaint.setColor(random);
            Point point=pointList.get(i);
            canvas.drawCircle(point.x,point.y,radomPoint(10,15),mPointPaint);
        }
    }

    private void drawSmallPath(Canvas canvas) {
        circlePath.reset();
        circlePath.lineTo(0,0);
        float end=mAnimatorValue*length;
        float start = (float) (end - ((0.5 - Math.abs(mAnimatorValue - 0.3)) * length));
        //float start = 0;
        pathMeasure.getSegment(start,end,circlePath,true);
        canvas.drawPath(circlePath,mPaint);
    }

    private void drawSmallCircle(Canvas canvas) {
        float end = pathMeasure.getLength()*mAnimatorValue;
        float[] coords = new float[]{0f, 0f};
        pathMeasure.getPosTan(end, coords, null);
        canvas.drawCircle(coords[0], coords[1], 8, mCirclePaint);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(x,y,buttonWidth,circlePaint);
    }

    private void drawButton(Canvas canvas) {

        if (button!=null){
            int width=button.getWidth();
            int height= button.getHeight();
            canvas.drawBitmap(button,x-width/2,y-height/2,buttonPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x=w/2;
        y=h/2;


        //点
        for (int i=0;i<5;i++){
            Point point=new Point();
            point.x=isLeftOrRight?radomPoint(x-button.getWidth()/2-80,x-button.getWidth()/2-50):radomPoint(x+button.getWidth()/2+50,x+button.getWidth()/2+80);
            point.y=radomPoint(y,y+button.getWidth()/2+20);
            pointList.add(point);
            if (isLeftOrRight){
                isLeftOrRight=false;
            }else{
                isLeftOrRight=true;
            }
        }


        circlePath=new Path();

        dashPath=new Path();

        dashPath.addCircle(x,y,buttonWidth+padding, Path.Direction.CW);

        pathMeasure=new PathMeasure();

        pathMeasure.setPath(dashPath,true);

        length = pathMeasure.getLength();

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
    public void onClick(View view) {
//        isClick=true;
//        invalidate();
    }

    private int radomPoint(int minX,int MaxX){

        Random random = new Random();

        int s = random.nextInt(MaxX)%(MaxX-minX+1) + minX;

        return s;
    }
}
