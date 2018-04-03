package com.xatu.yuexin.welding4sl.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.xatu.yuexin.util.FileUtils;
import com.xatu.yuexin.util.NetUtils;
import com.xatu.yuexin.util.PhoneUtil;
import com.xatu.yuexin.util.UpdateManager;
import com.xatu.yuexin.util.gps.GpsService;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.R;
import com.xatu.yuexin.welding4sl.service.MyService;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HelloActivity extends AppCompatActivity {
    public static int DELAYTIME = 3; // 延迟时间
    int NewVerCode;
    int localVercode;
    private JSONObject jsonObject = null;
    private UpdateManager mUpdateManager = null;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    checkVersion();
                    break;
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        /**全屏设置，隐藏窗口所有装饰**/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hello);
//			setContentView(R.layout.about2);
        //创建必要的文件夹
//        FileUtils fu = new FileUtils();
//        fu.createSDDir(AppContent.appPath);
        super.onCreate(savedInstanceState);
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)&&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            //如果没有授权，则请求授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            startService();
        }

    }
    /**
     * 处理权限请求结果
     *
     * @param requestCode
     *          请求权限时传入的请求码，用于区别是哪一次请求的
     *
     * @param permissions
     *          所请求的所有权限的数组
     *
     * @param grantResults
     *          权限授予结果，和 permissions 数组参数中的权限一一对应，元素值为两种情况，如下:
     *          授予: PackageManager.PERMISSION_GRANTED
     *          拒绝: PackageManager.PERMISSION_DENIED
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for(int i=0;i<grantResults.length;i++){
            if (PackageManager.PERMISSION_DENIED == grantResults[i]){
                Toast.makeText(this,"拒绝权限，无法开启",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        startService();
    }
    protected void startService(){

        mUpdateManager = new UpdateManager(HelloActivity.this);
//        if(false == NetUtils.isConnected(getApplicationContext())){
//            AppContent.hasAD = false;
//        }
//        if(AppContent.hasAD ){
//        }

        new AsyncTask<Void, Void, Integer>(){//异步任务，进行软件更新等

            @Override
            protected Integer doInBackground(Void... params) {

                //启动后台服务
                Intent serviceIntent = new Intent(HelloActivity.this, MyService.class);
                //serviceIntent.setAction("com.lanshui.mobile.MYGETPUSH_ORDER_ACTION");
                serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(serviceIntent);

                return 1;
            }

            @Override
            protected void onPostExecute(Integer result) {

            };
        }.execute(new Void[]{});
        //创建必要的文件夹
//			FileUtils fu = new FileUtils();
//			fu.createSDDir(AppContent.picPath);
//			fu.createSDDir(AppContent.AppBasePath+"/checkok");


//			mVersionNameText = (TextView) findViewById(R.id.version_name);
//			mVersionNameText.setText("0.0.1");
        if(NetUtils.isConnected(getApplicationContext())){
            getRemoteVersion();//检测更新
        }else{
            mUpdateManager.update = false;
        }
        //等待转向

        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(AppContent.servicefinish && mUpdateManager.update == false){

//                    Intent it = null;
//                    if(AppContext.usingLogin){
//                        it = new Intent(LogoActivity.this, LoginActivity.class);
//                    }else{
//                        it = new Intent(LogoActivity.this, FunctionActivity.class);
//                    }
                    Intent it = new Intent(HelloActivity.this, CheckFaceActivity.class); //你要转向的Activity
                    startActivity(it); //执行
                    HelloActivity.this.finish();
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 1000 * DELAYTIME , 1000);
        //等待转向
    }

    protected void checkVersion() {
        try {
            localVercode = getPackageManager().getPackageInfo(
                    "com.xatu.yuexin.welding4sl", 0).versionCode;
            // 这里来检测版本是否需要更新
            if (NewVerCode > localVercode) {
                String downloadUrl =  jsonObject.getString("downloadUrl") ;
//					"http://www.chongchi-tech.com/ccsoft/uploads/app/SmartHome1.0.16_201410091748.apk";

                String verComment = jsonObject.getString("verComment");

                mUpdateManager.checkUpdateInfo(downloadUrl, verComment);
            } else {
                //Toast.makeText(this, "已是最新版本无需更新！", Toast.LENGTH_SHORT).show();
                mUpdateManager.update = false;
            }
        } catch (Exception e) {
            mUpdateManager.update = false;
            e.printStackTrace();
        }
    }

    public void getRemoteVersion() {
        new Thread() {
            public void run() {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len = 0;
                URL url;
                try {
                    url = new URL(
                            AppContent.updateInfo);
//						Toast.makeText(LogoActivity.this, "http://192.168.1.105:8080/permission/permission/test.mvc", Toast.LENGTH_LONG).show();
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    InputStream inStream = conn.getInputStream();
                    while ((len = inStream.read(data)) != -1) {
                        outStream.write(data, 0, len);
                    }
                    inStream.close();
                    String result = new String(outStream.toByteArray(), "GBK");// 通过out.Stream.toByteArray获取到写的数据
//						Toast.makeText(LogoActivity.this, result, Toast.LENGTH_LONG).show();
                    jsonObject = new JSONObject(result);
                    NewVerCode = jsonObject.getInt("verCode");

                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    mUpdateManager.update = false;
                    e.printStackTrace();
                }
            };
        }.start();
    }
}
