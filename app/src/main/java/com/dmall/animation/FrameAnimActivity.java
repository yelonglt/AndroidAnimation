package com.dmall.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 逐帧动画
 * Created by yelong on 16/8/22.
 * mail:354734713@qq.com
 */
public class FrameAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mGameView;
    private ImageView mWifiView;

    private Button mAscButton;
    private Button mDescButton;
    private Button mStopButton;
    private Button mGameButton;

    private AnimationDrawable mGameDrawable;
    private AnimationDrawable mWifiDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        mGameView = (ImageView) findViewById(R.id.gameImage);
        mWifiView = (ImageView) findViewById(R.id.wifiImage);
        mAscButton = (Button) findViewById(R.id.animAsc);
        mDescButton = (Button) findViewById(R.id.animDesc);
        mGameButton = (Button) findViewById(R.id.animGame);
        mStopButton = (Button) findViewById(R.id.animStop);
        mAscButton.setOnClickListener(this);
        mDescButton.setOnClickListener(this);
        mGameButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);

        mGameDrawable = (AnimationDrawable) mGameView.getBackground();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animAsc:
                mWifiDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_ascend);
                mWifiView.setImageDrawable(mWifiDrawable);
                assert mWifiDrawable != null;
                mWifiDrawable.start();
                break;
            case R.id.animDesc:
                mWifiDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_descend);
                mWifiView.setImageDrawable(mWifiDrawable);
                assert mWifiDrawable != null;
                mWifiDrawable.start();
                break;
            case R.id.animStop:
                if (null != mWifiDrawable && mWifiDrawable.isRunning()) {
                    mWifiDrawable.stop();
                }
                break;
            case R.id.animGame:
                if (!mGameDrawable.isRunning()) {
                    mGameDrawable.start();
                    mGameButton.setText("暂停游戏");
                } else {
                    mGameDrawable.stop();
                    mGameButton.setText("开始游戏");
                }
                break;
        }
    }
}
