package edu.feicui.myapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.feicui.myapplication.R;

/**
 * Created by 荆兴业 on 2016/4/27.
 * 欢迎界面
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView mIvSplash;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        mAnimation.setAnimationListener(listener);
        mIvSplash.setAnimation(mAnimation);
    }

    private Animation.AnimationListener listener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(SplashActivity.this, TelMsgActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
