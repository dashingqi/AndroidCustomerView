package wanandroidqi.dashingqi.com.a02_viewpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
     * 每次滑动对应的page角标
     */
    private int item;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            item++;
            mViewPager.setCurrentItem(item);
            //4秒钟切换一页
            mHandler.sendEmptyMessageDelayed(0, 4000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewList = new ArrayList<>();
        initView();
        initData();

        mHandler.sendEmptyMessageDelayed(0, 3000);

    }


    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            TextView mTextView = new TextView(this);
            mTextView.setText(titles[i]);
            mTextView.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mTextView.setLayoutParams(layoutParams);
            mTextView.setGravity(Gravity.CENTER);
            mTextViewList.add(mTextView);
        }
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this, mTextViewList);
        mViewPager.setAdapter(myViewPagerAdapter);
        //item 必须是 titles.length的整数倍
        item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % titles.length;
        //将ViewPager第一个Item设置到中间位置，这样左右都有初始化好的Page，就可以保证无限左右滑动了
        mViewPager.setCurrentItem(item);
        mTvTitle.setText(titles[item % titles.length]);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当ViewPager已经滑动回调这个方法
             * @param i
             * @param v
             * @param i1
             */
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * 当ViewPager选中的时候回调
             * @param i
             */
            @Override
            public void onPageSelected(int i) {

                mTvTitle.setText(titles[i % titles.length]);
            }

            /**
             * 当ViewPager滑动的状态发生改变的时候回调。
             * @param i
             */
            @Override
            public void onPageScrollStateChanged(int i) {

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
            position = position % mTvLists.size();
            TextView mTvTextView = mTvLists.get(position);
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
                            mHandler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d(TAG, "onTouch: Up");
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            mHandler.sendEmptyMessageDelayed(0, 4000);
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
