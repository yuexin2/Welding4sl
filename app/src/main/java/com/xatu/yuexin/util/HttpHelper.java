package com.xatu.yuexin.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;


import android.os.Environment;


@SuppressWarnings("deprecation")
public class HttpHelper implements Runnable {

	public final static int GET = 1;
	public final static int POST = 2;
	public final static int POST_FILE = 3;
	public final static int PUT = 4;
	public final static int DELETE = 5;
	
	public int method = -1;
	
	private HttpClient httpclient;
	private String url;
	private Map<String,String> params;
	private String filePath = "";
	private String fileName = "";
	
	public HttpHelper(int method,String url,Map<String,String> params){
		httpclient = new DefaultHttpClient();
		// http version 
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		this.method = method;
		this.url = url;
		this.params = params;
	}
	public HttpHelper(int method,String url,Map<String,String> params,String filePath,String fileName){
		httpclient = new DefaultHttpClient();
		// http version 
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		this.method = method;
		this.url = url;
		this.params = params;
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	public synchronized String get(){
		
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");    
		httpget.setHeader("Accept-Encoding", "gzip, deflate");    
		httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpget.setHeader("Connection", "keep-alive");
		httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		if(params !=null)
		for(String key : params.keySet()){
			httpget.getParams().setParameter(key, params.get(key));
		}
		
		System.out.println("executing request " + httpget.getRequestLine());

		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpEntity resEntity = response.getEntity();

		System.out.println(response.getStatusLine());// ͨ��Ok
		String json = "";
		if (resEntity != null) {
			try {
				json = EntityUtils.toString(resEntity, "utf-8");
				resEntity.consumeContent();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		httpclient.getConnectionManager().shutdown();

		return json;
	}
	public synchronized String post(){
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");    
		httppost.setHeader("Accept-Encoding", "gzip, deflate");    
        httppost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httppost.setHeader("Connection", "keep-alive");
		httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		if(params !=null)
		for(String key : params.keySet()){
			httppost.getParams().setParameter(key, params.get(key));
		}
		System.out.println("executing request " + httppost.getRequestLine());

		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpEntity resEntity = response.getEntity();

		System.out.println(response.getStatusLine());// ͨ��Ok
		String json = "";
		if (resEntity != null) {
			try {
				json = EntityUtils.toString(resEntity, "utf-8");
				resEntity.consumeContent();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		httpclient.getConnectionManager().shutdown();

		return json;
	}
	private synchronized String put(){
		
		return "";
	}
	private synchronized String delete(){
	
		return "";
	}
	private synchronized String postFile(){
		File path= Environment.getExternalStorageDirectory(); //get SDcard path		

		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");    
		httppost.setHeader("Accept-Encoding", "gzip, deflate");    
        httppost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httppost.setHeader("Connection", "keep-alive");
		httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		
		File file = new File(path.getPath()+File.separator+this.filePath);

		MultipartEntity mpEntity = new MultipartEntity(); //�ļ�����
		ContentBody cbFile = new FileBody(file);
		mpEntity.addPart(this.fileName, cbFile); // <input type="file" name="userfile" /> ��Ӧ��

		httppost.setEntity(mpEntity);
		
		if(params !=null)
		for(String key : params.keySet()){
			httppost.getParams().setParameter(key, params.get(key));
		}
		
		System.out.println("executing request " + httppost.getRequestLine());

		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "network error";
		}
		HttpEntity resEntity = response.getEntity();

		System.out.println(response.getStatusLine());// ͨ��Ok
		String json = "";
		if (resEntity != null) {
			try {
				json = EntityUtils.toString(resEntity, "utf-8");
				resEntity.consumeContent();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		httpclient.getConnectionManager().shutdown();

		return json;
	}

	@Override
	public void run() {
		switch(this.method){
			case 1:get(); break;
			case 2:post(); break;
			case 3:postFile(); break;
			case 4:put(); break;
			case 5:delete(); break;
			default:;
		}
	}
}
