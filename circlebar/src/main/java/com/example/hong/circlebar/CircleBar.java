package com.example.hong.circlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Hong on 2015/12/17.
 */
public class CircleBar extends View {

    private float circleWidth = 20;
    private int bgColor = Color.GRAY;
    private int pgColor = Color.GREEN;
    private int progress;
    private int maxProgress = 100;
    private int textColor = Color.GRAY;
    private int textSize = 25;
    private Paint paint;

    @Override
    public String toString() {
        return "CircleBar{" +
                "circleWidth=" + circleWidth +
                ", bgColor=" + bgColor +
                ", pgColor=" + pgColor +
                ", progress=" + progress +
                ", maxProgress=" + maxProgress +
                ", textColor=" + textColor +
                ", textSize=" + textSize +
                ", paint=" + paint +
                '}';
    }

    public CircleBar(Context context) {
        super(context);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > maxProgress) {
            progress = maxProgress;
        }
        this.progress = progress;
        postInvalidate();
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public CircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);

        //得到所有自定义属性的数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CircleBar_style);

        //得到数组中对应的属性，并在此方法中设定默认值
        typedArray.getInteger(R.styleable.CircleBar_style_progress, 50);
        typedArray.getColor(R.styleable.CircleBar_style_roundColor, Color.BLUE);

        //释放资源数据
        typedArray.recycle();

    }

    private int viewWidth;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆圈
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(bgColor);
        paint.setStrokeWidth(circleWidth);

        float cx = viewWidth / 2;
        float cy = viewWidth / 2;
        float radius = viewWidth/2 - circleWidth/2;
        canvas.drawCircle(cx, cy, radius, paint);

        //绘制圆弧
        paint.setStrokeWidth(25);
        paint.setColor(pgColor);

        RectF oval = new RectF(circleWidth/2, circleWidth/2, viewWidth - circleWidth/2, viewWidth - circleWidth/2);
        canvas.drawArc(oval, 0, progress * 360 / maxProgress, false, paint);

        //绘制文字
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);

        Rect boungds = new Rect();
        String text = progress*100/maxProgress + "%";
        paint.getTextBounds(text, 0, text.length(), boungds);

        int width = boungds.width();
        int height = boungds.height();

        float x = viewWidth/2 - width/2;
        float y = viewWidth/2 - height/2;

        canvas.drawText(text, x, y, paint);
    }
}
