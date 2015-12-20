package com.example.hong.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Hong on 2015/12/17.
 */
public class WaveView extends View {


    private static final int WHAT_SHOW = 1;
    private Circle circle;
    private int[] colors = {Color.BLUE,Color.RED,Color.GREEN,Color.GRAY,
    Color.YELLOW};

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_SHOW:

                    if (circle == null) {
                        return;
                    }
                    //否则就强制重绘,
                    invalidate();

                    circle.setRadius(circle.getRadius() + 5);
                    //更新paint:
                    Paint paint = circle.getPaint();
                    int alpha = paint.getAlpha();
                    if (alpha <= 0) {
                        circle = null;
                        return;
                    }
                    alpha -= 5;
                    paint.setAlpha(alpha);
                    //圆环的宽度
                    paint.setStrokeWidth(paint.getStrokeWidth() + 2);


                    //发送延迟消息，重复上面的动作
                    sendEmptyMessageDelayed(WHAT_SHOW, 50);
                    break;
            }
        }
    };

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showCircle(event);//之所以传 event 是因为 圆的中心和event有关;
                break;
            case MotionEvent.ACTION_MOVE:
                showCircle(event);
                break;
        }

        return true;
    }

    private void showCircle(MotionEvent event) {
        //如果有圆的对象
        if (circle != null) {
            Paint paint = new Paint();//paint用来设置圆的形状，搞成圆环和各种颜色
            paint.setColor(colors[new Random().nextInt(colors.length)]);//设置随机颜色
            paint.setStyle(Paint.Style.STROKE);//空心
            paint.setAntiAlias(true);//消除锯齿
            circle.setX(event.getX());
            circle.setY(event.getY());
            circle.setPaint(paint);
            circle.setRadius(20);
            // 在触摸点设置圆
        } else {
            //否则创建圆的对象
            Paint paint = new Paint();//paint用来设置圆的形状，搞成圆环和各种颜色
            paint.setColor(colors[new Random().nextInt(colors.length)]);//设置随机颜色
            paint.setStyle(Paint.Style.STROKE);//空心
            paint.setAntiAlias(true);//消除锯齿
            circle = new Circle(event.getX(), event.getY(), 20, paint);//在触摸点设置圆
        }
        handler.sendEmptyMessageDelayed(WHAT_SHOW, 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (circle != null) {
            canvas.drawCircle(circle.getX(),circle.getY(),circle.getRadius(),circle.getPaint());
        }
    }
}
