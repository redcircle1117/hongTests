package com.example.hong.dropdown;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Hong on 2015/12/16.
 */
public class DefineView extends View implements View.OnClickListener {

    private Bitmap pic1Bitmap;
    private Bitmap pic2Bitmap;
    private Paint paint;
    private boolean isOpen;
    private int downX;


    public DefineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        paint = new Paint();
        //解析两张图片   BitmapFactory;
        pic2Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pic2);
        pic1Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pic1);
        //给滑动块设置最大x轴坐标
        maxSlide = pic2Bitmap.getWidth() - pic1Bitmap.getWidth();
        //初始化就给他设置点击监听
        setOnClickListener(this);
    }

    public DefineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override//测量视图大小
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将背景图片的宽高设置为 视图的宽高；
        setMeasuredDimension(pic2Bitmap.getWidth(), pic2Bitmap.getHeight());
    }

    int slideLeft;
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(pic2Bitmap, 0, 0, paint);
        canvas.drawBitmap(pic1Bitmap, slideLeft, 0, paint);

    }

    //相应用户操作 ontouch

    boolean clickable = true;

    private float lastX;
    private float maxSlide ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int eventX = (int) event.getRawX();
        switch(event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                clickable = true;//恢复clickable初始状态

                downX = (int) eventX;
                lastX = eventX;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = eventX - lastX;
                slideLeft += dx;

                if (slideLeft < 0) {
                    isOpen = false;
                    slideLeft = 0;
                }
                if (slideLeft > maxSlide) {
                    isOpen = true;
                    slideLeft = (int) maxSlide;
                }

                //强制重绘
                invalidate();//会重新调用 onDraw方法从而对视图进行重绘
                lastX = eventX;

                int totalDx = Math.abs(eventX - downX);
                if (totalDx > 10) {
                    clickable = false;
                }

                break;
            case MotionEvent.ACTION_UP:

                if (slideLeft > maxSlide / 2) {
                    slideLeft = (int) maxSlide;
                    invalidate();
                }else{
                    slideLeft = 0;
                    invalidate();
                }
                break;
        }
        return true;
    }



    @Override
    public void onClick(View v) {

        if (!clickable) {
            return;
        }

        if (!isOpen) {
            Log.e("TAG", "open");
            slideLeft = (int) maxSlide;
            invalidate();
            isOpen = true;
        } else {
            Log.e("TAG", "close");
            slideLeft = 0;
            invalidate();
            isOpen = false;
        }
    }
}
