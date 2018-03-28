package com.xatu.yuexin.util.db;


import java.text.SimpleDateFormat;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.xatu.yuexin.welding4sl.AppContent;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TB_NAME_WELDING_DATA_HOT = "weldingDataHot";
	public static final String TB_NAME_INFO = "system_info";
	public static final String ID = "_id";
	public static final int DATABASE_VERSION = 1;
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
			String sql = "CREATE TABLE  " + TB_NAME_WELDING_DATA_HOT + " (proid varchar NOT NULL,id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,  "
					+ "  procode varchar NOT NULL,  userid varchar NOT NULL,  "
					+ "username varchar NOT NULL,hgcode varcharNOT NULL,  hgname varchar NOT NULL, rid varchar NOT NULL, mcode varchar NOT NULL,  "
					+ "lastmcode varchar ,  gps varchar NOT NULL,  ygarea varchar NOT NULL,  hjdate varchar NOT NULL,  temperature varchar NOT NULL,  sdr varchar NOT NULL,  "
					+ "material varchar NOT NULL , pipespec varchar NOT NULL,  hjtemperature varchar NOT NULL, tdyl varchar NOT NULL,hjzyl varchar NOT NULL,xrtime varchar NOT NULL,qhtime varchar NOT NULL "
					+ ",sytime varchar NOT NULL,lqtime varchar NOT NULL,pcode varchar NOT NULL,hjresult varchar NOT NULL,wgjqfbjc varchar NOT NULL,isupload varchar NOT NULL,proname varchar NOT NULL)";
			db.execSQL(sql);
		}
		if(!tabbleIsExist(TB_NAME_INFO)){
			String sql = "CREATE TABLE  " + TB_NAME_INFO + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,  "
					+ "   imei varchar NOT NULL , isRegister varchar , registerTime varchar )";
			db.execSQL(sql);
			
			
			String insertSql = "insert into "+TB_NAME_INFO+"(imei,isRegister) values(" +
					"'"+ AppContent.phoneUtil.getIMEI()+"','no')";
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