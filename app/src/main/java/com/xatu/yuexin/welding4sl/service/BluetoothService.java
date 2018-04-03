package com.xatu.yuexin.welding4sl.service;


import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.xatu.yuexin.util.bluetooth.BluetoothChatService;
import com.xatu.yuexin.util.bluetooth.BluetoothDeviceListActivity;
import com.xatu.yuexin.welding4sl.R;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BluetoothService extends Service {

	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	
	//// 键名字从收到的bluetoothchatservice处理程序
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";
	// Intent需要 编码
	public static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;
	
	private String fmsg = ""; // 保存用数据缓存
	
	// 当地的蓝牙适配器
	private BluetoothAdapter mBluetoothAdapter = null;
	// 成员对象的聊天服务
	private BluetoothChatService mChatService = null;
	// 名字的连接装置
	private String mConnectedDeviceName = null;
		
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//Toast.makeText(getApplicationContext(), "onBind", Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		// 得到当地的蓝牙适配器
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mChatService == null)
			setupChat();
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		// 蓝牙聊天服务站
		if (mChatService != null)
			mChatService.stop();
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//启用前台服务，主要是startForeground()  
        Notification notification = new Notification(R.drawable.logo, "用电脑时间过长了！白痴！"
                , System.currentTimeMillis());

//        notification.setLatestEventInfo(this, "塑龙本地数据传输终端", "后台服务开启。。。", null);
        //https://stackoverflow.com/questions/32345768/cannot-resolve-method-setlatesteventinfo
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            try {
                //采用反射调用notification.setLatestEventInfo(context, appName, contentText, //contentIntent);
                Method deprecatedMethod = notification.getClass().
                        getMethod("setLatestEventInfo", Context.class, CharSequence.class,
                                CharSequence.class, PendingIntent.class);
                deprecatedMethod.invoke(notification, this, "塑龙本地数据传输终端", "后台服务开启。。。", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }else{
//            //采用Notification.Builder来构建
//        }
        //设置通知默认效果  
        notification.flags = Notification.FLAG_SHOW_LIGHTS;  
        startForeground(1, notification);  
        //启用前台服务，主要是startForeground()  
        
        
		if (mChatService != null) {
			// 只有国家是state_none，我们知道，我们还没有开始
			if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
				// 启动蓝牙聊天服务
				mChatService.start();
			}
		}else{
			
		}

		int requestCode = intent.getExtras().getInt("requestCode");
		int resultCode = intent.getExtras().getInt("resultCode");
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE:
			// 当devicelistactivity返回连接装置
			if (resultCode == Activity.RESULT_OK) {
				// 获得设备地址
				String address = intent.getExtras().getString(
						BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS);
				// 把蓝牙设备对象
				BluetoothDevice device = mBluetoothAdapter
						.getRemoteDevice(address);
				//Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
				// 试图连接到装置
				mChatService.connect(device);
			}
			break;
		case REQUEST_ENABLE_BT:
			// 当请求启用蓝牙返回
			if (resultCode == Activity.RESULT_OK) {
				// 蓝牙已启用，所以建立一个聊天会话
				setupChat();
			} else {
				// 用户未启用蓝牙或发生错误
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();
//				finish();
			}
		}
		//flags = START_STICKY;//防止被kill掉
		return super.onStartCommand(intent, flags, startId);
	}

	private void setupChat() {
		//Toast.makeText(getApplicationContext(), "setupChat", Toast.LENGTH_SHORT).show();

		// 初始化bluetoothchatservice执行蓝牙连接
		mChatService = new BluetoothChatService(this, mHandler);

		// 缓冲区初始化传出消息
		//mOutStringBuffer = new StringBuffer("");
	}
	
	// 处理程序，获取信息的bluetoothchatservice回来
		private final Handler mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MESSAGE_STATE_CHANGE:
					switch (msg.arg1) {
					case BluetoothChatService.STATE_CONNECTED:
//						mInputEditText.setText("");
						break;
					case BluetoothChatService.STATE_CONNECTING:
//						mTitle.setText(R.string.title_connecting);
						break;
					case BluetoothChatService.STATE_LISTEN:
					case BluetoothChatService.STATE_NONE:
//						mTitle.setText(R.string.title_not_connected);
						break;
					}
					break;
				case MESSAGE_WRITE:
					byte[] writeBuf = (byte[]) msg.obj;
					// 构建一个字符串缓冲区
					String writeMessage = new String(writeBuf);
					//mmsg += writeMessage;

//						mInputEditText.getText().append("\n<--"+writeMessage+"\n");
//	                    fmsg+="\n<--"+writeMessage+"\n";

					break;
				case MESSAGE_READ:
					//TODO
					//byte[] readBuf = (byte[]) msg.obj;
					// 构建一个字符串从有效字节的缓冲区
//						mInputEditText.getText().append("---->");
						fmsg+="\n";
					//String readMessage = new String(readBuf, 0, msg.arg1);
					Bundle data = msg.getData();  
	                String readMessage = data.getString("BTdata"); 
					
//	                    mInputEditText.getText().append(readMessage);
	                    fmsg+=readMessage;
					//获取到数据，进行处理。
					//String bluetoothStr = msg.getData().getString(TOAST);

	                // Toast.makeText(getApplicationContext(), fmsg, Toast.LENGTH_SHORT).show();
					Map map = analysisBluetoothStr(fmsg);
					if(map.size() > 1){
						//说明数据传输完成
						fmsg = "";
						//将数据放到数据库中
						WeldingData2DB wdf2db = new WeldingData2DB(getApplication());
						wdf2db.insertHot4Bluetooth(map);
					}

					break;
				case MESSAGE_DEVICE_NAME:
					// 保存该连接装置的名字
					mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
					Toast.makeText(getApplicationContext(),
							"已连接 " + mConnectedDeviceName, Toast.LENGTH_SHORT)
							.show();
					break;
				case MESSAGE_TOAST:
					Toast.makeText(getApplicationContext(),
							msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
							.show();
					break;
				}
			}
		};



	private Map<String,String> analysisBluetoothStr(String str){
		Map<String,String> map = new HashMap<String,String>();


		String tempStr = str.replaceAll("\n","");
		if(tempStr.contains("INFORMATIONS")){//表示最后一个数据过来了
			String[] strs = str.split("\r\n");
			String dataType="";
			for (int i = 0;i<strs.length;i++){
				strs[i] = strs[i].replaceAll("\n","");
				//分析数据
				if(strs[i].contains(":")){
					String key =(strs[i].subSequence(0, strs[i].indexOf(":"))).toString().replace("-","").trim();
					map.put(key,strs[i].subSequence( strs[i].indexOf(":")+1,strs[i].length()).toString().trim());
				}

			}
		}
		return map;
	}

}
