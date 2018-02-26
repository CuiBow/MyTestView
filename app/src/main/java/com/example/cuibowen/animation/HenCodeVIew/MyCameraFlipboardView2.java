package com.example.cuibowen.animation.HenCodeVIew;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/10/12.
 */

public class MyCameraFlipboardView2 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Camera camera=new Camera();
    Point point1 = new Point(200, 50);

    ValueAnimator animator=ValueAnimator.ofInt(0,-180);
    int dergee;

    public MyCameraFlipboardView2(Context context) {
        super(context);
    }

    public MyCameraFlipboardView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCameraFlipboardView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dergee= (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth=bitmap.getWidth();
        int bitmapHeight=bitmap.getHeight();
        int centerX=getWidth()/2;
        int centerY=getHeight()/2;
        int x=centerX-bitmapWidth/2;
        int y=centerY-bitmapHeight/2;



        //下半部分
        canvas.save();
        canvas.drawBitmap(bitmap,x,y,paint);
        canvas.restore();

        canvas.save();
        if (Math.abs(dergee)<90){
            canvas.clipRect(0,0,getWidth(),centerY);
        }else{
            canvas.clipRect(0,centerY,getWidth(),getHeight());
        }
        camera.save();
        camera.rotateX(dergee);
        canvas.translate(centerX,centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX,-centerY);
        camera.restore();
        canvas.drawBitmap(bitmap,x,y,paint);
        canvas.restore();

    }
}
