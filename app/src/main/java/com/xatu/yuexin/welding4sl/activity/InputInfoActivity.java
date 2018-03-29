package com.xatu.yuexin.welding4sl.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.xatu.yuexin.welding4sl.R;

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

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
