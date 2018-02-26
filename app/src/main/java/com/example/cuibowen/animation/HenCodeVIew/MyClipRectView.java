package com.example.cuibowen.animation.HenCodeVIew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/10/12.
 */

public class MyClipRectView extends View{
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public MyClipRectView(Context context) {
        super(context);
    }

    public MyClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = (getWidth() - bitmap.getWidth()) / 2;
        int top = (getHeight() - bitmap.getHeight()) / 2;
        canvas.save();
        canvas.clipRect(left+50,top+50,left+300,top+200);
        canvas.drawBitmap(bitmap,left,top,paint);
        canvas.restore();
    }
}
