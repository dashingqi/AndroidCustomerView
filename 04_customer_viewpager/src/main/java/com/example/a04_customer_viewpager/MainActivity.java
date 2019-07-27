package com.example.a04_customer_viewpager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IRadioButtonCheckListener {

    private MyViewPager myViewPager;

    private String[] contents = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};
    private RadioGroup mLinearRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < contents.length; i++) {
            TextView mTextView = new TextView(this);
            mTextView.setText(contents[i]);
            mTextView.setTextSize(18);
            mTextView.setTextColor(Color.WHITE);
            mTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mTextView.setLayoutParams(layoutParams);
            mTextView.setGravity(Gravity.CENTER);
            myViewPager.addView(mTextView);

            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            if (i == 0) {
                radioButton.setChecked(true);
            }
            mLinearRadioGroup.addView(radioButton);
        }

    }

    private void initView() {
        myViewPager = findViewById(R.id.myViewPager);
        myViewPager.setRadioButtonListener(this);
        mLinearRadioGroup = findViewById(R.id.mLinearRadioGroup);
        mLinearRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myViewPager.justIndex(checkedId);
            }
        });
    }

    @Override
    public void setCheckId(int checkId) {
        //获取到当前滑动页面的page index
        mLinearRadioGroup.check(checkId);
    }
}
