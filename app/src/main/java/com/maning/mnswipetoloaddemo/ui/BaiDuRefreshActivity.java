package com.maning.mnswipetoloaddemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maning.mnswipetoloaddemo.R;
import com.maning.mnswipetoloaddemo.view.BaiduRefreshHeaderView;

/**
 * 百度外卖刷新界面
 */
public class BaiDuRefreshActivity extends AppCompatActivity implements OnRefreshListener {

    private static final String TAG = "BaiDuRefreshActivity";
    private SwipeToLoadLayout swipeToLoadLayout;
    private ImageView iv_refresh_bg;
    private BaiduRefreshHeaderView swipe_refresh_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_refresh);

        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        iv_refresh_bg = (ImageView) findViewById(R.id.iv_refresh_bg);
        swipe_refresh_header = (BaiduRefreshHeaderView) findViewById(R.id.swipe_refresh_header);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
                swipeToLoadLayout.setRefreshFinalDragOffset(iv_refresh_bg.getHeight() - 100);
            }
        }, 100);

        swipe_refresh_header.setOnMoveListener(new BaiduRefreshHeaderView.OnMoveListener() {
            @Override
            public void onMove(int y) {
                int height = iv_refresh_bg.getHeight();
                int move = height - y - 20;
                Log.i(TAG, "y:" + y + ",move:" + move);
                if (move < 20) {
                    move = 20;
                }
                iv_refresh_bg.setY(-move);
            }
        });

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
