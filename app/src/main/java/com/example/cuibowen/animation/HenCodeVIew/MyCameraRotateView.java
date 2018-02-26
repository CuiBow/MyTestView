package com.example.cuibowen.animation.HenCodeVIew;

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

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/10/12.
 */

public class MyCameraRotateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Camera camera=new Camera();
    Point point1 = new Point(200, 100);
    Point point2 = new Point(600, 200);
    public MyCameraRotateView(Context context) {
        super(context);
    }

    public MyCameraRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCameraRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        camera.save();
        camera.rotateX(30);
        camera.applyToCanvas(canvas);
        canvas.drawBitmap(bitmap,point1.x,point1.y,paint);
        camera.restore();
        canvas.restore();


        canvas.save();
        camera.save();
        camera.rotateY(30);
        camera.applyToCanvas(canvas);
        canvas.drawBitmap(bitmap,point2.x,point2.y,paint);
        camera.restore();
        canvas.restore();
    }
}
