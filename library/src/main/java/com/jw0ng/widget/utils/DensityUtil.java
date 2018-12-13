package com.jw0ng.widget.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 分辨率转换工具类
 * @author Jw0ng
 * @version 1.0.0
 * @date 2018/12/1
 */
public class DensityUtil {

    /**
     * 转DP
     */
    public static int toDp(Context context, float value){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,context
                .getResources()
                .getDisplayMetrics());
    }

    /**
     * 转SP
     */
    public static float toSp(Context context, float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,value,context
                .getResources()
                .getDisplayMetrics());
    }

    /**
     * 转PX
     */
    public static float toPx(Context context, float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,value,context
                .getResources()
                .getDisplayMetrics());
    }
}
