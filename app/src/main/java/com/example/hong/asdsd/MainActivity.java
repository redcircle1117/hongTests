package com.example.hong.asdsd;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Filter;

public class MainActivity extends Activity  {

    private TextView tv_main_q;
    private TextView tv_main;
    private ImageButton ib_main;

    MyReceiver receiver = new MyReceiver();
    MyReceiver2 receiver2 = new MyReceiver2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_main = (TextView) findViewById(R.id.tv_main);
        tv_main_q = (TextView) findViewById(R.id.tv_main_q);
        ib_main = (ImageButton) findViewById(R.id.ib_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver2, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver2);
    }


    class MyReceiver2 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            if(activeNetworkInfo == null && activeNetworkInfo.isAvailable()){
                Toast.makeText(context, "没有网络", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "网络改变", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
