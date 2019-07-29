package com.example.indexwordproject;

import android.content.Context;

public class DenistyUtils {

    public static float dp2px(Context context, float defaultSize) {
        //获取到设备的密度
        float density = context.getResources().getDisplayMetrics().density;

        return density * defaultSize + 0.5f;

    }

    public static float sp2px(Context context, float defaultSize) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return scaledDensity * defaultSize + 0.5f;
    }
}
