package com.example.a04_customer_viewpager;

import android.os.SystemClock;

public class MyScroller {

    private float mStartX;
    private float mStartY;
    private float mDx;
    private float mDy;
    private float mDuration;
    private long mStartTime;
    private boolean isFinish;
    private long mEndTime;
    private float speed;
    private float mCurrX;

    /**
     * @param startX   开始的X轴上的位置
     * @param startY   开始的Y轴上的位置
     * @param dx       X轴待滑动的距离
     * @param dy       Y轴待滑动的距离
     * @param duration 滑动距离执行的时间
     */
    public void startScroll(float startX, float startY, float dx, float dy, float duration) {
        mStartX = startX;
        mStartY = startY;
        mDx = dx;
        mDy = dy;
        mDuration = duration;
        mStartTime = SystemClock.uptimeMillis();
        isFinish = false;
    }

    /**
     * false 表示结束移动
     * true 表示还在移动
     *
     * @return
     */
    public boolean computeScrollOffset() {
        if (isFinish) {
            return false;
        }

        mEndTime = SystemClock.uptimeMillis();
        //计算一小段滑动的时间
        long mSmallTime = mEndTime - mStartTime;
        //计算速度
        speed = mDx / mDuration;

        //小于当前要滑动的总时间，说明还在滑动
        if (mSmallTime < mDuration) {

            //计算一小段时间移动的距离
            float mSmallOffset = mSmallTime * speed;
            //每次将滑动的一小段距离进行相加，记录当前一发动移动了多长。
            mCurrX = mStartX + mSmallOffset;

        } else {
            //就不滑动,滑动结束
            isFinish = true;
            //滑动结束了，当前在X轴滑动的距离就是要滑动的总距离
            mCurrX = mStartX + mDx;
        }

        return true;
    }

    /**
     * 获取到当前滑动的距离
     *
     * @return
     */
    public int getCurrX() {
        return (int) mCurrX;
    }
}
