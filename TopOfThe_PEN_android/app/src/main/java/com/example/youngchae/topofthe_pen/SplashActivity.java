package com.example.youngchae.topofthe_pen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by youngchae on 2016-07-24.
 */
public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext()
                        , MainActivity.class);
                startActivity(intent);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 5000);
    }

    @Override
    protected void onDestroy() {
        Log.i("test", "onDstory()");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
