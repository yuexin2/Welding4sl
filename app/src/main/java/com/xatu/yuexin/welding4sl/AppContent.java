package com.xatu.yuexin.welding4sl;

import java.io.Serializable;

import android.app.Application;

import com.xatu.yuexin.util.AppUtils;
import com.xatu.yuexin.util.PhoneUtil;
import com.xatu.yuexin.welding4sl.vo.ElectrofusionData;
import com.xatu.yuexin.welding4sl.vo.HotmeltData;


public final class AppContent extends Application implements Serializable{

	private static final long serialVersionUID = 1L;
	public static String test = "http://1.82.190.45/GasProject/wp/test3.action?cmd=--calcss--";
	public static String getProjectList = "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectGdhjService/getProjectList";
	public static String getHgList = "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectInfoService/getHangongListByMCode";
	public static String getRid = "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectGdhjService/getProjectHkCode";
	public static String uploadData = "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectGdhjService/addHanjieRecord3";
	
	private static String baseUrl = "http://192.168.1.103:8080/GasProject/wp/";
	public static String loginUrl = baseUrl+"loging_update4phone_action.action";
	
	//public static Project project = new Project();
	public static String weldData;
	public static PhoneUtil phoneUtil = null;

	public static String onlyUUID = "";//设置一个UUID表示本次的数据唯一，该UUID用于标识蓝牙采集的信息与人工输入的信息一致。
	public static ElectrofusionData electrofusionData;//暂存项目中一次的录入数据。
	public static HotmeltData hotmeltData;//暂存项目中一次的录入数据。
	public static String updateInfo = "http://1.82.190.46/WeldingHotTemp.txt";
	public static String appPath = "/Welding4sl";
    public static boolean servicefinish = false;//判断服务是否完成，完成后才能进入下一步。
    public static String sendJsonUrl = "http://1.82.190.46/DataChangeServlet?json=";
    public static int canUsingDay = 5;
    public static AppUtils appUtils = null;

}
