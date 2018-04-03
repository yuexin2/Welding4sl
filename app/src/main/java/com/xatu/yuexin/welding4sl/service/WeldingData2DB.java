package com.xatu.yuexin.welding4sl.service;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.xatu.yuexin.util.db.DBHelper;
import com.xatu.yuexin.util.db.DataBaseManager;
import com.xatu.yuexin.welding4sl.AppContent;
import com.xatu.yuexin.welding4sl.vo.HotmeltData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
//    private LocationManager lm;


    public WeldingData2DB(Context c){
        this.context = c;
        db = DataBaseManager.getInstance(context);
//        lm = (LocationManager)c.getSystemService(Context.LOCATION_SERVICE);
//
//        // 为获取地理位置信息时设置查询条件
//        String bestProvider = lm.getBestProvider(getCriteria(), true);
//        // 获取位置信息
//        // 如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
//        Location location = lm.getLastKnownLocation(bestProvider);
//        //updateView(location);
//        // 监听状态
//        //lm.addGpsStatusListener(locationListener);
//        // 绑定监听，有4个参数
//        // 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
//        // 参数2，位置信息更新周期，单位毫秒
//        // 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
//        // 参数4，监听
//        // 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
//
//        // 1秒更新一次，或最小位移变化超过1米更新一次；
//        // 注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
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
                        "HGPS_UTC='"+AppContent.gpstime+"',HGPS_latitude='"+AppContent.latitude.substring(0, 2) + "度" + AppContent.latitude.substring(2, AppContent.latitude.length()) + "分"+"'," +
                        "HGPS_longitude='"+AppContent.longitude.substring(0, 3) + "度" + AppContent.longitude.substring(3, AppContent.longitude.length()) + "分"+"',HGPS_status='"+AppContent.gpsStatue+"',HGPS_high='"+AppContent.gpsHigh+"') where uuid = '"+AppContent.onlyUUID+"'";
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
                        + AppContent.gpstime
                        + "','"
                        + AppContent.latitude.substring(0, 2) + "度" + AppContent.latitude.substring(2, AppContent.latitude.length()) + "分"
                        + "','"
                        + AppContent.longitude.substring(0, 3) + "度" + AppContent.longitude.substring(3, AppContent.longitude.length()) + "分"
                        + "','"
                        + AppContent.gpsStatue
                        + "','"
                        + AppContent.gpsHigh
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
                    "HInfo='"+map.get("HInfo")+"', HProject='"+map.get("HProject")+"', HConstruction_code='"+map.get("HConstruction_code")+"', " +
                    "HGPS_UTC='"+AppContent.gpstime+"',HGPS_latitude='"+AppContent.latitude.substring(0, 2) + "度" + AppContent.latitude.substring(2, AppContent.latitude.length()) + "分"+"'," +
                    "HGPS_longitude='"+AppContent.longitude.substring(0, 3) + "度" + AppContent.longitude.substring(3, AppContent.longitude.length()) + "分"+"',HGPS_status='"+AppContent.gpsStatue+"',HGPS_high='"+AppContent.gpsHigh+"' where uuid='"+AppContent.onlyUUID+"'";
        }else{
            sql = "insert into "+DBHelper.TB_NAME_WELDING_DATA_HOT+"(Hway,HOperat,HInfo,HProject,HConstruction_code,HGPS_UTC,HGPS_latitude,HGPS_longitude,HGPS_status,HGPS_high,uuid) " +
                    "values('"+map.get("Hway")+"','"+map.get("HOperat")+"','"+map.get("HInfo")+"','"+map.get("HProject")+"','"+map.get("HConstruction_code")+"','HGPS_UTC='"+AppContent.gpstime+"',HGPS_latitude='"+AppContent.latitude.substring(0, 2) + "度" + AppContent.latitude.substring(2, AppContent.latitude.length()) + "分"+"'," +
            "HGPS_longitude='"+AppContent.longitude.substring(0, 3) + "度" + AppContent.longitude.substring(3, AppContent.longitude.length()) + "分"+"',HGPS_status='"+AppContent.gpsStatue+"',HGPS_high='"+AppContent.gpsHigh+"','"+AppContent.onlyUUID+"')";
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

//    LocationListener locationListener = new LocationListener() {
//
//        @Override
//        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onProviderEnabled(String arg0) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onProviderDisabled(String arg0) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onLocationChanged(Location arg0) {
//            // TODO Auto-generated method stub
//            //AppContent.project.setGps(arg0.getLongitude()+","+arg0.getLatitude());
//            String string = "纬度为：" + arg0.getLatitude() + ",经度为："
//                    + arg0.getLongitude();
//            latitude = arg0.getLatitude()+"";
//            longitude = arg0.getLongitude()+"";
//            Calendar calendar = Calendar.getInstance();
//
//            calendar.setTimeInMillis(arg0.getTime());
//            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            gpstime = df.format(calendar.getTime());
//            gpsHigh = arg0.getAltitude()+"";
//            //Toast.makeText(context, string, 1).show();
////			Project p = saveObjectUtils.getObject("myProject", Project.class);
////            p.setGps(arg0.getLongitude()+","+arg0.getLatitude());
////            saveObjectUtils.setObject("myProject", p);
//        }
//    };
//
//    private void getGps(){
//        //获取定位服务
//        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        //获取当前可用的位置控制器
//        List<String> list = locationManager.getProviders(true);
//        String provider;
//        if (list.contains(LocationManager.GPS_PROVIDER)) {
//            //是否为GPS位置控制器
//            provider = LocationManager.GPS_PROVIDER;
//        }
//        else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
//            //是否为网络位置控制器
//            provider = LocationManager.NETWORK_PROVIDER;
//
//        } else {
//            Toast.makeText(context, "请检查网络或GPS是否打开",Toast.LENGTH_LONG).show();
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(provider);
//        if (location != null) {
//            location = locationManager.getLastKnownLocation(provider);
//            if (location != null) {
//                //获取当前位置，这里只用到了经纬度
//                String string = "纬度为：" + location.getLatitude() + ",经度为："
//                        + location.getLongitude();
//                latitude = location.getLatitude()+"";
//                longitude = location.getLongitude()+"";
//                Calendar calendar = Calendar.getInstance();
//
//                calendar.setTimeInMillis(location.getTime());
//                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                gpstime = df.format(calendar.getTime());
//                gpsHigh = location.getAltitude()+"";
//                //AppContent.project.setGps(location.getLongitude()+","+location.getLatitude());
////	            Project p = saveObjectUtils.getObject("myProject", Project.class);
////	            p.setGps(location.getLongitude()+","+location.getLatitude());
////	            saveObjectUtils.setObject("myProject", p);
//            }
//        }
//
//        //绑定定位事件，监听位置是否改变
//        //第一个参数为控制器类型第二个参数为监听位置变化的时间间隔（单位：毫秒）
//        //第三个参数为位置变化的间隔（单位：米）第四个参数为位置监听器
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
//    }
//
//    /**
//     * 返回查询条件
//     *
//     * @return
//     */
//    private Criteria getCriteria() {
//        Criteria criteria = new Criteria();
//        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        // 设置是否要求速度
//        criteria.setSpeedRequired(true);
//        // 设置是否允许运营商收费
//        criteria.setCostAllowed(true);
//        // 设置是否需要方位信息
//        criteria.setBearingRequired(true);
//        // 设置是否需要海拔信息
//        criteria.setAltitudeRequired(true);
//        // 设置对电源的需求
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//        return criteria;
//    }
}

