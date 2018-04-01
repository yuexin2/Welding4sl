/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xatu.yuexin.welding4sl.activity;

import java.io.UnsupportedEncodingException;
import java.util.UUID;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xatu.yuexin.util.bluetooth.BluetoothChatService;
import com.xatu.yuexin.util.bluetooth.BluetoothDeviceListActivity;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.R;
import com.xatu.yuexin.welding4sl.service.BluetoothService;

/**
 * This is the main Activity that displays the current chat session.
 */
@SuppressLint("NewApi")
public class BluetoothActivity extends AppCompatActivity {
	// 类型的消息发送从bluetoothchatservice处理程序
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
    public static final String BluetoothData = "fullscreen";
	public String filename = ""; // 用来保存存储的文件名
	private String fmsg = ""; // 保存用数据缓存
	// 键名字从收到的bluetoothchatservice处理程序
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";
	// 独特的是这个应用程序

	// Intent需要 编码
	public static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;

	// 布局控件
//	private TextView mTitle;
	private EditText mInputEditText;
	private Button breakButton;
	private Button ok4bt;
	
	// 名字的连接装置
	private String mConnectedDeviceName = null;
	// 传出消息的字符串缓冲区
	private StringBuffer mOutStringBuffer;
	// 当地的蓝牙适配器
	private BluetoothAdapter mBluetoothAdapter = null;
	// 成员对象的聊天服务
	private BluetoothChatService mChatService = null;
	// 设置标识符，选择用户接受的数据格式
	private boolean dialogs;
	
    //第一次输入加入-->变量
	private int sum =1;
	private int UTF =1;

	// 名社民党记录当创建服务器套接字
	String mmsg = "";
	String mmsg2 = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//生成UUID每次第一次进入生成UUID，为了表示service与手填的一致
		AppContent.onlyUUID =  UUID.randomUUID().toString();

//		X_SystemBarUI.initSystemBar(this, R.color.statusbar_bg);
		// 设置窗口布局
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.bluetooth_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.bluetooth_custom_title);
		mInputEditText = (EditText) findViewById(R.id.editText1);
		mInputEditText.setGravity(Gravity.TOP);
		mInputEditText.setSelection(mInputEditText.getText().length(),
		mInputEditText.getText().length());
		mInputEditText.clearFocus();
		mInputEditText.setFocusable(false);
		// 设置文本的标题
//		mTitle = (TextView) findViewById(R.id.title_left_text);
//		mTitle.setText(R.string.app_name);
//		mTitle = (TextView) findViewById(R.id.title_right_text);
		// 初始化Radiobutton]
		breakButton = (Button) findViewById(R.id.button_break);
		ok4bt= (Button) findViewById(R.id.ok4bt);
		// 得到当地的蓝牙适配器
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		// 初始化CheckBox
		//点击图片跳转到公司页面
		

		 if(getWindow().getAttributes().softInputMode==WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED)

		 {

		   //隐藏软键盘

		   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		 }


		// 初始化Socket
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_LONG)
					.show();
			finish();
			return;
		}
	}

//	@Override
//	public void onStart() {
//		super.onStart();
//
//		// 如果是没有，要求它启用。
//		// setupchat()将被称为在onactivityresult
//		if (!mBluetoothAdapter.isEnabled()) {
//	//以为这样会无提示，结果无效，fu'c'k		
//    //			mBluetoothAdapter.enable();
//			Intent enableIntent = new Intent(
//					BluetoothAdapter.ACTION_REQUEST_ENABLE);
//			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//			// 否则，设置聊天会话
//		} else {
//			if (mChatService == null)
//				setupChat();
//		}
//	}

	// 连接按键响应函数
	public void onConnectButtonClicked(View v) {

		if (breakButton.getText().equals("连接")||breakButton.getText().equals("connect")) {
			Intent serverIntent = new Intent(this, BluetoothDeviceListActivity.class); // 跳转程序设置
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE); // 设置返回宏定义
			breakButton.setText(R.string.duankai);
			
		} else {
			// 关闭连接socket
			try {
				// 关闭蓝牙
				breakButton.setText(R.string.button_break);
				mChatService.stop();

			} catch (Exception e) {
			}
		}
		return;
	}

	

	public void onMyButtonClick(View view) {
		
		if (view.getId() == R.id.button_clean) {
			Toast.makeText(this, "qingkong", Toast.LENGTH_LONG).show();
			mInputEditText.setText("");
			fmsg = "";
		}
		if (view.getId() == R.id.button_break) {

			onConnectButtonClicked(breakButton);
		}
		if (view.getId() == R.id.ok4bt) {

			try {
				// 关闭蓝牙
				breakButton.setText(R.string.button_break);
				mChatService.stop();

			} catch (Exception e) {
			}
			//Toast.makeText(this, fmsg, Toast.LENGTH_LONG).show();
//			Intent intent  = new Intent(getApplicationContext(),DataInfoActivity.class);
//			intent.putExtra("fmsg", fmsg);
//			startActivity(intent);
		}
		
	}


	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Intent it=new Intent(this, BluetoothService.class);
		it.putExtra("requestCode", requestCode);
		it.putExtra("resultCode", resultCode);
		//Toast.makeText(getApplicationContext(), requestCode+"==="+resultCode, 1).show();
		if(requestCode==REQUEST_CONNECT_DEVICE && resultCode == Activity.RESULT_OK){

			it.putExtra(BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS, data.getExtras().getString(BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS));
			startService(it);

			Intent intent = new Intent(this, InputInfoActivity.class);
			startActivity(intent);


		}

		
	}


	
	
}