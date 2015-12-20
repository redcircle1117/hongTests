package com.example.hong.adsshow;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private LinearLayout ll_main_points;
    private ViewPager vp_main_icons;
    private TextView tv_main_content;

    private List<ImageView> iconIvs = new ArrayList<>();//icons
    private List<ImageView> points = new ArrayList<>();//圆点
    private String[] texts = { "尚硅谷在线课堂", "7月就业名单全部曝光",
            "抱歉, 没座了!", "尚硅谷拔河争霸赛", "任性!平均薪水11345元",
            "尚硅谷新学员做客CCTV"};

    private int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f};//图片资源

    private  int currentPosition = 0;//当前页的下标
    private PagerAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            //切换到下一页
            vp_main_icons.setCurrentItem(vp_main_icons.getCurrentItem()+1, true);

            //再发消息
            sendEmptyMessageDelayed(2, 3000);
        }
    };


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 设置圆点的灰白
         * @param position :当前页的下标
         */
        @Override
        public void onPageSelected(int position) {
            position = position%points.size();

            //更新文本
            tv_main_content.setText(texts[position]);

            //更新圆点
            points.get(currentPosition).setEnabled(true);//上一页的item变为灰色
            points.get(position).setEnabled(false);//被选中的那个item变成白色

            currentPosition = position;
        }

        private boolean dragging = false;//拖拽的标识;
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                handler.removeCallbacksAndMessages(null);
                dragging = true;
            }else if(state == ViewPager.SCROLL_STATE_IDLE&&dragging){
                handler.sendEmptyMessageDelayed(2, 3000);
                dragging = false;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_main_content = (TextView) findViewById(R.id.tv_main_content);
        ll_main_points = (LinearLayout) findViewById(R.id.ll_main_points);
        vp_main_icons = (ViewPager) findViewById(R.id.vp_main_icons);

        //设置圆点之间的间距 == 10dp
        LinearLayout.LayoutParams params;
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);//创建的元素(小圆点)为自适应
        params.leftMargin = 5;//元素的左边距5dp
        params.leftMargin = 5;//元素的右边距5dp


        for(int i = 0; i < images.length; i++) {
            //添加图片icon
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);

            //设置点击监听
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "index = " + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });

            iconIvs.add(imageView);

            //points
            imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.point_selector);
            points.add(imageView);

            //给ll添加圆点
            ll_main_points.addView(imageView, params);
            if (i == 0) {
                imageView.setEnabled(false);//默认第一个显示白色
            }
        }

        adapter = new MyAdapter();
        vp_main_icons.setAdapter(adapter);

        //初始显示时，定位到中间位置，就可以上来向左滑动,阴招
        vp_main_icons.setCurrentItem(iconIvs.size() * 10000);

        //使用handler发送延迟消息
        handler.sendEmptyMessageDelayed(2, 3000);

        //给vp设置监听:设置小圆点
        vp_main_icons.addOnPageChangeListener(onPageChangeListener);

        //初始化文本
        tv_main_content.setText(texts[0]);
    }


    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//取最大值;图片显示多少张，根据getCount来决定的;
        }

        @Override//判断object是不是view;
        public boolean isViewFromObject(View view, Object object) {
            return view == object;


        }

        @Override//初始化item视图,并添加到容器中:viewPager,并返回item视图
        public Object instantiateItem(ViewGroup container, int position) {
            //取模;计算出无限循环的下标
            position = position%iconIvs.size();

            ImageView imageView = iconIvs.get(position);
            //把imageView 放进去
            container.addView(imageView);

            return imageView;
        }


        @Override//从container中，移除对应的item视图对象；
        public void destroyItem(ViewGroup container, int position, Object object) {
            //这个方法
           // container.removeView(iconIvs.get(position));
            //这也是方法 更简单
            container.removeView((ImageView) object);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private ImageView im_main;

    public void turnOver(View view) {
        ImageView iv_main = (ImageView) findViewById(R.id.iv_main);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_main,"");
    }
}
