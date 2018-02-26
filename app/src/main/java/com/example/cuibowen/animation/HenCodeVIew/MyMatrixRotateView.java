package com.example.cuibowen.animation.HenCodeVIew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class MyMatrixRotateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Matrix matrix=new Matrix();
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public MyMatrixRotateView(Context context) {
        super(context);
    }

    public MyMatrixRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMatrixRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        matrix.reset();
        matrix.postRotate(180,point1.x+bitmap.getWidth()/2,point1.y+bitmap.getHeight()/2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        matrix.reset();
        matrix.postRotate(45,point2.x+bitmap.getWidth()/2,point2.y+bitmap.getHeight()/2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
