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
		   "mmsh://bcr.media.hinet.net/RA000004",//ASIAFM�ìP���֥x
		   "mmsh://bcr.media.hinet.net/RA000001",//Apple line ī�G�u�W
		   "mmsh://bcr.media.hinet.net/RA000010",//BestRadio �x���n��
		   "mmsh://bcr.media.hinet.net/RA000013",//BestRadio �x�_�n��
		   "mmsh://bcr.media.hinet.net/RA000011",//BestRadio �Ὤ�n��
		   "mmsh://bcr.media.hinet.net/RA000012",//BestRadio �����䳣
		   "mmsh://bcr.media.hinet.net/RA000019",//Flyradio�������W
		   "mmsh://bcr.media.hinet.net/RA000041",//KISS RADIO �j�]�߼s��
		   "mmsh://bcr.media.hinet.net/RA000040",//KISS RADIO�j���s���q�x
		   "mmsh://bcr.media.hinet.net/RA000042",//KISSRadio �������֥x
		   "mmsh://bcr.media.hinet.net/RA000080",//POP Radio
		   "mmsh://bcr.media.hinet.net/RA000005",//���s�j���
		   "mmsh://bcr.media.hinet.net/RA000009",//���s�y���
		   "mmsh://bcr.media.hinet.net/RA000007",//���s���ֺ�i radio
		   "mmsh://bcr.media.hinet.net/RA000031",//�έ�CCM
		   "mmsh://bcr.media.hinet.net/RA000047",//����s�����ֺ�
		   "mmsh://bcr.media.hinet.net/RA000035",//�x�_����HitFM�p���� �x��
		   "mmsh://bcr.media.hinet.net/RA000036",//�x�_����HitFM�p���� �x�_
		   "mmsh://bcr.media.hinet.net/RA000034",//�x�_����HitFM�p���� ����
		   "mmsh://bcr.media.hinet.net/RA000018",//�x�_�R��
		   "mmsh://bcr.media.hinet.net/RA000061",//�x�W����-����
		   "mmsh://bcr.media.hinet.net/RA000064",//�Ӷ��q�x
		   "mmsh://bcr.media.hinet.net/RA000014",//�_���j�孵�ֺ�
		   "mmsh://bcr.media.hinet.net/RA000066",//�_�q�s�n�s���q�x
		   "mmsh://bcr.media.hinet.net/RA000002",//ASIA FM92.3�Ȥӹq�x
		   "mmsh://bcr.media.hinet.net/RA000003",//ASIA FM92.7�Ȧ{�q�x
		   "mmsh://bcr.media.hinet.net/RA000028",//GOLD FM-�x�������s��
		   "mmsh://bcr.media.hinet.net/RA000027",//GOLD FM-�x�_���d�q�x
		   "mmsh://bcr.media.hinet.net/RA000037",//IC����
		   "mmsh://bcr.media.hinet.net/RA000029",//�έ��s���q�x
		   "mmsh://bcr.media.hinet.net/RA000030",//�έ��q�x2�x
		   "mmsh://bcr.media.hinet.net/RA000068",//����s��
		   "mmsh://bcr.media.hinet.net/RA000046",//�x���s��
		   "mmsh://bcr.media.hinet.net/RA000070",//�x�_�s���q�x- ���|��T�W�D
		   "mmsh://bcr.media.hinet.net/RA000069",//�x�_�s���q�x����v�W�D
		   "mmsh://bcr.media.hinet.net/RA000067",//�j�d�q�x
		   "mmsh://bcr.media.hinet.net/RA000017",//�j�R�����q�x
		   "mmsh://bcr.media.hinet.net/RA000065",//�y�����s�q�x
		   "mmsh://bcr.media.hinet.net/RA000020",//���t�s��
		   "mmsh://bcr.media.hinet.net/RA000033",//�u�ߤ����s���q�x
		   "mmsh://bcr.media.hinet.net/RA000079",//�C�K�u�W
		   "mmsh://bcr.media.hinet.net/RA000072",//���йq�x
		   "mmsh://bcr.media.hinet.net/RA000073",//NEWS98�s�D��
		   "mmsh://bcr.media.hinet.net/RA000008",//���s�s�D��
		   "mmsh://bcr.media.hinet.net/RA000063",//�x�W����-�ػy
		   "mmsh://bcr.media.hinet.net/RA000026",//�_���s���q�x �u�i��
		   "mmsh://bcr.media.hinet.net/RA000024",//�_���s���q�x �Ĥ@��
		   "mmsh://bcr.media.hinet.net/RA000025",//�_���s���q�x �ĤG��
		   "mmsh://bcr.media.hinet.net/RA000015",//���n�x�_�մT�x
		   "mmsh://bcr.media.hinet.net/RA000016",//���n�x�_���W�x
		   "mmsh://bcr.media.hinet.net/RA000075",//�~�n���غ�-���i
		   "mmsh://bcr.media.hinet.net/RA000077",//�~�n���غ�-�u�i
		   "mmsh://bcr.media.hinet.net/RA000074",//�~�n�s���q�x
		   "mmsh://bcr.media.hinet.net/RA000076",//�~�n�s���q�x������W��
		   "mmsh://bcr.media.hinet.net/RA000032",//���M���x�W��Ƽs���q�x
		   "mmsh://bcr.media.hinet.net/RA000038",//ICRT
		   "mmsh://bcr.media.hinet.net/RA000060",//�x�W����-FM
		   "mmsh://bcr.media.hinet.net/RA000059",//�x�W����-�Ȭw
		   "mmsh://bcr.media.hinet.net/RA000062",//�x�W����-�ڬ��Τ訥
};
	
	private static final String[] Station_MmsName={
		   "ASIAFM�ìP���֥x",//ASIAFM�ìP���֥x
		   "Apple line ī�G�u�W",//Apple line ī�G�u�W
		   "BestRadio �x���n��",//BestRadio �x���n��
		   "BestRadio �x�_�n��",//BestRadio �x�_�n��
		   "BestRadio �Ὤ�n��",//BestRadio �Ὤ�n��
		   "BestRadio �����䳣",//BestRadio �����䳣
		   "Flyradio�������W",//Flyradio�������W
		   "KISS RADIO �j�]�߼s��",//KISS RADIO �j�]�߼s��
		   "KISS RADIO�j���s���q�x",//KISS RADIO�j���s���q�x
		   "KISSRadio �������֥x",//KISSRadio �������֥x
		   "POP Radio",//POP Radio
		   "���s�j���",//���s�j���
		   "���s�y���",//���s�y���
		   "���s���ֺ�i radio",//���s���ֺ�i radio
		   "�έ�CCM",//�έ�CCM
		   "����s�����ֺ�",//����s�����ֺ�
		   "�x�_����HitFM�p���� �x��",//�x�_����HitFM�p���� �x��
		   "�x�_����HitFM�p���� �x�_",//�x�_����HitFM�p���� �x�_
		   "�x�_����HitFM�p���� ����",//�x�_����HitFM�p���� ����
		   "�x�_�R��",//�x�_�R��
		   "�x�W����-����",//�x�W����-����
		   "�Ӷ��q�x",//�Ӷ��q�x
		   "�_���j�孵�ֺ�",//�_���j�孵�ֺ�
		   "�_�q�s�n�s���q�x",//�_�q�s�n�s���q�x
		   "ASIA FM92.3�Ȥӹq�x",//ASIA FM92.3�Ȥӹq�x
		   "ASIA FM92.7�Ȧ{�q�x",//ASIA FM92.7�Ȧ{�q�x
		   "GOLD FM-�x�������s��",//GOLD FM-�x�������s��
		   "GOLD FM-�x�_���d�q�x",//GOLD FM-�x�_���d�q�x
		   "IC����",//IC����
		   "�έ��s���q�x",//�έ��s���q�x
		   "�έ��q�x2�x",//�έ��q�x2�x
		   "����s��",//����s��
		   "�x���s��",//�x���s��
		   "�x�_�s���q�x- ���|��T�W�D",//�x�_�s���q�x- ���|��T�W�D
		   "�x�_�s���q�x����v�W�D",//�x�_�s���q�x����v�W�D
		   "�j�d�q�x",//�j�d�q�x
		   "�j�R�����q�x",//�j�R�����q�x
		   "�y�����s�q�x",//�y�����s�q�x
		   "���t�s��",//���t�s��
		   "�u�ߤ����s���q�x",//�u�ߤ����s���q�x
		   "�C�K�u�W",//�C�K�u�W
		   "���йq�x",//���йq�x
		   "NEWS98�s�D��",//NEWS98�s�D��
		   "���s�s�D��",//���s�s�D��
		   "�x�W����-�ػy",//�x�W����-�ػy
		   "�_���s���q�x �u�i��",//�_���s���q�x �u�i��
		   "�_���s���q�x �Ĥ@��",//�_���s���q�x �Ĥ@��
		   "�_���s���q�x �ĤG��",//�_���s���q�x �ĤG��
		   "���n�x�_�մT�x",//���n�x�_�մT�x
		   "���n�x�_���W�x",//���n�x�_���W�x
		   "�~�n���غ�-���i",//�~�n���غ�-���i
		   "�~�n���غ�-�u�i",//�~�n���غ�-�u�i
		   "�~�n�s���q�x",//�~�n�s���q�x
		   "�~�n�s���q�x������W��",//�~�n�s���q�x������W��
		   "���M���x�W��Ƽs���q�x",//���M���x�W��Ƽs���q�x
		   "ICRT",//ICRT
		   "�x�W����-FM",//�x�W����-FM
		   "�x�W����-�Ȭw",//�x�W����-�Ȭw
		   "�x�W����-�ڬ��Τ訥",//�x�W����-�ڬ��Τ訥
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
