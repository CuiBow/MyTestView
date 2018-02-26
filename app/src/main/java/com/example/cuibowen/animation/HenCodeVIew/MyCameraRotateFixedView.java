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

public class MyCameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Camera camera=new Camera();
    private Matrix matrix=new Matrix();
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    public MyCameraRotateFixedView(Context context) {
        super(context);
    }

    public MyCameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX1=point1.x+bitmap.getWidth()/2;
        int centerY1=point1.y+bitmap.getHeight()/2;
        int centerX2=point2.x+bitmap.getWidth()/2;
        int centerY2=point2.y+bitmap.getHeight()/2;

        canvas.save();
        camera.save();
        matrix.reset();
        camera.rotateX(30);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX1,-centerY1);
        matrix.postTranslate(centerX1,centerY1);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap,point1.x,point1.y,paint);

        canvas.restore();



        camera.save();
        matrix.reset();
        camera.rotateY(30);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX2,-centerY2);
        matrix.postTranslate(centerX2,centerY2);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap,point2.x,point2.y,paint);
        canvas.restore();
    }
}
