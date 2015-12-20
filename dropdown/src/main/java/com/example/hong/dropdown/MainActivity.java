package com.example.hong.dropdown;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private ImageView iv_main_drop;
    private EditText et_main;
    private PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main = (EditText) findViewById(R.id.et_main);
        iv_main_drop = (ImageView) findViewById(R.id.iv_main_drop);
    }

    public void showList(View v) {
        initList();
        showPW();
    }

    private BaseAdapter adapter;
    private List<String> data = new ArrayList<String>();
    private ListView listView;//

    //给listview中添加数据
    private void initList() {
        if (listView != null) {
            return;
        }
        listView = new ListView(this);
        listView.setBackgroundResource(R.drawable.listview_background);
        listView.setOnItemClickListener(this);


        for (int i = 0; i < 20; i++) {
            data.add("aaaaaaaaaa" + i);
        }

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(MainActivity.this,
                            R.layout.item_drop, null);
                }

                String account = data.get(position);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_item);
                textView.setText(account);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_delete);
                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        data.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });


                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }

    private void showPW() {

        if (pw == null) {
            pw = new PopupWindow(this);
            //这里需要动画
            pw.setContentView(listView);
            pw.setWidth(et_main.getWidth());
            pw.setHeight(DensityUtils.dip2px(this, 200));

            //设置pw可以获得焦点，这样才能相应Item点击事件和点击外部自动dismiss
            pw.setFocusable(true);
        }

        pw.showAsDropDown(et_main);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        et_main.setText(data.get(position));
        pw.dismiss();
    }
}
