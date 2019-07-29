package wanandroidqi.dashingqi.com.imitationviewpager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private MyViewPager mViewPager;
    private String[] contents = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        for (int i = 0; i < contents.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(contents[i]);
            textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            textView.setTextSize(18);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textView.setLayoutParams(layoutParams);
            mViewPager.addView(textView);
        }
    }

    private void initView() {
        mViewPager = findViewById(R.id.mViewPager);
    }
}
