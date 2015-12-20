package com.example.hong.viewpager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
    private MyViewPager mvp_main;
    private RadioGroup rg_main;

    private int[] pics = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
            R.drawable.a4, R.drawable.a5, R.drawable.a6,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        mvp_main = (MyViewPager) findViewById(R.id.mvp_main);

        for(int i = 0; i < pics.length; i++) {

            //RadioButton
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            rg_main.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
            }

            //ImageView
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(pics[i]);
            imageView.setImageResource(pics[i]);
            mvp_main.addView(imageView);
        }


        //添加一个 测试页 和 单选框
        View testView = View.inflate(this, R.layout.test, null);
        mvp_main.addView(testView);
        RadioButton radioButton = new RadioButton(this);
        radioButton.setId(pics.length);
        rg_main.addView(radioButton);

        //给rg_main设置监听
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override//checkedId,被选中的RadioButton的id
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mvp_main.scrollToPage(checkedId);//checkedId就是对应的position
            }
        });

        //给mvp_main设置监听
        mvp_main.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageChange(int position) {
                rg_main.check(position);
            }
        });
    }
}
