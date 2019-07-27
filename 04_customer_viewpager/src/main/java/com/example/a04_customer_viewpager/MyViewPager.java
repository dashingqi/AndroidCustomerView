package com.example.a04_customer_viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyViewPager extends ViewGroup {
    private static final String TAG = "MyViewPager";

    private GestureDetector mGestureDetector;
    private final Context mContext;
    private float startX;
    private float endX;
    private int currentIndex;
    private MyScroller myScroller;
    private IRadioButtonCheckListener mListener;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public void setRadioButtonListener(IRadioButtonCheckListener mListener) {
        this.mListener = mListener;
    }

    private void initView() {
        myScroller = new MyScroller();
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
                scrollBy((int) distanceX, getScrollY());
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

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
            childView.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: Down");
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: MOVE");
                endX = event.getX();
                Log.d(TAG, "onTouchEvent: endX = " + endX);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: UP");
                int tempIndex = currentIndex;
                if (startX - endX > getWidth() / 3) {
                    tempIndex++;
                } else if (endX - startX > getWidth() / 3) {
                    tempIndex--;
                }
                justIndex(tempIndex);
                break;
        }
        return true;
    }

    public void justIndex(int tempIndex) {
        if (tempIndex < 0) {
            tempIndex = 0;
        } else if (tempIndex > getChildCount() - 1) {
            tempIndex = getChildCount() - 1;
        }

        currentIndex = tempIndex;
        if (mListener != null)
            mListener.setCheckId(currentIndex);
        myScroller.startScroll(getScrollX(), getScrollY(), currentIndex * getWidth() - getScrollX(), 0, 200);
        //触发onDraw()和computeScroll()方法。
        invalidate();
    }

    @Override
    public void computeScroll() {
        //如果还在移动
        if (myScroller.computeScrollOffset()) {
            //开始执行，每次都执行到指定的位置
            scrollTo(myScroller.getCurrX(), getScrollY());
            postInvalidate();
        }
    }
}
