package wanandroidqi.dashingqi.com.a02_viewpager;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
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
}
