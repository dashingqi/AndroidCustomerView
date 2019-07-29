package com.example.indexwordproject;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private MyIndexWordsView mIndexWordsView;
    private TextView mTvWord;

    private Handler mHandler = new Handler();
    private RelativeLayout mRelativeTvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
            }
        });
    }
}
