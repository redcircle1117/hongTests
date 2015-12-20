package com.example.hong.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Hong on 2015/12/18.
 */
public class MyViewPager extends ViewGroup {

    private Scroller scroller;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //让每个子view都进行测量
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override//给子view进行布局
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);

            //给子view重新布局
            int height = getHeight();
            int width = getWidth();
            int left = i*width;
            int right = (1+i)*width;
            childView.layout(left, 0, right, height);

        }
    }

    /**
     * 拖动图片翻页
     * 当从down到up的位移超过一定距离后，变进行翻页处理
     * @param event
     * @return
     */
    private int lastX;
    private int position;//当前页的下标
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int eventX = (int) event.getRawX();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = eventX;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = eventX - lastX;
                int toScrollX = getScrollX() - dx;
                scrollTo(toScrollX, 0);
                lastX = eventX;
                break;

            case MotionEvent.ACTION_UP:
                //得到偏移量(down --> up)
                int upScrollX = getScrollX() - position*getWidth();
                if (upScrollX > getWidth() / 4) {//下一页
                    scrollToPage(position + 1);
                }else if (upScrollX < -getWidth() / 4) {
                    scrollToPage(position - 1);
                }else {
                    scrollToPage(position);
                }
                break;
        }
        return true;
    }

    /**
     * 滚动到指定页
     * @param position
     */
    public void scrollToPage(int position) {
        //限制position
        if (position < 0) {
            position = 0;
        }else if (position > getChildCount() - 1) {
            position = getChildCount()-1;
        }

        //更新下标
        if (this.position != position) {
            this.position = position;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageChange(position);
            }
        }
        //根据position计算出toScrollX
        int toScrollX = position * getWidth();
        //平滑滚动
        scroller.startScroll(getScrollX(),0,toScrollX-getScrollX(),0);
        invalidate();
    }



    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    /**
     * 自定义设置监听
     */
    private OnPageChangeListener onPageChangeListener;

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    /**
     * 自定义一个接口
     * 这是要回调的方法;
     */
    public interface OnPageChangeListener{
        void onPageChange(int position);

    }


    private float downX,downY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        /**c
         * 如果水平方向滑动的距离大于数值方向滑动，就是左右滑动，拦截事件
         * 否则事件继续传递
         */

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //第一次按下坐标
                downX = ev.getX();
                downY = ev.getY();
                lastX = (int) ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:

                //来到新的坐标
                float moveX = ev.getX();
                float moveY = ev.getY();

                //计算距离
                int distanceX = (int) Math.abs(moveX - downX);
                int distanceY = (int) Math.abs(moveY - downY);
                //防止手指移动时候的偏差, 并防止抖动
                if (distanceX > distanceY && distanceX > 10) {
                    intercept = true;
                }
                break;
        }
        return intercept;
    }
}
