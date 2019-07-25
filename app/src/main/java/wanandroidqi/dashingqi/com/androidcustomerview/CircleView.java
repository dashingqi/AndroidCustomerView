package wanandroidqi.dashingqi.com.androidcustomerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CircleView extends View {
    private static final String TAG = CircleView.class.getSimpleName();

    /**
     * 获取到颜色
     */
    private int circleColor;
    /**
     * 获取奥默认大小
     */
    private int defaultSize;
    private Paint mPaint;
    private BitmapDrawable mBitmapDrawable;
    private Bitmap mBitmap;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //工具方法
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        circleColor = typedArray.getColor(R.styleable.CircleView_circle_color, Color.RED);
        defaultSize = (int) typedArray.getDimension(R.styleable.CircleView_circle_default_size, 100);
        //画一张图片
        Drawable drawable = typedArray.getDrawable(R.styleable.CircleView_circle_default_bg);
        mBitmapDrawable = (BitmapDrawable) drawable;
        mBitmap = mBitmapDrawable.getBitmap();

        typedArray.recycle();

        //遍历的方式
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            Log.d(TAG, attrs.getAttributeName(i) + "======" + attrs.getAttributeValue(i));
        }

        //命名
        String circle_default_bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "circle_default_bg");
        Log.d(TAG, "CircleView: " + circle_default_bg);
        String circle_default_size = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "circle_default_size");
        Log.d(TAG, "CircleView: " + circle_default_size);


        init();
    }


    /**
     * 初始化操作
     */
    private void init() {
        //声明一个画笔
        if (mPaint == null)
            mPaint = new Paint();
        //给画笔设置颜色
        mPaint.setColor(circleColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //单独处理下 wrap_content 因为在getChildrenMeasure方法中 我们知道 当为 wrap_content的时候，获取到的size和match_parent是一样，都是父view剩余的空间大小
        //当为wrap_content 指定一个固定的默认大小。
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultSize, defaultSize);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultSize, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defaultSize);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        Log.d(TAG, "width = " + width + " height = " + height);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        Log.d(TAG, "paddingLeft = " + paddingLeft + " paddingRight = " + paddingRight + " paddingTop = " + paddingTop + " paddingBottom = " + paddingBottom);

        //考虑padding
        int realWidth = width - paddingLeft - paddingRight;
        int realHeight = height - paddingBottom - paddingTop;

        int radius = Math.min(realWidth, realHeight) / 2;
        Log.d(TAG, "radius = " + radius);

        //开始画圆
        canvas.drawCircle(realWidth
                / 2, realHeight / 2, radius, mPaint);

        //画一张图片
        canvas.drawBitmap(mBitmap, 20, 20, mPaint);

    }
}
