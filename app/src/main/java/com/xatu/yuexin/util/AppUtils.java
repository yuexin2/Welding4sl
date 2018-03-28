package com.xatu.yuexin.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

/**
 * 跟App相关的辅助类
 * 
 * @author zhy
 * 
 */
public class AppUtils
{

	private Context context;
	private static AppUtils instance;
	 
	private AppUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}
	public static AppUtils getInstance(Context context){
		if (instance==null) {
		    instance = new AppUtils(context);
		}else if(instance.context!=context){
		    instance = new AppUtils(context);
		}
		return instance;
	}
	 private AppUtils(Context context){
	        this.context = context;
	 }

	/**
	 * 获取应用程序名称
	 */
	public String getAppName()
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public String getVersionName()
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
