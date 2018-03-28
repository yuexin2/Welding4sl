package com.xatu.yuexin.welding4sl;

import java.io.Serializable;

import android.app.Application;

import com.xatu.yuexin.util.PhoneUtil;


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
}
