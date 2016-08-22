package com.dmall.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 属性动画
 * 属性动画文件得放在res/animator目录
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.messageView);

        //加载XML动画
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_set);
        //将动画添加到某个对象上
        animator.setTarget(mTextView);
        animator.start();
    }

    /**
     * ObjectAnimator继承于ValueAnimator,底层动画也是基于ValueAnimator实现的
     */
    public void objectAnimator() {
        //淡入淡出动画
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
        //旋转动画
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTextView, "rotation", 0f, 360);
        //水平移动动画
        ObjectAnimator moveX = ObjectAnimator.ofFloat(mTextView, "translationX",
                mTextView.getTranslationX(), -500f, mTextView.getTranslationX());
        //垂直缩放动画
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTextView, "scaleY", 1f, 3f, 1f);

        /**
         * 组合属性动画
         * 调用play方法返回一个AnimatorSet.Builder的实例,里面包括四个方法
         * after(Animator anim)将现有动画插入到传入的动画之后执行
         * before(Animator anim)将现有动画插入到传入的动画之前执行
         * with(Animator anim)将现有动画和传入的动画同时执行
         */
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotate).with(fadeInOut).after(moveX).after(scaleY);

        animatorSet.setDuration(3000);
        animatorSet.start();
        //设置动画的监听器
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        //只实现自己想监听的方法
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
    }

    /**
     * ValueAnimator是属性动画最核心的类
     */
    public void valueAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt();
        animator.setDuration(300);
        //设置动画延迟播放时间
        animator.setStartDelay(100);
        //设置动画的重复次数
        animator.setRepeatCount(2);
        //设置重复模式,只有两种重新播放和倒序播放
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }
}
