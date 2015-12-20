package com.example.hong.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.util.Log.e;

/**
 * Created by Hong on 2015/12/17.
 */
public class MyRectangle extends View {

    private static final int WHAT_SHOW = 1;
    private Rectangle rec;

    private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_SHOW:
                        if (rec == null) {
                            return;
                        }

                        invalidate();

                        Paint paint = rec.getPaint();
                        int alpha = paint.getAlpha();
                        alpha -= 10;
                        paint.setAlpha(alpha);

                        if (alpha < 0) {
                            rec = null;
                            return;
                        }

                        paint.setStrokeWidth(paint.getStrokeWidth() + 5);

                        rec.setRighty(rec.getRighty() + 10);
                        rec.setRightX(rec.getRightX() + 10);

                        sendEmptyMessageDelayed(WHAT_SHOW, 20);

                    break;
                }
            }
        };

    public MyRectangle(Context context) {
        super(context);
    }

    public MyRectangle(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showRec(event);
                break;

            case MotionEvent.ACTION_MOVE:
                showRec(event);
                break;

            case MotionEvent.ACTION_UP:


                break;
        }


        return true;
    }

    private void showRec(MotionEvent event) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        if (rec == null) {
            rec = new Rectangle(event.getX(), event.getY(), event.getX() + 50, event.getY() + 80, paint);
        }else{
            rec.setLeftX(event.getX());
            rec.setLeftY(event.getY());
            rec.setRightX(event.getX() + 50);
            rec.setRighty(event.getX() + 50);
            rec.setPaint(paint);
        }
        //显示矩形
        handler.sendEmptyMessage(WHAT_SHOW);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (rec != null) {
            canvas.drawRect(rec.getLeftX(),rec.getLeftY(),rec.getRightX(),rec.getRighty(),rec.getPaint());
        }
    }
}
