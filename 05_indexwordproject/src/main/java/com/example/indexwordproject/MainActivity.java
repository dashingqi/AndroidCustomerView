package com.example.indexwordproject;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private MyIndexWordsView mIndexWordsView;
    private TextView mTvWord;

    private Handler mHandler = new Handler();
    private RelativeLayout mRelativeTvWord;
    private List<Person> personData;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        personData = new ArrayList<>();
        personData.add(new Person("啊三", PinYinUtils.getPingYin("啊三")));
        personData.add(new Person("安倍", PinYinUtils.getPingYin("安倍")));
        personData.add(new Person("特兰普", PinYinUtils.getPingYin("特兰普")));
        personData.add(new Person("米歇尔", PinYinUtils.getPingYin("米歇尔")));
        personData.add(new Person("宋江", PinYinUtils.getPingYin("宋江")));
        personData.add(new Person("林聪", PinYinUtils.getPingYin("林聪")));
        personData.add(new Person("黄改改", PinYinUtils.getPingYin("黄改改")));
        personData.add(new Person("王琨", PinYinUtils.getPingYin("王琨")));
        personData.add(new Person("罗莎莎", PinYinUtils.getPingYin("罗莎莎")));
        personData.add(new Person("周瑜", PinYinUtils.getPingYin("周瑜")));
        personData.add(new Person("黄盖", PinYinUtils.getPingYin("黄盖")));
        personData.add(new Person("关于", PinYinUtils.getPingYin("关于")));
        personData.add(new Person("诸葛亮", PinYinUtils.getPingYin("诸葛亮")));
        personData.add(new Person("赵云", PinYinUtils.getPingYin("赵宇")));
        personData.add(new Person("张飞", PinYinUtils.getPingYin("张飞")));
        personData.add(new Person("曹操", PinYinUtils.getPingYin("曹操")));
        personData.add(new Person("魏聪", PinYinUtils.getPingYin("魏聪")));
        personData.add(new Person("武松", PinYinUtils.getPingYin("武松")));
        personData.add(new Person("马化腾", PinYinUtils.getPingYin("马化腾")));
        personData.add(new Person("码云", PinYinUtils.getPingYin("码云")));
        personData.add(new Person("李彦宏", PinYinUtils.getPingYin("李彦宏")));
        personData.add(new Person("程伟", PinYinUtils.getPingYin("程伟")));
        personData.add(new Person("张勇", PinYinUtils.getPingYin("张勇")));
        personData.add(new Person("安卓", PinYinUtils.getPingYin("安卓")));
        personData.add(new Person("牛牛", PinYinUtils.getPingYin("牛牛")));
        personData.add(new Person("常女士", PinYinUtils.getPingYin("常女士")));

        Collections.sort(personData, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPinyin().compareTo(o2.getPinyin());
            }
        });

        myAdapter = new MyAdapter(this, personData);
        mListView.setAdapter(myAdapter);


    }

    private void initView() {
        mListView = findViewById(R.id.mListView);
        mTvWord = findViewById(R.id.mTvWord);
        mIndexWordsView = findViewById(R.id.mIndexOWrsView);
        mRelativeTvWord = findViewById(R.id.mRelativeTvWord);

        mIndexWordsView.setIndexWords(new IIndexWordsListener() {
            @Override
            public void indexWords(String word) {


                mRelativeTvWord.setVisibility(View.VISIBLE);
                mTvWord.setText(word);
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRelativeTvWord.setVisibility(View.GONE);
                    }
                }, 3000);

                for (int i = 0; i < personData.size(); i++) {
                    String pinyin = personData.get(i).getPinyin().substring(0, 1);
                    if (pinyin.equals(word)) {
                        mListView.setSelection(i);
                        return;
                    }
                }
            }
        });
    }
}
