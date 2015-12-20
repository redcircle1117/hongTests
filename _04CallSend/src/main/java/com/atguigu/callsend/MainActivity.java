package com.atguigu.callsend;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnLongClickListener {
	
	private Button btn_main_call;
	private Button btn_main_send;
	private EditText et_main_sms;
	private EditText et_main_num;
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v == btn_main_call){
				String number = et_main_num.getText().toString();
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:"+number));
				startActivity(intent);		
				
			}else if(v == btn_main_send){
				Intent intent = new Intent(Intent.ACTION_SENDTO);
				String number = et_main_num.getText().toString();
				String sms = et_main_sms.getText().toString();
				
				intent.setData(Uri.parse("smsto:"+number));
				intent.putExtra("sms_body", sms);
				startActivity(intent);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_main_call = (Button)findViewById(R.id.btn_main_call);
		btn_main_send = (Button)findViewById(R.id.btn_main_send);
		
		et_main_num = (EditText)findViewById(R.id.et_main_num);
		et_main_sms = (EditText)findViewById(R.id.et_main_sms);
		
		btn_main_call.setOnClickListener(listener);
		btn_main_send.setOnClickListener(listener);
		
		btn_main_call.setOnLongClickListener(this);
		btn_main_send.setOnLongClickListener(this);
		
	}

	@Override
	public boolean onLongClick(View v) {
		if(v == btn_main_call){
			String number = et_main_num.getText().toString();
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+number));
			startActivity(intent);
		}else if(v == btn_main_send){
			String number = et_main_num.getText().toString();
			String sms = et_main_sms.getText().toString();
			
			SmsManager manager = SmsManager.getDefault();
			manager.sendTextMessage(number, null, sms, null, null);
			Toast.makeText(this, "发送成功", 0).show();
			
		}
		return true;
	}
}
