package com.example.a03_togglebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyToggleButton extends View implements View.OnClickListener {

    private static final String TAG = "MyToggleButton";

    private Bitmap mBottomBitmap;
    private Bitmap mTopBitmap;
    private Paint paint;
    private int defaultSize;

    /**
     * 顶部最大滑动距离
     */
    private int maxDist;

    private int offsetX;

    /**
     * 是否打开开关的标记
     */
    private boolean isOpen = false;

    private boolean isClickEnable = true;

    private int firstX;

    public MyToggleButton(Context context) {
        this(context, null);
    }

    public MyToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToggleButton);
        BitmapDrawable bottomBgDrawable = (BitmapDrawable) typedArray.getDrawable(R.styleable.MyToggleButton_toggle_bottom_bg);
        mBottomBitmap = bottomBgDrawable.getBitmap();

        BitmapDrawable topBgDrawable = (BitmapDrawable) typedArray.getDrawable(R.styleable.MyToggleButton_toggle_top_bg);
        mTopBitmap = topBgDrawable.getBitmap();

        defaultSize = (int) typedArray.getDimension(R.styleable.MyToggleButton_default_size, 40);

        typedArray.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        maxDist = mBottomBitmap.getWidth() - mTopBitmap.getWidth();
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBottomBitmap.getWidth(), mBottomBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBottomBitmap, 0, 0, paint);
        canvas.drawBitmap(mTopBitmap, offsetX, 0, paint);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        if (isClickEnable) {
            isOpen = !isOpen;
            flushView();
        }
    }


    /**
     * 刷新视图
     */
    private void flushView() {
        if (isOpen) {
            offsetX = maxDist;
        } else {
            offsetX = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        super.onTouchEvent(event);
        int startX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = startX = (int) event.getX();
                isClickEnable = true;
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) event.getX();
                int offsetDist = endX - startX;
                offsetX += offsetDist;
                if (offsetX < 0) {
                    offsetX = 0;
                } else if (offsetX > maxDist) {
                    offsetX = maxDist;
                }
                invalidate();
                if (Math.abs(endX - firstX) > 8) {
                    isClickEnable = false;
                }
                startX = (int) event.getX();

                break;
            case MotionEvent.ACTION_UP:
                if (!isClickEnable) {
                    if (offsetX > maxDist / 2) {
                        isOpen = true;
                    } else {
                        isOpen = false;
                    }
                    flushView();
                }
                break;
        }
        return true;
    }
}
