package wanandroidqi.dashingqi.com.a01_ykcd;

import android.animation.ObjectAnimator;
import android.view.ViewGroup;

/**
 * @ProjectName: AndroidCustomerView
 * @Package: wanandroidqi.dashingqi.com.a01_ykcd
 * @ClassName: YkMenuTools
 * @Author: DashingQI
 * @CreateDate: 2019-07-22 21:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-07-22 21:04
 * @UpdateRemark:
 * @Version: 1.0
 */
public class YkMenuTools {

    public static void showMenu(ViewGroup view, int delayOffset) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 180, 360);
        animator.setStartDelay(delayOffset);
        animator.setDuration(500);
        animator.start();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }

    public static void showMenu(ViewGroup view) {
        showMenu(view, 0);
    }

    public static void hideMenu(ViewGroup view, int delayOffset) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 180);
        animator.setStartDelay(delayOffset);
        animator.setDuration(500);
        animator.start();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }

    public static void hideMenu(ViewGroup view) {
        hideMenu(view, 0);
    }
}
