package com.xatu.yuexin.welding4sl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xatu.yuexin.util.gps.GpsService;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.R;
import com.xatu.yuexin.welding4sl.service.BluetoothService;
import com.xatu.yuexin.welding4sl.service.WeldingData2DB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InputInfoActivity extends AppCompatActivity {

    private EditText projectNum;//工程编号
    private EditText hgNum;//焊工编号
    private EditText hkNum;//焊口编号
    private EditText jlNum;//监理员编号
    private EditText zjNum;//质检员编号
    private EditText proManagerNum;//项目经理编号
    private EditText projectAdminNum;//工程管理员编号
    private EditText constructionNum;//施工企业编号
    private EditText managerNum;//管理编号
    private Button inputFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);

        projectNum = (EditText) findViewById(R.id.projectNum);//工程编号
        hgNum = (EditText) findViewById(R.id.hgNum);//焊工编号
        hkNum = (EditText) findViewById(R.id.hkNum);//焊口编号
        jlNum = (EditText) findViewById(R.id.jlNum);//监理员编号
        zjNum = (EditText) findViewById(R.id.zjNum);//质检员编号
        proManagerNum = (EditText) findViewById(R.id.proManagerNum);//项目经理编号
        projectAdminNum = (EditText) findViewById(R.id.projectAdminNum);//工程管理员编号
        constructionNum = (EditText) findViewById(R.id.constructionNum);//施工企业编号
        managerNum = (EditText) findViewById(R.id.managerNum);//管理编号
        inputFinish = (Button)findViewById(R.id.inputFinish);
        inputFinish.setOnClickListener(clickListener);

    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //想法，先判断由蓝牙service获取数据。若没有数据则一直等待
            /**
             * 工程编号：Hway
             * 焊工编号：HOperat
             * 焊口编号：HInfo
             * 监理员编号（项目经理，该字段原来为项目经理，现在被监理员占用）：HProject
             * 质检员编号（工程管理员编号，这两个用的是一个字段）：HConstruction_code
             * 施工企业编号（热熔没有，电熔有）
             * 管理编号（热熔没有，电熔有）
             */
            Map map = new HashMap();
            map.put("Hway",projectNum.getText().toString());
            map.put("HOperat",hgNum.getText().toString());
            map.put("HInfo",hkNum.getText().toString());
            map.put("HProject",jlNum.getText().toString());
            map.put("HConstruction_code",zjNum.getText().toString());
            WeldingData2DB wdf2db = new WeldingData2DB(getApplication());
            wdf2db.insertInput2DB(map);

            Intent stopBluetoothService = new Intent(InputInfoActivity.this,BluetoothService.class);
            stopService(stopBluetoothService);

            Intent stopgpsService = new Intent(InputInfoActivity.this,GpsService.class);
            stopService(stopgpsService);
            AppContent.latitude = "99.9999999999";//纬度
            AppContent.longitude = "999.9999999999";//纬度

            AppContent.gpstime = "";
            AppContent.gpsHigh = "";
            AppContent.gpsStatue = "";

            Intent intent = new Intent( InputInfoActivity.this , LoginActivity.class);
            startActivity(intent);
            InputInfoActivity.this.finish();
        }
    };



}
