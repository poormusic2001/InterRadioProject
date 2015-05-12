package com.internetradio;

import java.io.UnsupportedEncodingException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class StationDB extends SQLiteOpenHelper{

	private static final String db_Name ="Station_InfoDB";
	private static final String Table_Name ="Station_Info";
	private static final int db_Version=1;
	
	private static final String[] Station_MmsUrl={
		   "mmsh://bcr.media.hinet.net/RA000004",//ASIAFM衛星音樂台
		   "mmsh://bcr.media.hinet.net/RA000001",//Apple line 蘋果線上
		   "mmsh://bcr.media.hinet.net/RA000010",//BestRadio 台中好事
		   "mmsh://bcr.media.hinet.net/RA000013",//BestRadio 台北好事
		   "mmsh://bcr.media.hinet.net/RA000011",//BestRadio 花蓮好事
		   "mmsh://bcr.media.hinet.net/RA000012",//BestRadio 高雄港都
		   "mmsh://bcr.media.hinet.net/RA000019",//Flyradio飛揚調頻
		   "mmsh://bcr.media.hinet.net/RA000041",//KISS RADIO 大苗栗廣播
		   "mmsh://bcr.media.hinet.net/RA000040",//KISS RADIO大眾廣播電台
		   "mmsh://bcr.media.hinet.net/RA000042",//KISSRadio 網路音樂台
		   "mmsh://bcr.media.hinet.net/RA000080",//POP Radio
		   "mmsh://bcr.media.hinet.net/RA000005",//中廣古典網
		   "mmsh://bcr.media.hinet.net/RA000009",//中廣流行網
		   "mmsh://bcr.media.hinet.net/RA000007",//中廣音樂網i radio
		   "mmsh://bcr.media.hinet.net/RA000031",//佳音CCM
		   "mmsh://bcr.media.hinet.net/RA000047",//全國廣播音樂網
		   "mmsh://bcr.media.hinet.net/RA000035",//台北之音HitFM聯播網 台中
		   "mmsh://bcr.media.hinet.net/RA000036",//台北之音HitFM聯播網 台北
		   "mmsh://bcr.media.hinet.net/RA000034",//台北之音HitFM聯播網 高雄
		   "mmsh://bcr.media.hinet.net/RA000018",//台北愛樂
		   "mmsh://bcr.media.hinet.net/RA000061",//台灣之音-音樂
		   "mmsh://bcr.media.hinet.net/RA000064",//太陽電台
		   "mmsh://bcr.media.hinet.net/RA000014",//奇美古典音樂網
		   "mmsh://bcr.media.hinet.net/RA000066",//寶島新聲廣播電台
		   "mmsh://bcr.media.hinet.net/RA000002",//ASIA FM92.3亞太電台
		   "mmsh://bcr.media.hinet.net/RA000003",//ASIA FM92.7亞州電台
		   "mmsh://bcr.media.hinet.net/RA000028",//GOLD FM-台中城市廣播
		   "mmsh://bcr.media.hinet.net/RA000027",//GOLD FM-台北健康電台
		   "mmsh://bcr.media.hinet.net/RA000037",//IC之音
		   "mmsh://bcr.media.hinet.net/RA000029",//佳音廣播電台
		   "mmsh://bcr.media.hinet.net/RA000030",//佳音電台2台
		   "mmsh://bcr.media.hinet.net/RA000068",//全國廣播
		   "mmsh://bcr.media.hinet.net/RA000046",//台中廣播
		   "mmsh://bcr.media.hinet.net/RA000070",//台北廣播電台- 都會資訊頻道
		   "mmsh://bcr.media.hinet.net/RA000069",//台北廣播電台喔海洋頻道
		   "mmsh://bcr.media.hinet.net/RA000067",//大千電台
		   "mmsh://bcr.media.hinet.net/RA000017",//大愛網路電台
		   "mmsh://bcr.media.hinet.net/RA000065",//宜蘭中山電台
		   "mmsh://bcr.media.hinet.net/RA000020",//環宇廣播
		   "mmsh://bcr.media.hinet.net/RA000033",//真心之音廣播電台
		   "mmsh://bcr.media.hinet.net/RA000079",//青春線上
		   "mmsh://bcr.media.hinet.net/RA000072",//飛碟電台
		   "mmsh://bcr.media.hinet.net/RA000073",//NEWS98新聞網
		   "mmsh://bcr.media.hinet.net/RA000008",//中廣新聞網
		   "mmsh://bcr.media.hinet.net/RA000063",//台灣之音-華語
		   "mmsh://bcr.media.hinet.net/RA000026",//復興廣播電台 短波網
		   "mmsh://bcr.media.hinet.net/RA000024",//復興廣播電台 第一網
		   "mmsh://bcr.media.hinet.net/RA000025",//復興廣播電台 第二網
		   "mmsh://bcr.media.hinet.net/RA000015",//正聲台北調幅台
		   "mmsh://bcr.media.hinet.net/RA000016",//正聲台北調頻台
		   "mmsh://bcr.media.hinet.net/RA000075",//漢聲光華網-中波
		   "mmsh://bcr.media.hinet.net/RA000077",//漢聲光華網-短波
		   "mmsh://bcr.media.hinet.net/RA000074",//漢聲廣播電台
		   "mmsh://bcr.media.hinet.net/RA000076",//漢聲廣播電台全國調頻網
		   "mmsh://bcr.media.hinet.net/RA000032",//綠色和平台灣文化廣播電台
		   "mmsh://bcr.media.hinet.net/RA000038",//ICRT
		   "mmsh://bcr.media.hinet.net/RA000060",//台灣之音-FM
		   "mmsh://bcr.media.hinet.net/RA000059",//台灣之音-亞洲
		   "mmsh://bcr.media.hinet.net/RA000062",//台灣之音-歐美及方言
};
	
	private static final String[] Station_MmsName={
		   "ASIAFM衛星音樂台",//ASIAFM衛星音樂台
		   "Apple line 蘋果線上",//Apple line 蘋果線上
		   "BestRadio 台中好事",//BestRadio 台中好事
		   "BestRadio 台北好事",//BestRadio 台北好事
		   "BestRadio 花蓮好事",//BestRadio 花蓮好事
		   "BestRadio 高雄港都",//BestRadio 高雄港都
		   "Flyradio飛揚調頻",//Flyradio飛揚調頻
		   "KISS RADIO 大苗栗廣播",//KISS RADIO 大苗栗廣播
		   "KISS RADIO大眾廣播電台",//KISS RADIO大眾廣播電台
		   "KISSRadio 網路音樂台",//KISSRadio 網路音樂台
		   "POP Radio",//POP Radio
		   "中廣古典網",//中廣古典網
		   "中廣流行網",//中廣流行網
		   "中廣音樂網i radio",//中廣音樂網i radio
		   "佳音CCM",//佳音CCM
		   "全國廣播音樂網",//全國廣播音樂網
		   "台北之音HitFM聯播網 台中",//台北之音HitFM聯播網 台中
		   "台北之音HitFM聯播網 台北",//台北之音HitFM聯播網 台北
		   "台北之音HitFM聯播網 高雄",//台北之音HitFM聯播網 高雄
		   "台北愛樂",//台北愛樂
		   "台灣之音-音樂",//台灣之音-音樂
		   "太陽電台",//太陽電台
		   "奇美古典音樂網",//奇美古典音樂網
		   "寶島新聲廣播電台",//寶島新聲廣播電台
		   "ASIA FM92.3亞太電台",//ASIA FM92.3亞太電台
		   "ASIA FM92.7亞州電台",//ASIA FM92.7亞州電台
		   "GOLD FM-台中城市廣播",//GOLD FM-台中城市廣播
		   "GOLD FM-台北健康電台",//GOLD FM-台北健康電台
		   "IC之音",//IC之音
		   "佳音廣播電台",//佳音廣播電台
		   "佳音電台2台",//佳音電台2台
		   "全國廣播",//全國廣播
		   "台中廣播",//台中廣播
		   "台北廣播電台- 都會資訊頻道",//台北廣播電台- 都會資訊頻道
		   "台北廣播電台喔海洋頻道",//台北廣播電台喔海洋頻道
		   "大千電台",//大千電台
		   "大愛網路電台",//大愛網路電台
		   "宜蘭中山電台",//宜蘭中山電台
		   "環宇廣播",//環宇廣播
		   "真心之音廣播電台",//真心之音廣播電台
		   "青春線上",//青春線上
		   "飛碟電台",//飛碟電台
		   "NEWS98新聞網",//NEWS98新聞網
		   "中廣新聞網",//中廣新聞網
		   "台灣之音-華語",//台灣之音-華語
		   "復興廣播電台 短波網",//復興廣播電台 短波網
		   "復興廣播電台 第一網",//復興廣播電台 第一網
		   "復興廣播電台 第二網",//復興廣播電台 第二網
		   "正聲台北調幅台",//正聲台北調幅台
		   "正聲台北調頻台",//正聲台北調頻台
		   "漢聲光華網-中波",//漢聲光華網-中波
		   "漢聲光華網-短波",//漢聲光華網-短波
		   "漢聲廣播電台",//漢聲廣播電台
		   "漢聲廣播電台全國調頻網",//漢聲廣播電台全國調頻網
		   "綠色和平台灣文化廣播電台",//綠色和平台灣文化廣播電台
		   "ICRT",//ICRT
		   "台灣之音-FM",//台灣之音-FM
		   "台灣之音-亞洲",//台灣之音-亞洲
		   "台灣之音-歐美及方言",//台灣之音-歐美及方言
};
	public StationDB(Context context) {
		super(context, StationDB.db_Name, null, StationDB.db_Version);
		
		
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		
			/*StringBuilder sqlCmdCreate = new StringBuilder();
			sqlCmdCreate.append("CREATE TABLE " + StationDB.db_Table + "(");
			sqlCmdCreate.append("\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT");
			sqlCmdCreate.append(", \"NAME\" TEXT");
			sqlCmdCreate.append(", \"URL\" TEXT NOT NULL");
			sqlCmdCreate.append(", \"COMMENT\" TEXT");
				sqlCmdCreate.append(");");*/
			
			String sqlCmdCreate ="CREATE TABLE " +StationDB.Table_Name + "("
					+"ID INTEGER PRIMARY KEY AUTOINCREMENT"
					+", NAME TEXT"
					+", URL TEXT NOT NULL"
					+", COMMENT TEXT"
					+")";
			Log.d("Jim", sqlCmdCreate);
			db.execSQL(sqlCmdCreate);
			StringBuilder sqlCmdInsert = new StringBuilder();
			
			for(int i=0;i<Station_MmsUrl.length;i++){
				byte[] CodeValue;
				try {
					CodeValue = Station_MmsName[i].getBytes();
					sqlCmdInsert.append("INSERT INTO "+StationDB.Table_Name
							+"(NAME,URL)"
							+"VALUES(\'"+new String(CodeValue, "UTF-8")+"\',\'"+Station_MmsUrl[i]+"\');");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("Jim",sqlCmdInsert.toString());
				db.execSQL(sqlCmdInsert.toString());
				sqlCmdInsert.setLength(0);
			}
			
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS "+StationDB.Table_Name);
		onCreate(db);
	}
	public Cursor ReadData(String ID){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(StationDB.Table_Name, new String[] {"ID", "NAME" ,"URL", "COMMENT"},
				"ID =\'"+ID+"\'", 
				null, null, null, "ID DESC"
				);
		cursor.moveToFirst();
		return cursor;
		
	}
	public long InsertData(String n, String u, String c){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues InsertValues = new ContentValues();
		InsertValues.put("NAME", n);
		InsertValues.put("URL", u);
		InsertValues.put("COMMENT", c);
		return db.insert(StationDB.Table_Name, null, InsertValues);
	}
	public int UpdateData(String id,String n,String u, String c){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues UpdateValues = new ContentValues();
		UpdateValues.put("NAME", n);
		UpdateValues.put("URL", u);
		UpdateValues.put("COMMENT", c);

		return db.update(StationDB.Table_Name, UpdateValues, "ID=\'"+id+"\'", null);
	}
	public int DeleteData(String id) throws UnsupportedEncodingException{
		SQLiteDatabase db = this.getWritableDatabase();
		//byte[] bName = n.getBytes();
		return db.delete(StationDB.Table_Name, "ID=\'"+id/*new String(bName,"UTF-8")*/+"\'", null);
	}
	public Cursor GetAllRecords(){
		SQLiteDatabase db = this.getReadableDatabase();
				
		//return db.rawQuery("SELECT * FROM "+StationDB.Table_Name+";",null); 		
		return db.rawQuery("SELECT * FROM Station_Info;",null);
	}

}
