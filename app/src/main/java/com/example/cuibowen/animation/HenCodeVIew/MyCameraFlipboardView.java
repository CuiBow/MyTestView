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

public class MyCameraFlipboardView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Camera camera=new Camera();
    private Matrix matrix=new Matrix();
    Point point1 = new Point(200, 50);

    ValueAnimator animator=ValueAnimator.ofInt(0,180);
    int dergee;

    public MyCameraFlipboardView(Context context) {
        super(context);
    }

    public MyCameraFlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCameraFlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);

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


        //上半部分
        canvas.save();
        canvas.clipRect(0,0,getWidth(),centerY);
        canvas.drawBitmap(bitmap,x,y,paint);
        canvas.restore();


        // 第二遍绘制：下半部分
        canvas.save();

        if (dergee < 90) {
            canvas.clipRect(0, centerY, getWidth(), getHeight());
        } else {
            canvas.clipRect(0, 0, getWidth(), centerY);
        }
        camera.save();
        camera.rotateX(dergee);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();


    }
}
