package com.example.hong.dropdown;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Hong on 2015/12/16.
 */
public class MyView extends View implements View.OnClickListener {

    private Bitmap bgBitmap;
    private Bitmap slideBitmap;

    private Paint paint;


    //new 对象
    public MyView(Context context) {
        super(context);
    }

    //布局(没有引入样式)
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        //解析bitmap类型的图片 用 bitmapFactory.decodeResource
        bgBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.switch_background);
        slideBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide_button);

        //给滑动条设置最大边界 的x 坐标;
        maxSlideLeft = bgBitmap.getWidth() - slideBitmap.getWidth();

        //给自己设置点击监听
        setOnClickListener(this);
    }

    //布局(有引入样式)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //给视图设置坐标
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      //  super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将背景图片的宽高设置为 视图的宽高；
        setMeasuredDimension(bgBitmap.getWidth(), bgBitmap.getHeight());
    }



    private float slidLeft;//滑竿左上角x坐标

    @Override//绘制背景图片和滑竿图片  :坐标
    protected void onDraw(Canvas canvas) {
        //背景图片
        canvas.drawBitmap(bgBitmap, 0, 0, paint);
        //滑竿图片
        canvas.drawBitmap(slideBitmap, slidLeft, 0, paint);
    }


        //响应用户操作:ontouchEvent()
    private float lastX;
    private float maxSlideLeft;
    private boolean clickable = true;

    private float downX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//调用父类默认行为，能相应点击/长按事件
        float eventX = event.getRawX();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                clickable = true;
                lastX = eventX;
                downX = eventX;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = eventX - lastX;
                slidLeft += dx;
                //限制slidLeft:[0, bgwidth-slidwidth]
                if (slidLeft < 0) {
                    slidLeft = 0;
                }else if (slidLeft > maxSlideLeft) {
                    slidLeft = maxSlideLeft;
                }

                //强制重绘
                invalidate();
                lastX = eventX;

                //当总偏移量达到设定值时,就标识为不响应点击
                float totalDx = Math.abs(eventX - downX);
                if (totalDx > 10) {
                    clickable = false;
                }

                break;
            case MotionEvent.ACTION_UP:

                if (slidLeft < maxSlideLeft / 2) {
                    close();
                }else{
                    open();
                }
                break;
        }
        return true;
    }

    private boolean open = false;//默认关闭

    private void close(){
        open = false;
        slidLeft = 0;
        invalidate();
    }

    private void open(){
        open = true;
        slidLeft = maxSlideLeft;
        invalidate();
    }

    @Override
    public void onClick(View v) {
        if (!clickable) {
            return;
        }
        if (open) {
            close();
        } else {
            open();
        }
    }
}
