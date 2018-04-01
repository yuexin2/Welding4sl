package com.xatu.yuexin.welding4sl.vo;

public class AppInfo {
//isPay int,  appVersion varchar
	private int id;
	private String phoneId;
	private String updateTime;
	private String systemVersion;
	private String installTime;
	private String usingTimes;
	private String lastUsingTime;
	
	private String openTime;
	private String endDate;
	private String canUsingDay;
	private int interceptOutCall;
	private int interceptInCall;
	private int interceptOutMessage;
	private int interceptInMessage;
	private int interceptInstallApp;
	private int usingLogin;
	private String pwd;
	private String appVersion;
	private int isRoot;
	private int hasAD;
	
	
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public int getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(int isRoot) {
		this.isRoot = isRoot;
	}
	public int getHasAD() {
		return hasAD;
	}
	public void setHasAD(int hasAD) {
		this.hasAD = hasAD;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getUsingLogin() {
		return usingLogin;
	}
	public void setUsingLogin(int usingLogin) {
		this.usingLogin = usingLogin;
	}
	public String getLastUsingTime() {
		return lastUsingTime;
	}
	public void setLastUsingTime(String lastUsingTime) {
		this.lastUsingTime = lastUsingTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	public String getInstallTime() {
		return installTime;
	}
	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}
	public String getUsingTimes() {
		return usingTimes;
	}
	public void setUsingTimes(String usingTimes) {
		this.usingTimes = usingTimes;
	}
	public String getCanUsingDay() {
		return canUsingDay;
	}
	public void setCanUsingDay(String canUsingDay) {
		this.canUsingDay = canUsingDay;
	}
	public int getInterceptOutCall() {
		return interceptOutCall;
	}
	public void setInterceptOutCall(int interceptOutCall) {
		this.interceptOutCall = interceptOutCall;
	}
	public int getInterceptInCall() {
		return interceptInCall;
	}
	public void setInterceptInCall(int interceptInCall) {
		this.interceptInCall = interceptInCall;
	}
	public int getInterceptOutMessage() {
		return interceptOutMessage;
	}
	public void setInterceptOutMessage(int interceptOutMessage) {
		this.interceptOutMessage = interceptOutMessage;
	}
	public int getInterceptInMessage() {
		return interceptInMessage;
	}
	public void setInterceptInMessage(int interceptInMessage) {
		this.interceptInMessage = interceptInMessage;
	}
	public int getInterceptInstallApp() {
		return interceptInstallApp;
	}
	public void setInterceptInstallApp(int interceptInstallApp) {
		this.interceptInstallApp = interceptInstallApp;
	}
	
	
	
}
