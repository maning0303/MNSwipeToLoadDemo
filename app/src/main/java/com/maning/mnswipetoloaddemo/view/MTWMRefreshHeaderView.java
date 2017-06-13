package com.maning.mnswipetoloaddemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.maning.mnswipetoloaddemo.R;

/**
 * Created by maning on 2017/4/10.
 * 美团外卖下拉刷新
 */
public class MTWMRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView ivRefresh;
    private LinearLayout llRefresh;

    private AnimationDrawable mAnimDrawable;

    private int mHeaderHeight;


    public MTWMRefreshHeaderView(Context context) {
        this(context, null);
    }

    public MTWMRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTWMRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        llRefresh = (LinearLayout) findViewById(R.id.llRefresh);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onRefresh() {
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        llRefresh.setAlpha(0.3f);
        ivRefresh.setScaleX(0.4f);
        ivRefresh.setScaleY(0.4f);
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            float scale = (float) y / (float) mHeaderHeight;
            if (y >= mHeaderHeight) {
                ivRefresh.setScaleX(1);
                ivRefresh.setScaleY(1);
                llRefresh.setAlpha(1.0f);
            } else if (y > 0 && y < mHeaderHeight) {
                ivRefresh.setScaleX(scale);
                ivRefresh.setScaleY(scale);
                llRefresh.setAlpha(scale);
            } else {
                ivRefresh.setScaleX(0.4f);
                ivRefresh.setScaleY(0.4f);
                llRefresh.setAlpha(0.3f);
            }
        }
    }

    @Override
    public void onRelease() {
        mAnimDrawable.stop();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        llRefresh.setAlpha(1.0f);
    }
}
