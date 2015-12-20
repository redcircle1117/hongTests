package com.example.hong.adshow;

import android.net.UrlQuerySanitizer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll_main_points;
    private TextView tv_main_contents;
    private ViewPager vp_main_icons;

    private List<ImageView> iconIvs = new ArrayList<>();
    private List<ImageView> pointIvs = new ArrayList<>();

    private int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,};
    private String[] contents = { "尚硅谷在线课堂", "7月就业名单全部曝光", "抱歉, 没座了!",

            "尚硅谷拔河争霸赛", "任性!平均薪水11345元", "尚硅谷新学员做客CCTV"};
    private int currentPosition;
    private MyAdapter adapter;
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
            //当page改变的时候  小圆圈和内容进行更新,
            position = position%pointIvs.size();//得到真实的position
            pointIvs.get(currentPosition).setEnabled(false);//变为灰色
            pointIvs.get(position).setEnabled(true);//变为白色
            currentPosition = position;

            tv_main_contents.setText(contents[position]);//更新文本内容
        }

        /**
         *
         * @param state The new scroll state.  当前状态
         * @see ViewPager#SCROLL_STATE_IDLE  空闲(不动)
         * @see ViewPager#SCROLL_STATE_DRAGGING 拖动
         * @see ViewPager#SCROLL_STATE_SETTLING 松手后自动滑动恢复
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            boolean dragging = false;
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                handler.removeCallbacksAndMessages(null);
                dragging = true;
            }else if (state == ViewPager.SCROLL_STATE_IDLE && dragging) {
                handler.sendEmptyMessageDelayed(2, 3000);
                dragging = false;
            }
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //切换到下一页
            vp_main_icons.setCurrentItem(vp_main_icons.getCurrentItem() + 1, true);//平滑移动到下一页

            //继续放消息
            sendEmptyMessageDelayed(2, 3000);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_main_points = (LinearLayout) findViewById(R.id.ll_main_points);
        tv_main_contents = (TextView) findViewById(R.id.tv_main_contents);
        vp_main_icons = (ViewPager) findViewById(R.id.vp_main_icons);

        //初始化文字:默认显示第一张对应的contents
        tv_main_contents.setText(contents[0]);

        //设置布局子元素的左右间距
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 5;
        params.rightMargin = 5;

        //给viewpage加入图片
        for(int i = 0; i < images.length; i++) {

            //给viewpaper添加图片
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);

            //设置点击监听
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "index"+v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });

            vp_main_icons.addView(imageView);

            iconIvs.add(imageView);

            //通过xml资源文件设置points


                //给ll元素添加圆点
            imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.points_main);
            pointIvs.add(imageView);
            ll_main_points.addView(imageView, params);

            if (i == 0) {
                imageView.setEnabled(true);//默认第一个为白色
            }
        }

        //将数据装入vp_main_icons中
        adapter = new MyAdapter();
        vp_main_icons.setAdapter(adapter);

        //把pager设置到中间位置
        vp_main_icons.setCurrentItem(iconIvs.size()*10000);

        //给viewpage设置监听
        vp_main_icons.addOnPageChangeListener(onPageChangeListener);

        //发送延迟消息  完成自动切换page功能
        handler.sendEmptyMessageDelayed(2, 3000);



    }

    class MyAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override//初始化item视图并添加到container, 并返回item视图
        public Object instantiateItem(ViewGroup container, int position) {

            //因为position是无限大的，所以对position取模
            position = position%iconIvs.size();

            ImageView imageView = iconIvs.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override//从container移除对应的item视图对象
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((ImageView)object);
        }
    }

}
