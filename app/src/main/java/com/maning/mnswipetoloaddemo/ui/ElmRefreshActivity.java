package com.maning.mnswipetoloaddemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maning.mnswipetoloaddemo.R;

/**
 * 饿了吗刷新界面
 */
public class ElmRefreshActivity extends AppCompatActivity implements OnRefreshListener {

    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elm_refresh);

        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);

        swipeToLoadLayout.setOnRefreshListener(this);

        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 100);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
