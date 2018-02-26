package com.example.cuibowen.animation.HenCodeVIew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/10/12.
 */

public class MyClipPathView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    Path path1 = new Path();
    Path path2 = new Path();
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    public MyClipPathView(Context context) {
        super(context);
    }

    public MyClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

        path1.addCircle(point1.x+200,point1.y+200,150, Path.Direction.CW);

        path2.setFillType(Path.FillType.INVERSE_WINDING);
        path2.addCircle(point2.x+200,point2.y+200,150, Path.Direction.CW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipPath(path1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.clipPath(path2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
