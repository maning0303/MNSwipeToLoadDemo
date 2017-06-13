package com.maning.mnswipetoloaddemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.maning.mnswipetoloaddemo.R;


/**
 * Created by maning on 2017/4/10.
 * 百度外卖刷新
 */
public class BaiduRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private Animation rotateAnimation;
    private Animation pull_back_anim_right_cloud_linear;
    private Animation pull_back_anim_left_cloud_linear;
    private Animation pull_back_anim_left_linear;
    private Animation pull_back_anim_right_linear;

    private ImageView iv_sun;
    private ImageView ivRefresh;
    private ImageView iv_back_cloud;
    private ImageView iv_back_cloud2;
    private ImageView iv_back_castle;
    private ImageView iv_back_castle2;

    private AnimationDrawable mAnimDrawable;


    public BaiduRefreshHeaderView(Context context) {
        this(context, null);
    }

    public BaiduRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaiduRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_sun);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);

        pull_back_anim_left_cloud_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_left_cloud_linear);
        pull_back_anim_right_cloud_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_right_cloud_linear);
        pull_back_anim_left_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_left_linear);
        pull_back_anim_right_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_right_linear);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        iv_sun = (ImageView) findViewById(R.id.iv_sun);
        iv_back_cloud = (ImageView) findViewById(R.id.iv_back_cloud);
        iv_back_cloud2 = (ImageView) findViewById(R.id.iv_back_cloud2);
        iv_back_castle = (ImageView) findViewById(R.id.iv_back_castle);
        iv_back_castle2 = (ImageView) findViewById(R.id.iv_back_castle2);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();

        //开启动画
        openAnimation();

    }

    private void openAnimation() {
        //开启动画
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }

        iv_sun.clearAnimation();
        iv_back_cloud.clearAnimation();
        iv_back_cloud2.clearAnimation();
        iv_back_castle.clearAnimation();
        iv_back_castle2.clearAnimation();

        iv_sun.startAnimation(rotateAnimation);
        iv_back_cloud.startAnimation(pull_back_anim_left_cloud_linear);
        iv_back_cloud2.startAnimation(pull_back_anim_right_cloud_linear);
        iv_back_castle.startAnimation(pull_back_anim_left_linear);
        iv_back_castle2.startAnimation(pull_back_anim_right_linear);
    }

    @Override
    public void onRefresh() {


    }

    @Override
    public void onPrepare() {
        Log.d("BaiduRefreshHeaderView", "onPrepare()");
        //开启动画
        openAnimation();

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (onMoveListener != null) {
                onMoveListener.onMove(y);
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("BaiduRefreshHeaderView", "onRelease()");
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        iv_sun.clearAnimation();
        iv_back_cloud.clearAnimation();
        iv_back_cloud2.clearAnimation();
        iv_back_castle.clearAnimation();
        iv_back_castle2.clearAnimation();
    }

    private OnMoveListener onMoveListener;

    public interface OnMoveListener {
        void onMove(int y);
    }

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

}
