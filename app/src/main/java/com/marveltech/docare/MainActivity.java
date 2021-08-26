package com.marveltech.docare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Animation animation1, animation2, animation3;
    ImageView imageView;
    TextView tv1, tv2;
    private static final int SPLASH_SCREEN_TIMEOUT = 7000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.mainlogo);
        animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.logoamination2);
        animation3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.logoanimation);
        tv1 = findViewById(R.id.docare);
        tv2 = findViewById(R.id.careforyou);
        imageView = findViewById(R.id.logo);
        imageView.setAnimation(animation1);
        tv1.startAnimation(animation1);
        tv2.startAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }
        },SPLASH_SCREEN_TIMEOUT);
    }
}