package com.xatu.yuexin.welding4sl.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.xatu.yuexin.util.PhoneUtil;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HelloActivity extends AppCompatActivity {
    private static final int FAILURE = 0; // 失败
    private static final int SUCCESS = 1; // 成功
    private static final int OFFLINE = 2; // 如果支持离线阅读，进入离线模式
    private static final int DELAYTIME = 1; // 延迟时间




//		SaveObjectUtils saveObjectUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**全屏设置，隐藏窗口所有装饰**/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hello);


        /////////////初始化部分数据、、、、、、、、、、
//			saveObjectUtils = new SaveObjectUtils(getApplicationContext(),"saveObjectUtils");
//			saveObjectUtils.setObject("getProjectList", "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectGdhjService/getProjectList");
//			saveObjectUtils.setObject("getHgList", "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectInfoService/getHangongListByMCode");
//			saveObjectUtils.setObject("getRid", "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectGdhjService/getProjectHkCode");
//			saveObjectUtils.setObject("uploadData", "https://ennvmp.enn.cn:4443/ServiceEngine/rest/ennProjectGdhjService/addHanjieRecord3");
//			saveObjectUtils.setObject("myProject", new Project());
        /////////////初始化部分数据、、、、、、、、、、
        super.onCreate(savedInstanceState);
        //创建必要的文件夹
        //FileUtils fu = new FileUtils();
        //fu.createSDDir(AppContent.picPath);
        AppContent.phoneUtil = PhoneUtil.getInstance(getApplicationContext());


        //final Intent it = new Intent(this, ProjectListActivity.class); //你要转向的Activity
        final Intent it2 = new Intent(this, CheckFaceActivity.class); //你要转向的Activity
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                startActivity(it2);

                HelloActivity.this.finish();
            }
        };
        timer.schedule(task, 1000 * DELAYTIME);

        new AsyncTask<Void, Void, Integer>(){//异步任务，进行软件更新等

            @Override
            protected Integer doInBackground(Void... params) {
                int result;
                result = loadingCache();
                return result;
            }

            @Override
            protected void onPostExecute(Integer result) {

            };
        }.execute(new Void[]{});
    }

    private int loadingCache() {
//			if (BaseApplication.mNetWorkState == NetworkUtils.NETWORN_NONE) {
        return OFFLINE;
//			}
//			return SUCCESS;
    }
}
