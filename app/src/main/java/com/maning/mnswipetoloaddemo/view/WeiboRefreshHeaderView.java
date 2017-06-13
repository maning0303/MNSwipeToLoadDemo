package com.maning.mnswipetoloaddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.maning.mnswipetoloaddemo.R;

/**
 * Created by maning on 2017/4/10.
 * 微博下拉刷新
 */
public class WeiboRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView ivArrow;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    public WeiboRefreshHeaderView(Context context) {
        this(context, null);
    }

    public WeiboRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeiboRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_60);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在刷新...");
    }

    @Override
    public void onPrepare() {
        Log.d("WeiboRefreshHeaderView", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            if (y > mHeaderHeight) {
                tvRefresh.setText("释放刷新");
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("WeiboRefreshHeaderView", "onRelease()");
    }

    @Override
    public void onComplete() {
        rotated = false;
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
        rotated = false;
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("下拉刷新");
    }

}
