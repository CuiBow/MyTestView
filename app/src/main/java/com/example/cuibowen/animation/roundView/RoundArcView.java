package com.example.cuibowen.animation.roundView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cuibowen on 2017/11/20.
 */

public class RoundArcView extends View implements PieViewAnimation.AnimationHandler{
    private Context mContent;
    private ArcConfig config;

    private float radius;
    private int padding;
    private float angle;
    private RectF mRectf;

    private PieViewAnimation animation;
    private PieInfo info;
    private List<PieInfo> pieInfos=new ArrayList<>();

    private enum LineGravity  {
        TOP_RIGHT,
        TOP_LEFT,
        BOTTM_RIGHT,
        BOTTM_LEFT,
        CENTER_RIGHT,
        CENTER_LEFT,

    }

    public RoundArcView(Context context) {
        this(context,null);
    }

    public RoundArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent=context;
    }
    private void init(){
         if (config==null){
             Toast.makeText(mContent,"请配置参数",Toast.LENGTH_SHORT).show();
         }else{

             padding=config.getPadding();
             mRectf=new RectF();
             initAnimation();
         }
    }
    private void initAnimation(){
        if (config!=null){
            animation=new PieViewAnimation(config);
            animation.setInterpolator(new LinearInterpolator());
            animation.bindAnimationHandler(this);
            animation.setDuration(config.getDuration());
        }
    }
    public void startAnimation(){
        if (animation!=null){
            info=null;
            pieInfos.clear();
            this.startAnimation(animation);
        }
    }
    public void setConfig(ArcConfig config){
        this.config=config;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (config!=null){
            final float width = getWidth() - getPaddingLeft() - getPaddingRight();
            final float height = getHeight() - getPaddingTop() - getPaddingBottom();
            canvas.translate(width / 2, height / 2);
            drawArcRange(canvas);

        }

    }

    private void drawArcRange(Canvas canvas) {
        if (info!=null){
            drawCachedInfos(canvas,null);

            canvas.drawArc(mRectf, info.getStart(), angle - info.getStart(), config.isFullOrStroke(),info.getmPaint());
            if (angle>=info.getMiddleSweep()&&angle<=info.getEnd()){
                drawText(canvas,info);
            }
        }
    }


    private void drawCachedInfos(Canvas canvas,PieInfo excluded) {
        if (pieInfos!=null){
            for (PieInfo pieInfo : pieInfos) {

                if (excluded != null && excluded.equalsWith(pieInfo)) {
                    continue;
                }
                drawText(canvas,pieInfo);
                canvas.drawArc(mRectf, pieInfo.getStart(), pieInfo.getSweep(), config.isFullOrStroke(), pieInfo.getmPaint());
            }
        }
    }
    //已知半径 弧度求坐标
    private void drawText(Canvas canvas,PieInfo pieInfo)
    {
        if (pieInfo!=null){

            final float pointRadius=radius+(config.isFullOrStroke()?0:padding/2)+PixelUtils.dp2px(16);

            float cx = (float) (pointRadius * Math.cos(Math.toRadians(pieInfo.getMiddleSweep())));
            float cy = (float) (pointRadius * Math.sin(Math.toRadians(pieInfo.getMiddleSweep())));
            //画点
            Paint paint = pieInfo.getCopyPaint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx,cy,PixelUtils.dp2px(4), paint);

            LineGravity lineGravity=lineGravityPosition(cx,cy);

        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        final int width = getWidth() - getPaddingLeft() - getPaddingRight();
        final int height = getHeight() - getPaddingTop() - getPaddingBottom();
        radius=Math.min(width, height) / 2*0.6f;
        mRectf.set(-radius, -radius, radius, radius);
    }

    @Override
    public void onAnimationProcessing(float angle, @NonNull PieInfo infoImpl) {
        if (info!=null){
            if (angle >= info.getEnd()) {
                boolean hasAdded = false;
                for (PieInfo pieInfo : pieInfos) {
                    if (pieInfo.equalsWith(info)) {
                        hasAdded = true;
                        break;
                    }
                }
                if (!hasAdded) {
                    pieInfos.add(info);
                }
            }
        }
        this.angle = angle;
        this.info = infoImpl;
        invalidate();
    }

    private LineGravity lineGravityPosition(float startX,float startY){
        if (startX>0){
            return startY>0?LineGravity.TOP_LEFT:LineGravity.BOTTM_LEFT;
        }else if (startY<0){
            return startY>0?LineGravity.TOP_RIGHT:LineGravity.BOTTM_RIGHT;
        }else if (startY==0){
            return startX > 0 ? LineGravity.CENTER_RIGHT : LineGravity.CENTER_LEFT;
        }
        return LineGravity.TOP_RIGHT;
    }

}
