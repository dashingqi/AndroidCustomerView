package com.example.indexwordproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyIndexWordsView extends View {
    private float viewWidth;
    private float viewHeight;
    private float itemWidth;
    private float itemHeight;
    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "W", "X", "Y", "Z"};
    private Paint mPaint;
    private Context mContext;
    private final float mDefaultWidth;
    private final float mWordsSize;
    private int currentIndex = -1;
    private IIndexWordsListener listener;


    public MyIndexWordsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyIndexWordsView);
        mDefaultWidth = typedArray.getDimension(R.styleable.MyIndexWordsView_defaultWidth, 30);
        mWordsSize = typedArray.getDimension(R.styleable.MyIndexWordsView_wordsSize, 18);
        //回收，防止内存泄漏
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置文字字体大小
        mPaint.setTextSize(mWordsSize);
        //设置文字字体加粗
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) mDefaultWidth, (int) mDefaultWidth);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) mDefaultWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, (int) mDefaultWidth);
        }
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        itemWidth = viewWidth;
        itemHeight = viewHeight / words.length;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
            //获取到字母
            String word = words[i];
            Rect wordRect = new Rect();
            //用矩形将字符串框起来，这样可以通过rect获取到字符串的信息。
            mPaint.getTextBounds(word, 0, 1, wordRect);
            int wordHeight = wordRect.height();
            int wordWidth = wordRect.width();
            //获取到文字的x坐标
            int wordX = (int) (itemWidth / 2 - wordWidth / 2);
            int heightX = (int) ((i * itemHeight) + itemHeight / 2 + wordHeight / 2);
            if (i == currentIndex) {
                mPaint.setColor(Color.GREEN);
            } else {
                mPaint.setColor(Color.BLACK);
            }

            canvas.drawText(word, wordX, heightX, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                float y = event.getY();
                currentIndex = (int) ((y / viewHeight) * words.length);
                invalidate();
                if (listener != null) {
                    listener.indexWords(words[currentIndex]);
                }
                break;
            case MotionEvent.ACTION_UP:
                currentIndex = -1;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public void setIndexWords(IIndexWordsListener listener) {
        this.listener = listener;
    }
}
