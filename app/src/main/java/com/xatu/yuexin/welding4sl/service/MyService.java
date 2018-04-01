package com.xatu.yuexin.welding4sl.service;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xatu.yuexin.util.AppUtils;
import com.xatu.yuexin.util.FileUtils;
import com.xatu.yuexin.util.NetUtils;
import com.xatu.yuexin.util.PhoneUtil;
import com.xatu.yuexin.util.db.DBHelper;
import com.xatu.yuexin.util.db.DataBaseManager;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.vo.AppInfo;

@SuppressLint("HandlerLeak")
public class MyService extends Service {

	private FileUtils fu= new FileUtils();
	private DataBaseManager dbManager;
	private DataBaseManager db;
	private String sendJson;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

//		//生成广播处理
//		DeleteInMSMReciever deleteInMSMReciever = new DeleteInMSMReciever();
//		//实例化过滤器并设置要过滤的广播
//		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//		intentFilter.setPriority(2147483647);
//		//注册广播
//		registerReceiver(deleteInMSMReciever, intentFilter);


		//flags = START_STICKY;//防止被kill掉
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onCreate() {

		AppContent.phoneUtil = PhoneUtil.getInstance(getApplicationContext());
		AppContent.appUtils = AppUtils.getInstance(getApplicationContext());

//		Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();

		super.onCreate();
	}



	@Override
	public void onDestroy() {
		Intent sevice = new Intent(this, MyService.class);
		this.startService(sevice);
		Log.d("StartUpMyService", "onDestroy");
		dbManager.close();
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
//		Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
//		fu.writeTxtFile("onCreate\n", "", "InterceptAssistant.log", false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 初始化DBManager(创建创建db)
//		dbManager = DataBaseManager.getInstance(this);
		db = DataBaseManager.getInstance(this);

		//先将时间写入数据库，并获取数据库设置
		List<Map<String, Object>> list = null;
		try {
			db.updateDataBySql("update "+ DBHelper.TB_NAME_INFO+" set openTime = ? , appVersion = ?", new String[]{sdf.format(new Date()),AppContent.appUtils.getVersionName()});
			list = db.queryData2Map("select * from "+ DBHelper.TB_NAME_INFO+" ", null, new AppInfo());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		//判断是否有网
		boolean haveNet = NetUtils.isConnected(getApplicationContext());
		if(haveNet){//有网时同步数据库
			/**
			 *  1.将所有数据发到后台
			 *  2.后台解析是否有这台手机，若没有保存，若有将后台的时间发下来更新
			 */
			AppInfo appInfo = new AppInfo();
			if(list.size()>0){
				Map map = list.get(0);

				//将list转换为json用于发送
				sendJson = new Gson().toJson(map);
				sendJson = AppContent.sendJsonUrl+sendJson.replaceAll(" ", "%20");
				//{"lastUsingTime":"2016-12-13 19:38:00","interceptInstallApp":"0","installTime":"2016-12-13 19:27:19","usingLogin":"0","pwd":" ","interceptOutCall":"0","openTime":"2016-12-13 19:40:03","interceptOutMessage":"0","interceptInCall":"0","systemVersion":"6.0","canUsingDay":"7","id":"1","phoneId":"862044037896375","usingTimes":"9","interceptInMessage":"0","endDate":"2016-12-20 19:27:19"}
				new Thread() {
					public void run() {
//						Toast.makeText(getApplicationContext(), "http://192.168.1.105:8080", Toast.LENGTH_LONG).show();
						ByteArrayOutputStream outStream = new ByteArrayOutputStream();
						byte[] data = new byte[1024];
						int len = 0;
						URL url;
						try {
							url = new URL(sendJson);
							HttpURLConnection conn = (HttpURLConnection) url
									.openConnection();
							InputStream inStream = conn.getInputStream();

							while ((len = inStream.read(data)) != -1) {
								outStream.write(data, 0, len);
							}
							inStream.close();
							String result = new String(outStream.toByteArray(), "GBK");// 通过out.Stream.toByteArray获取到写的数据
//							Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

							//解析并同步
							JSONObject jsonObject = new JSONObject(result);
							System.out.println("jsonObject----->"+jsonObject);
							Iterator<?> it = jsonObject.keys();
							List<String> values = new ArrayList<String>();
							List keys = new ArrayList();
							String value="";
							String key = "";
							String set = "";
							while(it.hasNext()){//遍历JSONObject
								key = (String) it.next().toString();
								value = jsonObject.getString(key);
								System.out.println("key----->"+key+"------"+value);
								if(!value.equals("-1") && !value.trim().equals("")){
									values.add(value);
									set = set + key +"=?,";
								}
							}
							set = set.substring(0,set.length()-1);
							String sql = "update "+DBHelper.TB_NAME_INFO+" set "+set;
							db.updateDataBySql(sql,values.toArray(new String[0]));

						} catch (Exception e) {
							e.printStackTrace();
						}
					};
				}.start();

			}
		}

		try {
			//与程序变量同步数据库设置

			Map<String, Object> map= list.get(0);
			String installTime = (String) map.get("installTime");
			String openTime = (String) map.get("openTime");
			String endTime = (String) map.get("endDate");
			String lastUsingTime = (String) map.get("lastUsingTime");

			System.out.println("usingLogin--->"+map.get("usingLogin"));



			Date now = sdf.parse(openTime);
			Date installDate = sdf.parse(installTime);
			Date lastUsingDate = sdf.parse(lastUsingTime);
			Date endDate = sdf.parse(endTime);
			//Date now = new Date();

			if(now.getTime()>=lastUsingDate.getTime()-60*60*1000 && now.getTime()<=endDate.getTime()){
				//在规定时间内,允许手机时间与同步时间有1小时的差距
				int usingTimes = Integer.parseInt((String) map.get("usingTimes"))+1;
				String id = (String) map.get("id");
				db.updateDataBySql("update "+DBHelper.TB_NAME_INFO+" set lastUsingTime = ?,usingTimes = ? where id = ?", new String[]{sdf.format(now),usingTimes+"",id});
//				AppContext.interceptOutCall  = (Integer.parseInt((String) map.get("interceptOutCall")))>0?true:false;
//				AppContext.interceptInCall  = (Integer.parseInt((String) map.get("interceptInCall")))>0?true:false;
//				AppContext.interceptOutMessage  = (Integer.parseInt((String) map.get("interceptOutMessage")))>0?true:false;
//				AppContext.interceptInMessage  = (Integer.parseInt((String) map.get("interceptInMessage")))>0?true:false;
//				AppContext.interceptInstallApp  = (Integer.parseInt((String) map.get("interceptInstallApp")))>0?true:false;
//				AppContext.usingLogin  = (Integer.parseInt((String) map.get("usingLogin")))>0?true:false;
//				AppContext.hasAD  = (Integer.parseInt((String) map.get("hasAD")))>0?true:false;
				if(endDate.getTime()-now.getTime() <= (AppContent.canUsingDay)*24*60*60*1000){
					//小于一个时间提示
					//Toast.makeText(getApplicationContext(), "剩余使用时间："+(endDate.getTime()-now.getTime()-24*60*60*1000)/(24*60*60*1000)+"天", Toast.LENGTH_LONG).show();
				}
			}else{
				//超出服务时间，与管理员联系
				//没有在规定的时间内的软件不做任何东西
				Toast.makeText(getApplicationContext(), "超出服务时间，功能无法使用，请与管理员联系", Toast.LENGTH_LONG).show();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		AppContent.servicefinish = true;
		Log.d("StartUpMyService", "onCreate");
//		fu.writeTxtFile("onStart\n", "", "InterceptAssistant.log", false);
		// 再次动态注册广播
//		IntentFilter localIntentFilter = new IntentFilter("android.intent.action.USER_PRESENT");
//		localIntentFilter.setPriority(Integer.MAX_VALUE);// 整形最大值
//		StartupReceiver startupReceiver = new StartupReceiver();
//		registerReceiver(startupReceiver, localIntentFilter);
//
//
//
//		OutgoingCallReceiver outgoingCallReceiver = new OutgoingCallReceiver();
//		//实例化过滤器并设置要过滤的广播
//		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
//		intentFilter.setPriority(2147483647);
//		//注册广播
//		registerReceiver(outgoingCallReceiver, intentFilter);


		Log.d("StartUpMyService", "onStart");
	}

	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}


}
