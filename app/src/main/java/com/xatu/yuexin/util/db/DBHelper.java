package com.xatu.yuexin.util.db;


import java.text.SimpleDateFormat;
import java.util.Date;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.xatu.yuexin.util.ConsoleUtil;
import com.xatu.yuexin.welding4sl.AppContent;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TB_NAME_WELDING_DATA_HOT = "weldingDataHot";
	public static final String TB_NAME_WELDING_DATA_ELE = "weldingDataEle";

	public static final String TB_NAME_INFO = "system_info";
	public static final String ID = "_id";
	public static final int DATABASE_VERSION = 2;
	public static final String DB_NAME = "weldingApp";
//	public static final String LOCATION = "my_location";
	
	/**
	 * 构造方法
	 * @param context上下文环境
	 * @param name数据库名
	 * @param factory可选的游标工厂（通常是 Null）
	 * @param version数据库的版本（整形数据）
	 */
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	public DBHelper(Context context) {
		this(context, DB_NAME, null, DATABASE_VERSION);  
	}
	/**
	 * 创建数据库后，对数据库的操作 
	 */
	public void onCreate(SQLiteDatabase db) {
		//建立表格
		//如果修改数据库一定要修改DATABASE_VERSION，切记切记！！！
		if(!tabbleIsExist(TB_NAME_WELDING_DATA_HOT)){
			System.out.println("建立表格");
			//不存在表时，创建表
			String sql = "CREATE TABLE  " + TB_NAME_WELDING_DATA_HOT + " (  HId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  " +
					"HNo varchar,  HDate varchar,  HHour varchar,  HEnd varchar,  HOperat varchar,  HWay varchar,  " +
					"HInfo varchar,  HManagement varchar,  HProject varchar,  HConstruction_code varchar,  " +
					"HMode varchar,  HNorme varchar,  HTemp varchar,  HPE varchar,  HDiam varchar,  HThick varchar,  " +
					"HPipe1 varchar,  HType1 varchar,  HBrand1 varchar,  HPE11 varchar,  HMateri1 varchar,  " +
					"HMFR1 varchar,  HInfo_11 varchar,  HInfo_21 varchar,  HPipe2 varchar,  HType2 varchar,  " +
					"HBrand2 varchar,  HPE22 varchar,  HMateri2 varchar,  HMFR2 varchar,  HInfo_12 varchar,  " +
					"HInfo_22 varchar,  HTHE varchar,  HDrg varchar,  HPb varchar,  HTb varchar,  HTbRe varchar,  " +
					"HPs varchar,  HTs varchar,  HTsRe varchar,  HTej varchar,  HTup varchar,  HPf varchar,  " +
					"HTf varchar,  HTfRe varchar,  HPf2 varchar,  HTf2 varchar,  HTf2Re varchar,  HPCo varchar,  " +
					"HTCo varchar,  HTrCo varchar,  HPmB varchar,  HPmS varchar,  HPmF varchar,  HPmC varchar,  " +
					"HEr varchar,  HMIId int(11) DEFAULT NULL,  HWeld varchar,  HBond varchar,  HCode varchar,  " +
					"HCompany varchar,  HGPS_UTC varchar,  HGPS_latitude varchar,  HGPS_longitude varchar,  " +
					"HGPS_status varchar,  HGPS_high varchar,  HFrame varchar,  " +
					"uuid varchar NOT NULL,serviceStatus varchar , inputStatus varchar)";
			//uuid保证服务和输入同步，serviceStatus为服务状态，inputStatus为输入的状态
			db.execSQL(sql);
		}
		if(!tabbleIsExist(TB_NAME_INFO)){
            //不存在表时，创建表，并且加入数据
            String sql = "CREATE TABLE IF NOT EXISTS " + TB_NAME_INFO + " (id INTEGER PRIMARY KEY,  " +
                    "phoneId varchar ,  updateTime varchar ,  systemVersion varchar ,  " +
                    "installTime varchar ,lastUsingTime varchar,  openTime varchar , endDate varchar , " +
                    "usingTimes int,  canUsingDay int ,  " +
                    "  usingLogin int , isPay int,  appVersion varchar, pwd varchar,isRoot int )";
            db.execSQL(sql);
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ConsoleUtil cu = new ConsoleUtil();
            String isRoot = cu.getRootAhth()?"1":"0";

            Date nowTime = new Date();
            Date usingEndTime = new Date(nowTime.getTime() + AppContent.canUsingDay* 24 * 60 * 60 * 1000);
            String insertSql = "insert into "+TB_NAME_INFO+"(phoneId,systemVersion,installTime,lastUsingTime,openTime,endDate,usingTimes,canUsingDay,usingLogin,isPay,appVersion,pwd,isRoot) values('"
                    +AppContent.phoneUtil.getIMEI()+ "','"+ AppContent.phoneUtil.getVersion()+"','"+ time.format(nowTime) +"','"+ time.format(nowTime) +"','"+ time.format(nowTime) +"','"+ time.format(usingEndTime) +"',"+ 1 +","+ AppContent.canUsingDay+","+0+","+0+",'"+AppContent.appUtils.getVersionName()+"',' ',"+isRoot+")";
            db.execSQL(insertSql);
			
		}
	}
	/**
	 * 数据库更新的方法
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion > oldVersion){
			db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_WELDING_DATA_HOT);
			db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_INFO);
			onCreate(db);
		}
	}
	
	private boolean tabbleIsExist(String tableName){
        boolean result = false;
        if(tableName == null){
                return false;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
                db = this.getReadableDatabase();
                String sql = "select count(*) as c from sqlite_master  where type ='table' and name ='"+tableName.trim()+"' ";
                cursor = db.rawQuery(sql, null);
                if(cursor.moveToNext()){
                        int count = cursor.getInt(0);
                        if(count>0){
                                result = true;
                        }
                }
                
        } catch (Exception e) {
                // TODO: handle exception
        }               
        return result;
}
     
 }