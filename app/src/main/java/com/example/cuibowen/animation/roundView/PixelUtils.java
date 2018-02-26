package com.example.cuibowen.animation.roundView;

import android.content.Context;

/**
 * Created by cuibowen on 2017/11/21.
 */

public class PixelUtils {

    public static Context mContext;

    public static void init(Context context){
        mContext=context;
    }
    static int dp2px(float dpValue) {
        if (mContext == null) return (int) (dpValue * 1.5f + 0.5f);
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
