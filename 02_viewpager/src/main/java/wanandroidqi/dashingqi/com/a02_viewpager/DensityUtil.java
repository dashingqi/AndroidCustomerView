package wanandroidqi.dashingqi.com.a02_viewpager;

import android.content.Context;

public class DensityUtil {

    public static int dp2px(Context context, float dpValue) {
        //获取到像素密度
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);

    }
}
