package com.example.hong.circlebar;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private CircleBar cb_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_main = (CircleBar) findViewById(R.id.cb_main);
    }

    public void start(View v) {
        new Thread(){
            @Override
            public void run() {
                cb_main.setMaxProgress(100);
                for (int i = 0; i < cb_main.getMaxProgress(); i++) {
                    SystemClock.sleep(50);
                  cb_main.setProgress(cb_main.getProgress() + 1);
                }
            }
        }.start();
    }
}
