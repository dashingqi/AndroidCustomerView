package com.example.indexwordproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Person> personData;

    public MyAdapter(Context context, List<Person> personData) {
        mContext = context;
        this.personData = personData;
    }

    @Override
    public int getCount() {
        return personData.size();
    }

    @Override
    public Person getItem(int position) {
        return personData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_pinyin, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            mViewHolder.mTvPinyin.setVisibility(View.VISIBLE);
            mViewHolder.mTvName.setText(personData.get(position).getName());
            mViewHolder.mTvPinyin.setText(personData.get(position).getPinyin().substring(0, 1));
        } else {
            String currentPinYin = personData.get(position).getPinyin().substring(0, 1);
            String prePinYin = personData.get(position - 1).getPinyin().substring(0, 1);
            if (currentPinYin.equals(prePinYin)) {
                mViewHolder.mTvPinyin.setVisibility(View.GONE);
            } else {
                mViewHolder.mTvPinyin.setVisibility(View.VISIBLE);
                mViewHolder.mTvPinyin.setText(currentPinYin);
            }

            mViewHolder.mTvName.setText(personData.get(position).getName());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView mTvName;
        TextView mTvPinyin;

        public ViewHolder(View view) {
            mTvName = view.findViewById(R.id.mTvName);
            mTvPinyin = view.findViewById(R.id.mTvWord);
        }
    }
}
