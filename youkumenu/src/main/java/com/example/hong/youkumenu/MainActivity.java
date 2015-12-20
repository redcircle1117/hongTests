package com.example.hong.youkumenu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private RelativeLayout rl_main_level3;
    private RelativeLayout rl_main_level2;
    private RelativeLayout rl_main_level1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_main_level1 = (RelativeLayout) findViewById(R.id.rl_main_level1);
        rl_main_level2 = (RelativeLayout) findViewById(R.id.rl_main_level2);
        rl_main_level3 = (RelativeLayout) findViewById(R.id.rl_main_level3);

        //保存当前状态值
        rl_main_level1.setTag(true);
        rl_main_level2.setTag(true);
        rl_main_level3.setTag(true);

    }

    /**
     * 判断布局是否打开的
     * @param layout
     * @return
     */
    private boolean isOpen(RelativeLayout layout){
        boolean open = (boolean) layout.getTag();
        return open;


    }

    public void clickMenu(View v) {
        swithcMenu(rl_main_level3, 0);

    }
    public void clickHome(View v) {
        swithcMenu(rl_main_level2, 0);
        if (isOpen(rl_main_level3)) {
            swithcMenu(rl_main_level3, 300);
        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU){
            swithcMenu(rl_main_level1, 0);
            if (isOpen(rl_main_level1) || isOpen(rl_main_level2)) {
                swithcMenu(rl_main_level2, 300);
            }
            if (isOpen(rl_main_level3)) {
                swithcMenu(rl_main_level3, 600);
            }
            return true;

        }
            return super.onKeyUp(keyCode, event);

    }

    public void swithcMenu(RelativeLayout layout, long delayTime) {

        boolean isOpen = (boolean) layout.getTag();//稍后改
        float fromDegrees = 0;
        float toDegrees = 0;
        if (isOpen) {
            fromDegrees = 0;
            toDegrees = 180;
        }else{
            fromDegrees = 180;
            toDegrees = 360;
        }

        layout.setPivotX(layout.getWidth() / 2);
        layout.setPivotY(layout.getHeight());

        layout.setTag(!isOpen);

        ObjectAnimator animator = ObjectAnimator.ofFloat(layout, "rotaion", fromDegrees, toDegrees);
        animator.setDuration(500);
        animator.setStartDelay(delayTime);
        animator.start();


    }



    public void swithcMenu1(RelativeLayout layout, long delayTime){
        float fromDegrees = 0;
        float toDegrees = 0;

        boolean isOpen = (boolean) layout.getTag();
        if(isOpen){
            //要关闭菜单
            fromDegrees = 0;
            toDegrees = 180;
        }else{
            //要打开菜单
            fromDegrees = 180;
            toDegrees = 360;
        }
        //保存新的状态值
        layout.setTag(!isOpen);

        //创建新的状态
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);

        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(delayTime);

        layout.startAnimation(rotateAnimation);

        //设置菜单布局子view的可点击性
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setClickable(!isOpen);
        }
    }
}
