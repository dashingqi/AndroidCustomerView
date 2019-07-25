package wanandroidqi.dashingqi.com.a02_viewpager;

import android.content.Context;

public class DensityUtil {

    /**
     * dp 转 px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        //获取到设备的像素密度
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * sp 转 px
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity; //获取到缩放密度
        return (int) (spValue * scaledDensity + 0.5f);
    }
}
