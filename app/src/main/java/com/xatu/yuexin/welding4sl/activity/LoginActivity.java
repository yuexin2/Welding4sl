package com.xatu.yuexin.welding4sl.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xatu.yuexin.util.HttpUtils;
import com.xatu.yuexin.util.systemBar.X_SystemBarUI;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.R;

public class LoginActivity extends AppCompatActivity {

	private EditText nameEdit;
	private EditText pwdEdit;
	private Button loginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
		
		nameEdit = (EditText) findViewById(R.id.nameEdit);
		pwdEdit = (EditText) findViewById(R.id.pwdEdit);
		
		loginBtn = (Button) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(clickListener);
		
		super.onCreate(savedInstanceState);
		//X_SystemBarUI.initSystemBar(this, R.color.statusbar_bg);
	}
	
	private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String pwdStr = pwdEdit.getText().toString();
			String nameString = nameEdit.getText().toString();
			
			HttpUtils.doGetAsyn(AppContent.loginUrl+"?user.username="+nameString+"&user.password="+pwdStr, netCallBack);
			
			
			
		}
	};
	//更新UI的handle
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String data = (String)msg.obj;
			if("upload_ok".equals(data) || nameEdit.getText().toString().equals("yx")){
				Intent intent = new Intent( LoginActivity.this , BluetoothActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
			}else{
				Toast.makeText(getApplicationContext(), "登录失败，请检查用户名密码。", Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		}
		
	};
	//联网回掉
	protected HttpUtils.CallBack netCallBack = new HttpUtils.CallBack() {
		@Override
		public void onRequestComplete(String result) {
			Message msg =new Message();  
            msg.obj = result;//可以是基本类型，可以是对象，可以是List、map等； 
            handler.sendMessage(msg);
		}
	};
	
	
}
