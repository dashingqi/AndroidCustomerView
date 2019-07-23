package wanandroidqi.dashingqi.com.a02_viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @ProjectName: AndroidCustomerView
 * @Package: wanandroidqi.dashingqi.com.a02_viewpager
 * @ClassName: MyViewPagerAdapter
 * @Author: DashingQI
 * @CreateDate: 2019-07-24 00:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-07-24 00:08
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyViewPagerAdapter extends PagerAdapter {

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
        container.addView(mTvTextView);
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
