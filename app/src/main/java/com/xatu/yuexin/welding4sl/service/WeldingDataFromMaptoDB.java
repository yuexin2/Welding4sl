package com.xatu.yuexin.welding4sl.service;

import android.content.Context;
import android.widget.Toast;

import com.xatu.yuexin.util.db.DBHelper;
import com.xatu.yuexin.util.db.DataBaseManager;
import com.xatu.yuexin.welding4sl.AppContent;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/31.
 */

public class WeldingDataFromMaptoDB {
    public int STATIC_FAIL = 1;
    public int STATIC_SUCCESS = 2;

    private Context context;
    public WeldingDataFromMaptoDB(Context c){
        this.context = c;
    }


    public void fromMaptoDB(Map map){
        insert15hot(map);
    }

    private int insert15hot(Map map) {

        // TODO Auto-generated method stub
        int res = 0;
        //DBResult rst = new DBResult();
        // Connection conn = JdbcUtils.getConnection();
        String[] msgs ={"","","","","","","0","","","","",""};//初始化解决即使没有GPS信息也可插入
//        String gpsdata=map.get("GPS").toString();
//        if(gpsdata!=null){
//            msgs = gpsdata.split(";");
//        }



//        if(msgs[3].indexOf("N")==-1 ){
//            msgs[1]="999999999999";
//            msgs[2]="999999999999";
//            msgs[4]="999999999999";
//        }


//        switch (Integer.parseInt(msgs[6]))
//        {
//            case 0:msgs[6]="未定位";break;
//            case 1:msgs[6]="非差分定位";break;
//            case 2:msgs[6]="差分定位";break;
//            case 6:msgs[6]="正在估算";break;
//            default: break;
//        }

        String sql = "insert into "+DBHelper.TB_NAME_INFO+"( md5,HCompany,HManagement,HNo,HDate,HHour,HEnd,HProject,HOperat,HWay,HInfo,HMode,HPipe1,HBrand1,HPipe2,HBrand2,HConstruction_code,HTemp,HNorme,HPE11,HDiam,HThick,HTHE,HDrg,HPb,HPmB,HTb,HTbRe,HPs,HPmS,HTs,HTsRe,HTej,HTup,HPf,HPmF,HTf,HTfRe,HPco,HPmC,HTCo,HTrCo,HEr,HGPS_UTC,HGPS_latitude,HGPS_longitude,HGPS_status,HGPS_high)"

                + " values ('"+md5Str+"','"
                + v2g9Info[13]
                + "','"
                + v2g9Info[1]
                + "','"
                + v2g9Info[4]
                + "','"
                + v2g9Info[5]
                + "','"
                + v2g9Info[6]
                + "','"
                + v2g9Info[7]

                + "','"
                + v2g9Info[9]
                + "','"
                + v2g9Info[48]
                + "','"
                + v2g9Info[11]
                + "','"
                + v2g9Info[12]
                + "','"
                + v2g9Info[14]
                + "','"
                + v2g9Info[15]
                + "','"
                + v2g9Info[16]
                + "','"
                + v2g9Info[17]
                + "','"
                + v2g9Info[18]
                + "','"
                + v2g9Info[19]
                + "','"
                + v2g9Info[20]
                + "','"
                + v2g9Info[21]
                + "','"
                + v2g9Info[22]
                + "','"
                + v2g9Info[23]
                + "','"
                + v2g9Info[24]
                + "','"
                + v2g9Info[25]
                + "','"
                + v2g9Info[27]
                + "','"
                + v2g9Info[28]
                + "','"
                + v2g9Info[29]
                + "','"
                + v2g9Info[30]
                + "','"
                + v2g9Info[31]
                + "','"
                + v2g9Info[32]
                + "','"
                + v2g9Info[33]
                + "','"
                + v2g9Info[34]
                + "','"
                + v2g9Info[35]
                + "','"
                + v2g9Info[36]
                + "','"
                + v2g9Info[37]
                + "','"
                + v2g9Info[38]
                + "','"
                + v2g9Info[39]
                + "','"
                + v2g9Info[40]
                + "','"
                + v2g9Info[41]
                + "','"
                + v2g9Info[42]
                + "','"
                + v2g9Info[43]
                + "','"
                + v2g9Info[44]
                + "','"
                + v2g9Info[45]
                + "','"
                + v2g9Info[46]
                + "','"
                +(Integer.parseInt(msgs[1].substring(0, 2))+8)+"时"+msgs[1].substring(2,4)+"分"+msgs[1].substring(4,6)+"秒"
                + "','"
                + msgs[2].substring(0, 2)+"度"+msgs[2].substring(2, msgs[2].length())+"分"
                + "','"
                + msgs[4].substring(0, 3)+"度"+msgs[4].substring(3, msgs[4].length())+"分"
                + "','"
                + msgs[6]
                + "','"
                + msgs[9]
                + "')";
        System.out.println("热熔---》"+sql);
        DataBaseManager db = DataBaseManager.getInstance(context);
        try {
            db.insertDataBySql(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(context,"加入数据库完成",Toast.LENGTH_LONG).show();

//		//发送短信
//		String result = v2g9Info[46];
//		if(!result.equals("ok") && !result.equals("OK") && !result.equals("成功")  ){
//			SmsSend s = new SmsSend();
//			s.sendSms(v2g9Info[4]);
//		}
        return res;
    }


}

