package com.maning.mnswipetoloaddemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.maning.mnswipetoloaddemo.R;

/**
 * Created by maning on 2017/4/10.
 * 天猫下拉刷新
 */
public class TianMaoRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private static final String TAG = "TianMaoRefreshHeaderView";

    private ImageView ivRefresh;
    private LinearLayout llRefresh;
    private TextView tvRefresh;

    private AnimationDrawable mAnimDrawable;

    private int mHeaderHeight;


    public TianMaoRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TianMaoRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TianMaoRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        llRefresh = (LinearLayout) findViewById(R.id.llRefresh);
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onRefresh() {
        tvRefresh.setText("正在刷新...");
        ivRefresh.setBackgroundResource(R.drawable.animation_list_refresh_tm_release);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (y > mHeaderHeight) {
                tvRefresh.setText("释放刷新");
            } else if (y < mHeaderHeight) {
                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        mAnimDrawable.stop();
        ivRefresh.setBackgroundResource(R.drawable.animation_list_refresh_tm_pull);
        tvRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        ivRefresh.setBackgroundResource(R.drawable.animation_list_refresh_tm_pull);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        tvRefresh.setText("下拉刷新");
    }
}
