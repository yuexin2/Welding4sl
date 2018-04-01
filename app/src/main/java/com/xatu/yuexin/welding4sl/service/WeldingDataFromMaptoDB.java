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

    }

    private int insert49hot(Map map) {

        // TODO Auto-generated method stub
        int res = 0;
        //DBResult rst = new DBResult();
        // Connection conn = JdbcUtils.getConnection();
        String[] msgs ={"","","","","","","0","","","","",""};//初始化解决即使没有GPS信息也可插入
        String gpsdata=map.get("GPS").toString();
        if(gpsdata!=null){
            msgs = gpsdata.split(";");
        }



        if(msgs[3].indexOf("N")==-1 ){
            msgs[1]="999999999999";
            msgs[2]="999999999999";
            msgs[4]="999999999999";
        }


        switch (Integer.parseInt(msgs[6]))
        {
            case 0:msgs[6]="未定位";break;
            case 1:msgs[6]="非差分定位";break;
            case 2:msgs[6]="差分定位";break;
            case 6:msgs[6]="正在估算";break;
            default: break;
        }

        String tempdate = (String) map.get("Date (Time)");
        System.out.println(tempdate);
        String date = tempdate.split(" ")[0];
        System.out.println(date);
        String time = tempdate.split(" ")[1];
        time = time.substring(1,time.length()-1);
        System.out.println(time);

        String checkSql = "select * from tempp05hotmeltdata where HManagement='"+map.get("Serial No.")+"' and HDate='"+date+"' and HHour='"+ time+"'";
        System.out.println(checkSql);


        String sql = "insert into "+DBHelper.TB_NAME_WELDING_DATA_HOT+"( HFrame,HDate,HHour,HManagement,HOperat,HWay,HInfo," +
                "HNorme,HPE11," +
                "HDiam," +
                "HTHE," +
                "HDrg," +
                "HPmB," +
                "HTsRe," +
                "HTej," +
                "HPmC," +
                "HTCo," +
                "HTrCo,HThick,HEr,Hcompany" +
                "HGPS_UTC,HGPS_latitude,HGPS_longitude,HGPS_status,HGPS_high)"
                + " values ('"
                +map.get("Machine Type")
                + "','"
                + date
                + "','"
                +time
                + "','"
                + map.get("Serial No.")
                + "','"
                + map.get("Operator Code")
                + "','"
                + map.get("Job Name")
                + "','"
                + map.get("Current Joint")
                + "','"
                + map.get("Weld Std")
                + "','"
                + map.get("Material")
                + "','"
                + map.get("外 径")
                + "','"
                + map.get("Actual temp.")
                + "','"
                + map.get("Slide press.")
                + "','"
                + map.get("Heat soak press.")
                + "','"
                + map.get("Heat soak time")
                + "','"
                + map.get("Chg. over time")
                + "','"
                + map.get("Fusion press.")
                + "','"
                + map.get("Desired Cool Time")
                + "','"
                + map.get("Cool Time")
                + "','"
                + map.get("SDR")
                + "','"
                + map.get("INFORMATIONS")
                + "','"
                + map.get("COMPANY CODE")
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

