package wanandroidqi.dashingqi.com.a01_ykcd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRelativeLevel1;
    private RelativeLayout mRelativeLevel2;
    private RelativeLayout mRelativeLevel3;
    private ImageView mIvHome;
    private ImageView mIvMenu;
    private ImageView mIvYk;
    private boolean isHideLevel2 = false;
    private boolean isHideLevel3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRelativeLevel1 = findViewById(R.id.relativeLevel1);
        mRelativeLevel2 = findViewById(R.id.relativeLevel2);
        mRelativeLevel3 = findViewById(R.id.relativeLevel3);
        mIvHome = findViewById(R.id.ivHome);
        mIvHome.setOnClickListener(this);
        mIvMenu = findViewById(R.id.ivMenu);
        mIvMenu.setOnClickListener(this);
        mIvYk = findViewById(R.id.ivYk);
        mIvYk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHome:
                if (!isHideLevel2) {
                    YkMenuTools.hideMenu(mRelativeLevel2);
                    isHideLevel2 = true;
                    if (!isHideLevel3) {
                        YkMenuTools.hideMenu(mRelativeLevel3, 200);
                        isHideLevel3 = true;
                    }
                } else {
                    YkMenuTools.showMenu(mRelativeLevel2);
                    isHideLevel2 = false;
                    if (isHideLevel3) {
                        isHideLevel3 = false;
                        YkMenuTools.showMenu(mRelativeLevel3, 200);
                    }
                }
                break;
            case R.id.ivMenu:
                if (isHideLevel3) {
                    YkMenuTools.showMenu(mRelativeLevel3);
                    isHideLevel3 = false;
                } else {
                    YkMenuTools.hideMenu(mRelativeLevel3);
                    isHideLevel3 = true;
                }
                break;
            case R.id.ivYk:
                break;
        }
    }
}
