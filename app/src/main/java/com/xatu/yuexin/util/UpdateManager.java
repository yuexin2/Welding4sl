package com.xatu.yuexin.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xatu.yuexin.welding4sl.R;

/*
服务器：
response.setContentType("text/plain");
response.setCharacterEncoding("GBK");
PrintWriter out;
try {
	out = response.getWriter();
	out.print("{\"verCode\":2,\"verComment\":\"测试测试\",\"downloadUrl\":\"http://192.168.1.105:8080/InterceptAssistant.apk\"}");
} catch (IOException e) {
	e.printStackTrace();
}
调用方法：
protected void checkVersion() {
	try {
		localVercode = getPackageManager().getPackageInfo(
				"com.yx.interceptassistant.activity", 0).versionCode;
		// 这里来检测版本是否需要更新
		if (NewVerCode > localVercode) {
			mUpdateManager = new UpdateManager(LogoActivity.this);
			String downloadUrl =  jsonObject.getString("downloadUrl") ;
			String verComment = jsonObject.getString("verComment");
			mUpdateManager.checkUpdateInfo(downloadUrl, verComment);
		} else {
			Toast.makeText(this, "已是最新版本无需更新！", 0).show();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}




 */
public class UpdateManager {

	private Context mContext;

	// 提示语
	private String updateMsg = "";

	// 返回的安装包url
	private String apkUrl = "";

	private Dialog noticeDialog;

	private Dialog downloadDialog;
	// TODO Auto-generated catch block
	/* 下载包安装路径 */
	private static final String savePath = "/sdcard/yxapp";
	// TODO Auto-generated catch block
	private static final String saveFileName = savePath
			+ "/test.apk";

	/* 进度条与通知ui刷新的handler和msg常量 */
	private ProgressBar mProgress;

	private static final int DOWN_UPDATE = 1;

	private static final int DOWN_OVER = 2;

	private int progress;

	private Thread downLoadThread;

	private boolean interceptFlag = false;
	
	public boolean update = true;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				downloadDialog.cancel();
				((Activity) mContext).finish();
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context) {
		this.mContext = context;
	}

	// 外部接口让主Activity调用
	public void checkUpdateInfo(String apkUrl, String updateMsg) {
		this.apkUrl = apkUrl;
		this.updateMsg = updateMsg;
		showNoticeDialog();
	}

	private void showNoticeDialog() {
		Builder builder = new Builder(mContext);
		builder.setTitle("软件版本更新");
		builder.setMessage(updateMsg);
		builder.setPositiveButton("下载", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog();
				update = true;
			}
		});
		builder.setNegativeButton("以后再说", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				update = false;
			}
		});
		noticeDialog = builder.create();
		noticeDialog.show();
	}

	private void showDownloadDialog() {
		Builder builder = new Builder(mContext);
		builder.setTitle("软件版本更新");

		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.progress);

		builder.setView(v);
		builder.setCancelable(false);
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				interceptFlag = true;
				update = false;
			}
		});
		downloadDialog = builder.create();
		downloadDialog.show();

		downloadApk();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
//				FileUtils fu = new FileUtils();
//				fu.createSDDir(AppContext.appPath);
				
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);

				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载.

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	/**
	 * 下载apk
	 * 
	 * @param url
	 */

	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);

	}

}
