package com.xatu.yuexin.welding4sl.service;

import android.content.Context;
import android.widget.Toast;

import com.xatu.yuexin.util.db.DBHelper;
import com.xatu.yuexin.util.db.DataBaseManager;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.vo.HotmeltData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/31.
 */

public class WeldingData2DB {
    public int STATIC_FAIL = 1;
    public int STATIC_SUCCESS = 2;
    private DataBaseManager db =null;
    private Context context;
    public WeldingData2DB(Context c){
        this.context = c;
        db = DataBaseManager.getInstance(context);
    }




    public int insertHot4Bluetooth(Map map) {
        DataBaseManager db = DataBaseManager.getInstance(context);
        // TODO Auto-generated method stub
        int res = 0;
        //DBResult rst = new DBResult();
        // Connection conn = JdbcUtils.getConnection();
        String[] msgs ={"","","","","","","0","","","","",""};//初始化解决即使没有GPS信息也可插入

        try {

            String sql = "";
            if(isHaveData()){
                sql = "update "+ DBHelper.TB_NAME_WELDING_DATA_HOT + " set HCompany = '"+map.get("COMPANYCODE")+"' , HManagement = '"+map.get("PILOTFUSENo")+"'," +
                        "HNo = '"+map.get("N.WELD")+"',HDate='"+map.get("DATE")+"',HHour='"+map.get("HOUR")+"',HEnd='"+map.get("COMPLETION HOUR")+"'," +
                        "HProject='"+map.get("PROJECT MANAGER")+"',HOperat='"+map.get("OPERATOR")+"',HWay='"+map.get("PLACE")+"',HInfo='"+map.get("WELD BOND CODE")+"'," +
                        "HMode='"+map.get("MODE")+"',HPipe1='"+map.get("N.BATCH PIPE 1")+"',HBrand1='"+map.get("MANUFACTURER PIPE 1")+"',HPipe2='"+map.get("N.BATCH PIPE 2")+"'," +
                        "HBrand2='"+map.get("MANUFACTURER PIPE 2")+"',HConstruction_code='"+map.get("CONSTRUCT.CODE")+"',HTemp='"+map.get("TEMPERATURE")+"',HNorme='"+map.get("STANDARD")+"'," +
                        "HPE11='"+map.get("RESIN")+"',HDiam='"+map.get("DIAMETER")+"',HThick='"+map.get("SDR")+"',HTHE='"+map.get("HEATER TEMPERATURE")+"'," +
                        "HDrg='"+map.get("DRAG PRESSURE")+"',HPb='"+map.get("BEAD UP")+"',HPmB='"+map.get("BEADUP_MEANPRESSURE")+"',HTb='"+map.get("BEADUP_TIME")+"'," +
                        "HTbRe='"+map.get("BEADUP_REALTIME")+"',HPs='"+map.get("SOAK")+"',HPmS='"+map.get("SOAK_MEANPRESSURE")+"',HTs='"+map.get("SOAK_TIME")+"'," +
                        "HTsRe='"+map.get("SOAK_REALTIME")+"',HTej='"+map.get("DWELL TIME")+"',HTup='"+map.get("SETTING PRESSURE TIME")+"',HPf='"+map.get("FUSION")+"'," +
                        "HPmF='"+map.get("FUSION_MEANPRESSURE")+"',HTf='"+map.get("FUSION_TIME")+"',HTfRe='"+map.get("FUSION_REALTIME")+"',HPco='"+map.get("COOLING")+"'," +
                        "HPmC='"+map.get("COOLING_MEANPRESSURE")+"',HTCo='"+map.get("COOLING_TIME")+"',HTrCo='"+map.get("COOLING_REALTIME")+"',HEr='"+map.get("INFORMATIONS")+"'," +
                        "HGPS_UTC='',HGPS_latitude='',HGPS_longitude='',HGPS_status='',HGPS_high='') where uuid = '"+AppContent.onlyUUID+"'";
            }else {
                sql = "insert into " + DBHelper.TB_NAME_WELDING_DATA_HOT + "(uuid, HCompany,HManagement,HNo,HDate,HHour,HEnd,HProject,HOperat,HWay,HInfo,HMode,HPipe1,HBrand1,HPipe2,HBrand2,HConstruction_code,HTemp,HNorme,HPE11,HDiam,HThick,HTHE,HDrg,HPb,HPmB,HTb,HTbRe,HPs,HPmS,HTs,HTsRe,HTej,HTup,HPf,HPmF,HTf,HTfRe,HPco,HPmC,HTCo,HTrCo,HEr,HGPS_UTC,HGPS_latitude,HGPS_longitude,HGPS_status,HGPS_high)"
                        + " values ('"
                        + AppContent.onlyUUID+ "','"
                        + map.get("COMPANYCODE")  + "','"+ map.get("PILOTFUSENo")+ "','"+ map.get("N.WELD") + "','" + map.get("DATE")+ "','"+ map.get("HOUR") + "','"+ map.get("COMPLETION HOUR")+ "','"
                        + map.get("PROJECT MANAGER") + "','"+ map.get("OPERATOR")+ "','"+ map.get("PLACE")+ "','"+ map.get("WELD BOND CODE")+ "','"
                        + map.get("MODE")+ "','"+ map.get("N.BATCH PIPE 1")+ "','"+ map.get("MANUFACTURER PIPE 1")+ "','"+ map.get("N.BATCH PIPE 2")+ "','"
                        + map.get("MANUFACTURER PIPE 2")+ "','"+ map.get("CONSTRUCT.CODE") + "','"+ map.get("TEMPERATURE")+ "','"+ map.get("STANDARD")+ "','"
                        + map.get("RESIN")+ "','"+ map.get("DIAMETER")+ "','"+ map.get("SDR")+ "','"+ map.get("HEATER TEMPERATURE") + "','"
                        + map.get("DRAG PRESSURE")+ "','"+ map.get("BEAD UP") + "','"+ map.get("BEADUP_MEANPRESSURE")+ "','" + map.get("BEADUP_TIME")+ "','"
                        + map.get("BEADUP_REALTIME")+ "','"+ map.get("SOAK")+ "','"+ map.get("SOAK_MEANPRESSURE") + "','"+ map.get("SOAK_TIME")+ "','"
                        + map.get("SOAK_REALTIME") + "','" + map.get("DWELL TIME") + "','"+ map.get("SETTING PRESSURE TIME")+ "','"+ map.get("FUSION")+ "','"
                        + map.get("FUSION_MEANPRESSURE")+ "','"+ map.get("FUSION_TIME")+ "','"+ map.get("FUSION_REALTIME")+ "','"+ map.get("COOLING")+ "','"
                        + map.get("COOLING_MEANPRESSURE")+ "','"+ map.get("COOLING_TIME")+ "','"+ map.get("COOLING_REALTIME")+ "','"+ map.get("INFORMATIONS")
                        + "','"
                        + "(Integer.parseInt(msgs[1].substring(0, 2))+8)" + "时" + "msgs[1].substring(2,4)" + "分" + "msgs[1].substring(4,6)" + "秒"
                        + "','"
                        + "msgs[2].substring(0, 2)" + "度" + "msgs[2].substring(2, msgs[2].length())" + "分"
                        + "','"
                        + "msgs[4].substring(0, 3)" + "度" + "msgs[4].substring(3, msgs[4].length())" + "分"
                        + "','"
                        + msgs[6]
                        + "','"
                        + msgs[9]
                        + "')";

            }


            db.insertDataBySql(sql,new String[]{});
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(context,"加入数据库完成",Toast.LENGTH_LONG).show();

        return res;
    }


    public void insertInput2DB(Map map){

        /**
         * 工程编号：Hway
         * 焊工编号：HOperat
         * 焊口编号：HInfo
         * 监理员编号（项目经理，该字段原来为项目经理，现在被监理员占用）：HProject
         * 质检员编号（工程管理员编号，这两个用的是一个字段）：HConstruction_code
         * 施工企业编号（热熔没有，电熔有）
         * 管理编号（热熔没有，电熔有）
         */
        String sql = "";
        if(isHaveData()){
            sql = "update "+DBHelper.TB_NAME_WELDING_DATA_HOT+" set Hway='"+map.get("Hway")+"', HOperat='"+map.get("HOperat")+"', " +
                    "HInfo='"+map.get("HInfo")+"', HProject='"+map.get("HProject")+"', HConstruction_code='"+map.get("HConstruction_code")+"' where uuid='"+AppContent.onlyUUID+"'";
        }else{
            sql = "insert into "+DBHelper.TB_NAME_WELDING_DATA_HOT+"(Hway,HOperat,HInfo,HProject,HConstruction_code,uuid) " +
                    "values('"+map.get("Hway")+"','"+map.get("HOperat")+"','"+map.get("HInfo")+"','"+map.get("HProject")+"','"+map.get("HConstruction_code")+"','"+AppContent.onlyUUID+"')";
        }

        try{
            db.insertDataBySql(sql,new String[]{});
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean isHaveData(){
        String checkSql = "select * from "+DBHelper.TB_NAME_WELDING_DATA_HOT+" where uuid = '"+AppContent.onlyUUID+"'";
        List list = new ArrayList();
        try {
            list = db.queryData2Map(checkSql, new String[]{}, new HotmeltData());
        }catch (Exception e){
            e.printStackTrace();
        }
        return list.size()>=1;
    }
}

