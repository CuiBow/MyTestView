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

public class MyCameraRotateHittingFaceView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Camera camera=new Camera();
    private Matrix matrix=new Matrix();
    Point point1 = new Point(200, 50);

    ValueAnimator animator=ValueAnimator.ofInt(0,360);
    int dergee;

    public MyCameraRotateHittingFaceView(Context context) {
        super(context);
    }

    public MyCameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
        bitmap.recycle();
        bitmap = scaledBitmap;

        animator.setDuration(5000);
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
        camera.setLocation(0,0,200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX1=point1.x+bitmap.getWidth()/2;
        int centerY1=point1.y+bitmap.getHeight()/2;


        camera.save();
        matrix.reset();
        camera.rotateX(dergee);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX1,-centerY1);
        matrix.postTranslate(centerX1,centerY1);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap,point1.x,point1.y,paint);
        canvas.restore();


    }
}
