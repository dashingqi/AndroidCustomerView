package wanandroidqi.dashingqi.com.imitationviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @ProjectName: AndroidCustomerView
 * @Package: wanandroidqi.dashingqi.com.imitationviewpager
 * @ClassName: MyViewPager
 * @Author: DashingQI
 * @CreateDate: 2019-07-26 20:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-07-26 20:52
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyViewPager extends ViewGroup {

    private String[] contents = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private Context mContext;
    private GestureDetector mGestureDetector;
    private static final String TAG = "MyViewPager";
    private float startX;
    private float endx;
    private int currentIndex;
    private final Scroller scroller;


    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        scroller = new Scroller(mContext);
        initView();

    }

    private void initView() {

        mGestureDetector = new GestureDetector(mContext, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //滑动
                Log.d(TAG, "onScroll: ");
                scrollBy((int) distanceX, getScrollY());
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(TAG, "onLongPress: ");
                //长按

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                endx = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                int tempIndex = currentIndex;
                if (startX - endx > getWidth() / 3) {
                    tempIndex++;
                } else if (endx - startX > getWidth() / 3) {
                    tempIndex--;
                }

                justIndex(tempIndex);
                break;
        }
        return true;

    }

    private void justIndex(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > contents.length - 1) {
            index = contents.length - 1;
        }
        currentIndex = index;


        int distanceX = currentIndex * getWidth() - getScrollX();
        scroller.startScroll(getScrollX(), 0, distanceX, 0, 500);
        //scrollTo(currentIndex * getWidth(), getScrollY());
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }

        postInvalidate();
    }
}
