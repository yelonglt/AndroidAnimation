package com.dmall.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 属性动画
 * 属性动画文件得放在res/animator目录
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private TextView mTextView;

    private RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.messageView);
        mLayout = (RelativeLayout) findViewById(R.id.layoutHeight);

        //采用ValueAnimator，监听动画过程，自己实现属性的改变
        //performAnimate(mLayout, mLayout.getHeight(), 500);

        //用一个类来包装原始对象，间接为其提供get和set方法
        ViewWrapper wrapper = new ViewWrapper(mLayout);
        ObjectAnimator.ofInt(wrapper, "height", mLayout.getHeight(), 500).setDuration(5000).start();

    }

    public void xmlAnimator() {
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

    /**
     * 执行修改高度动画
     *
     * @param target   目标视图
     * @param start    开始高度
     * @param end      最终高度
     * @param duration 动画持续时间
     */
    private void performAnimate(final View target, final int start, final int end, long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                Log.d(TAG, "current value: " + currentValue);

                //计算当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = currentValue / 100f;

                //直接调用整型估值器通过比例计算出宽度，然后给target设置高度
                target.getLayoutParams().height = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });

        valueAnimator.setDuration(duration).start();
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getHeight() {
            return mTarget.getLayoutParams().height;
        }

        public void setHeight(int height) {
            mTarget.getLayoutParams().height = height;
            mTarget.requestLayout();
        }
    }
}
