package wanandroidqi.dashingqi.com.a02_viewpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    /**
     * 装载指示器的布局
     */
    private LinearLayout mLinearIndicator;
    private ViewPager mViewPager;
    /**
     * 标题栏TextView
     */
    private TextView mTvTitle;
    /**
     * ViewPager展示的数组
     */
    private List<TextView> mTextViewList;
    /**
     * 指示器的标题栏文本数组
     */
    private String[] titles = {"星期一", "星期二", "星期三", "星期四", "星期五"};

    /**
     * 是否是拖动
     */
    private boolean isDragged = false;

    private List<ImageView> mImageViewList;

    private int prePosition = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int position = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(position + 1);
            //4秒钟切换一页
            mHandler.sendEmptyMessageDelayed(0, 4000);
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewList = new ArrayList<>();
        mImageViewList = new ArrayList<>();
        initView();
        initData();

        mHandler.sendEmptyMessageDelayed(0, 3000);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            TextView mTextView = new TextView(this);
            mTextView.setText(titles[i]);
            mTextView.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mTextView.setLayoutParams(layoutParams);
            mTextView.setGravity(Gravity.CENTER);
            mTextViewList.add(mTextView);

            ImageView mIndicatorIv = new ImageView(this);
            mIndicatorIv.setBackgroundResource(R.drawable.iv_indicator_selector);
            //代码里设置的都是像素
            int widthPx = DensityUtil.dp2px(MainActivity.this, 8);
            int heightPx = DensityUtil.dp2px(MainActivity.this, 8);
            LinearLayout.LayoutParams mIndicatorIvLayout = new LinearLayout.LayoutParams(widthPx, heightPx);
            if (i == 0) {
                mIndicatorIv.setEnabled(true);
            } else {
                mIndicatorIv.setEnabled(false);
                mIndicatorIvLayout.leftMargin = widthPx;
            }
            mLinearIndicator.addView(mIndicatorIv);

            mIndicatorIv.setLayoutParams(mIndicatorIvLayout);
            mImageViewList.add(mIndicatorIv);

        }
        final MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this, mTextViewList);
        mViewPager.setAdapter(myViewPagerAdapter);
        //item 必须是 titles.length的整数倍
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % titles.length;
        //将ViewPager第一个Item设置到中间位置，这样左右都有初始化好的Page，就可以保证无限左右滑动了
        mViewPager.setCurrentItem(item);

        //进行取模操作
        mTvTitle.setText(titles[item % titles.length]);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当ViewPager已经滑动回调这个方法
             * @param position
             * @param v
             * @param i1
             */
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            /**
             * 当ViewPager选中的时候回调
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                mImageViewList.get(prePosition).setEnabled(false);
                prePosition = position % titles.length;
                mImageViewList.get(position % titles.length).setEnabled(true);
                mTvTitle.setText(titles[position % titles.length]);
            }

            /**
             * 当ViewPager滑动的状态发生改变的时候回调。
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                //拖拽
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    Log.d(TAG, "onPageScrollStateChanged: dragging");
                    isDragged = true;
                    mHandler.removeCallbacksAndMessages(null);

                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    //滑动
                    Log.d(TAG, "onPageScrollStateChanged: settling");

                } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragged) {
                    //空闲
                    Log.d(TAG, "onPageScrollStateChanged: idle");
                    isDragged = false;
                    mHandler.sendEmptyMessageDelayed(0, 4000);
                }
            }
        });
    }

    private void initView() {
        mLinearIndicator = findViewById(R.id.mLinearIndicator);
        mViewPager = findViewById(R.id.mViewPager);
        mTvTitle = findViewById(R.id.mTvTitle);

    }


    class MyViewPagerAdapter extends PagerAdapter {

        private List<TextView> mTvLists;

        private Context mContext;

        public MyViewPagerAdapter(Context context, List<TextView> tvLists) {
            mContext = context;
            mTvLists = tvLists;

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int realPosition = position % mTvLists.size();
            TextView mTvTextView = mTvLists.get(realPosition);
            //将获取到控件 添加在布局中，不添加不显示呀！！！！！！
            container.addView(mTvTextView);
            mTvTextView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            Log.d(TAG, "onTouch: Down");
                            //当手机按下的时候，就将消息队列清空,这样手指滑动停留在两个页面中间，就不会自动滑动了。
                            mHandler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d(TAG, "onTouch: Move");
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d(TAG, "onTouch: Up");
                            mHandler.sendEmptyMessageDelayed(0, 4000);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            Log.d(TAG, "on Touch: cancel");
                            break;
                    }
                    //不返回true 是为点击事件做准备的。返回true就因为这拦截消费这个点击事件了，就调用不到onClickListener事件了。
                    return true;
                }
            });
            return mTvTextView;
        }

        @Override
        public int getCount() {
            //可以无限滑动的前提
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
