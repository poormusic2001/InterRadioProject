package com.internetradio;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;


import io.vov.vitamio.*;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;


public class MainActivity extends Activity {
	//AudioManager aManager  = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	//int volume = aManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	public MediaPlayer mmsPlayer;
	private Spinner s;
	public String MMS_Station_Id;
	public String MMS_Station_Name;
	public String MMS_Station_Url;
	public String MMS_Station_Comment;
	public String UserDefineString=null;
	public StationDB sDB= new StationDB(MainActivity.this);
	public StationInfo sInfo;
	public ArrayAdapter<StationInfo> SourceAdapter; 
	public ArrayList<StationInfo> StationLists = new ArrayList<StationInfo>();
	public TextView CommentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AudioManager aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		//MediaController mmsController = new MediaController(this);
		//this.addContentView(mmsController, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		try {
			s = (Spinner)findViewById(R.id.spinner1);
			
			
			
			
	        
	        Button Start_Button =(Button) findViewById(R.id.button6);
	        Start_Button.setOnClickListener(PlayListener);
	        
	        Button Stop_Button =(Button) findViewById(R.id.button7);
	        Stop_Button.setOnClickListener(StopListener);
	        
	        Button UserAddNewURL_Button = (Button) findViewById(R.id.button3);
	        UserAddNewURL_Button.setOnClickListener(UserAddNewListener);
	        
	        Button Edit_Button = (Button) findViewById(R.id.button5);
	        Edit_Button.setOnClickListener(EditListener);
	        
	        Button Delete_Button = (Button) findViewById(R.id.button4);
	        Delete_Button.setOnClickListener(DeleteListener);
	        
	        CommentView = (TextView) findViewById(R.id.textView3);
	        
			
	        new GetDataFromDataBase().execute("");
	        
	      //ArrayAdapter
			ArrayController(s);
	        SetListener();
	        
	        //mmsPlayer = new MediaPlayer(MainActivity.this);
			//sDB.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
		
	}
	@Override
	public void onResume(){
		super.onResume();
		new GetDataFromDataBase().execute("");
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void ArrayController(Spinner spinner){
    	//new ArrayAdapter();
    	//ArrayAdapter< CharSequence > SourceAdapter = ArrayAdapter.createFromResource(this, R.array.channel_name, android.R.layout.simple_spinner_item);
    	SourceAdapter = new ArrayAdapter<StationInfo>(this, android.R.layout.simple_spinner_item,StationLists);
    	SourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	//spinner.setAdapter(SourceAdapter);
    	s.setAdapter(SourceAdapter);
    }
    private void SetListener(){
    	Log.d("Jim","SetListener");
    	s.setOnItemSelectedListener(getId);
    }
    
    private Spinner.OnItemSelectedListener getId = new Spinner.OnItemSelectedListener(){
    	
    	
		public void onItemSelected(AdapterView parent, View v, int position, long id) {
			
			//MMS_Station_Id = parent.getSelectedItemPosition();
			//MMS_Station_Name = parent.getItemAtPosition(MMS_Station_Id).toString();
			sInfo = (StationInfo)parent.getSelectedItem();
			MMS_Station_Id = sInfo.getId();
			Cursor c = sDB.ReadData(MMS_Station_Id);
			
			
			for(int i=0;i<c.getCount();i++){
				MMS_Station_Name=c.getString(1);//Name
				MMS_Station_Url =c.getString(2);//Url
				MMS_Station_Comment = c.getString(3);//Comment
				
			}
			
			
			Log.d("Jim","Comment:");
			if(MMS_Station_Comment != null){
				Log.d("Jim",MMS_Station_Comment);
				CommentView.setText(MMS_Station_Comment);
			}else{
				CommentView.setText("");
			}
			//Log.d("Jim",MMS_Station_Url);
	    
		}
		
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
    	
    };
    

    private OnClickListener PlayListener = new OnClickListener()
    {
    	public void onClick(View v){
    		
    		try {
    			//aManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, volume);
    			//String value = new GetDataFromYahoo().doInBackground(Yahoo_Weather_Url+Id_CityName[City_Id]);
  
    			new GetMmsFromNet().execute(MMS_Station_Url);
    				
    			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
    		
    	}
    	
    };
    private OnClickListener EditListener = new OnClickListener()
    {

		@Override
		public void onClick(View arg0) {
			
			
			Intent editIntent = new Intent();
			editIntent.setClass(MainActivity.this, EditStation.class);
			Bundle editBundle = new Bundle();
			if(UserDefineString == null || "".equals(UserDefineString)){
				
				editBundle.putString("Station_Url", MMS_Station_Url);
				editBundle.putString("Station_Name", MMS_Station_Name);
				editBundle.putString("Station_Id", MMS_Station_Id);	
				
			}/*else if(UserDefineString.startsWith("mmsh://")){
				
				editBundle.putString("Station_Url", UserDefineString);
				sDB.ReadData(UserDefineString);
			}*/
			
			editIntent.putExtras(editBundle);
			startActivity(editIntent);
		}
    	
    	
    };
    private OnClickListener DeleteListener = new OnClickListener()
    {

		@Override
		public void onClick(View v) {
			
			//Log.d("Jim",MMS_Station_Name);
			try {
				
				sDB.DeleteData(sInfo.getId());
				 
				SourceAdapter.remove(sInfo); 
				new GetDataFromDataBase().execute("");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sDB.close();
		}
    	
    	
    };
/*
    public void Play(View view ) throws IllegalArgumentException, IllegalStateException, IOException{
		new GetMmsFromNet().execute(Station_MmsUrl[MMS_Station_Id]);

    }*/
    private OnClickListener StopListener = new OnClickListener()
    {
    	public void onClick(View v){
    		
    		try {
    			//aManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
    			//String value = new GetDataFromYahoo().doInBackground(Yahoo_Weather_Url+Id_CityName[City_Id]);
    			//new GetMmsFromNet().execute(Station_MmsUrl[MMS_Station_Id]);
    			Intent stopIntent = new Intent(MainActivity.this, RadioService.class);
        		stopIntent.putExtra("info", 2);//2 for stop
    			stopService(stopIntent);
    			//aManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, volume);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
    		
    	}
    	
    };
    private OnClickListener UserAddNewListener = new OnClickListener()
    {
    	public void onClick(View v){
    		Intent AddIntent = new Intent(MainActivity.this, AddStation.class);
    		startActivity(AddIntent);
    		
    	}
    	
    };
    private class GetDataFromDataBase extends AsyncTask<String, Void, String>{
    	@Override
    	protected void onPreExecute(){
    		Cursor SelectCursor = sDB.GetAllRecords();
	        SelectCursor.moveToFirst();
	        do{//ID, Name
	        	sInfo =new StationInfo (SelectCursor.getString(0).toString(), SelectCursor.getString(1).toString());
	        	StationLists.add(sInfo);
	        	//StationLists.add(SelectCursor.getString(1).toString());
	        }while(SelectCursor.moveToNext());
	        if(MMS_Station_Comment != null){
				Log.d("Jim",MMS_Station_Comment);
				CommentView.setText(MMS_Station_Comment);
			}else{
				CommentView.setText("");
			}
    	}
		@Override
		protected String doInBackground(String... arg0) {
			
			
	        
			
			return null;
		}
    	
    	
    	
    	
    }
    private class GetMmsFromNet  extends AsyncTask<String, Void, String> {
    	String temp=null;
    	//TextView tview, tview_WaitMessage;
    	//Toast waitMessage = Toast.makeText(MainActivity.this, R.string.network_notconnetced, Toast.LENGTH_SHORT);
		
    	@Override
    	protected void onPreExecute(){
    		super.onPreExecute();
    		
    		Toast.makeText(MainActivity.this, R.string.wait_message, Toast.LENGTH_LONG).show();
    	}
    	@Override
    	protected String doInBackground(String... arg0) {
    		Intent startIntent = new Intent(MainActivity.this, RadioService.class);
			Bundle bData = new Bundle();
			showNotification(arg0[0]);
			bData.putString("MMS_URL", arg0[0]/*Station_MmsUrl[MMS_Station_Id]*/);
			startIntent.putExtras(bData);
			startIntent.putExtra("info", 1);//1 for play
			 Log.d("Jim","StartIntent...before");
			startService(startIntent);
    		
    		
			return temp;
    		
    	}
    	@Override
    	protected void onPostExecute(String unused){
    		
    	}
    	
    	
    	
    }
    //@TargetApi(16)
    private void showNotification(String URL_INFO){
    	NotificationManager notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    	//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
    	//Notification notiMsg = new Notification(R.drawable.ic_launcher,R.string.Now_Playing+URL_INFO,System.currentTimeMillis());
    	Notification notiMsg = new NotificationCompat.Builder(MainActivity.this) 
    		.setContentTitle(MMS_Station_Name+" 播放中")
    		.setContentText("您正在聆聽 "+MMS_Station_Name)
    		.setSmallIcon(R.drawable.ic_launcher).build();
    	
    	notiManager.notify(0, notiMsg);
    	
    }
    
}
