package com.example.ayaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ayaya;
    private MediaPlayer ayayaSound, superAyayaSound;
    private TextView countTextView;
    private int mcount = 0;
    private static long back_pressed;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        ayaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ayayaSound.isPlaying()) {
                    ayayaSound.release();
                    ayayaSound = null;
                }
                if (superAyayaSound.isPlaying()) {
                    superAyayaSound.release();
                    superAyayaSound = null;
                }
                checkRelease();
                randomizeSound();
                incrementCount();
            }
        });

    }

    private void initViews() {
        ayaya = (ImageView) findViewById(R.id.ayaya);
        countTextView = (TextView) findViewById(R.id.count);
        ayayaSound = MediaPlayer.create(this, R.raw.ayaya);
        superAyayaSound = MediaPlayer.create(this, R.raw.super_ayaya);
        title = (TextView) findViewById(R.id.title);
    }

    public void soundPlay(MediaPlayer sound) {
        sound.start();
    }

    public void randomizeSound() {
        int min = 1;
        int max = 10;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        if (i == 4 || i == 7 || i == 10) {
            soundPlay(superAyayaSound);
            final Animation animationRotateCenter = AnimationUtils.loadAnimation(
                    this, R.anim.anim_center);
//            animationRotateCenter.setDuration(2000);
            ayaya.startAnimation(animationRotateCenter);
            final Animation animationRotateCenter1 = AnimationUtils.loadAnimation(
                    this, R.anim.anim_center);
            title.startAnimation(animationRotateCenter1);
            final Animation animationRotateCenter2 = AnimationUtils.loadAnimation(
                    this, R.anim.anim_center);
            countTextView.startAnimation(animationRotateCenter2);
            animationRotateCenter.setDuration(2000);
            animationRotateCenter1.setDuration(2000);
            animationRotateCenter2.setDuration(2000);
        } else {
            soundPlay(ayayaSound);
        }

    }

    public void incrementCount() {
        mcount++;
        countTextView.setText(String.valueOf(mcount));
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "АYAYA AGAIN TO EXIT",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    public void checkRelease() {
        if (ayayaSound == null) {
            ayayaSound = MediaPlayer.create(this, R.raw.ayaya);
        }
        if (superAyayaSound == null) {
            superAyayaSound = MediaPlayer.create(this, R.raw.super_ayaya);
        }
    }
}