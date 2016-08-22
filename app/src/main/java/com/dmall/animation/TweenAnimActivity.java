package com.dmall.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * 补间动画
 * Created by yelong on 16/8/22.
 * mail:354734713@qq.com
 */
public class TweenAnimActivity extends AppCompatActivity {

    public static final String ANIM_TYPE = "anim_type";

    private ImageView mCardView;
    private ImageView mCircleView;

    private int animType;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        mCardView = (ImageView) findViewById(R.id.cardView);
        mCircleView = (ImageView) findViewById(R.id.circleView);

        animType = getIntent().getIntExtra(ANIM_TYPE, 0);
        switch (animType) {
            case 0:
                mAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                mCardView.startAnimation(mAnimation);

                // Animation alphaAn = new AlphaAnimation(0.1f, 1.0f);
                // alphaAn.setDuration(3000);
                // mCardView.startAnimation(alphaAn);
                break;
            case 1:
                mAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                mCardView.startAnimation(mAnimation);

                // Animation alphaAn = new ScaleAnimation(0, 2, 0, 2,
                // Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                // alphaAn.setDuration(2000);
                // alphaAn.setFillAfter(true);
                // mCardView.startAnimation(alphaAn);
                break;
            case 2:
                mAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                mCardView.startAnimation(mAnimation);

                // Animation alphaAn = new TranslateAnimation(0, 500, 0, 500);
                // alphaAn.setDuration(3000);
                // mCardView.startAnimation(alphaAn);
                break;
            case 3:
                mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                mCircleView.startAnimation(mAnimation);
                break;
            case 4:
                mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_translate);
                mCircleView.startAnimation(mAnimation);
                break;
            case 5:
                groupAnimation();
                break;
            default:
                break;
        }
    }

    /**
     * 组合动画
     */
    private void groupAnimation() {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.back_scale);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCardView.setBackgroundResource(R.drawable.card2);
                mAnimation = AnimationUtils.loadAnimation(
                        TweenAnimActivity.this, R.anim.front);
                // 通过AnimationUtils得到动画配置文件(/res/anim/front.xml),然后在把动画交给ImageView
                mCardView.startAnimation(mAnimation);
            }
        });
        mCardView.startAnimation(mAnimation);
    }
}
