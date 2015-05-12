package com.internetradio;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
//import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

//import io.vov.vitamio.*;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

public class RadioService extends Service {
	//public MediaPlayer mmsPlayer;
	private MediaPlayer mmsPlayer;
	public String MMS_URL;
	//public String MMS_ID;
	AudioManager aManager;
	//AudioManager aManager  = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	private int volume; 
	//int volume = aManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent i, int flags, int startId){
		
		
		try {
			Log.d("Jim","onStartCommand");
			Bundle b = i.getExtras();
			int info = b.getInt("info");// action code, 1 for start, 2 for stop
			
			//Get the MMS_ID from the MainActivity 
			
			if(info == 1){
				
				MMS_URL = i.getExtras().getString("MMS_URL");
				
				
				Log.i("Jim",MMS_URL);
				Play();
			}/*else if(info == 2){
				Stop();
				
			}*/
				
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		/*mmsPlayer.start();*/
			
		//return super.onStartCommand(i, flags, startId);
		return START_STICKY;
	}
	public void Stop(){
		
		mmsPlayer.pause();
		mmsPlayer.release();
		mmsPlayer =null;
		
	}
	public void Play(){
		
			//volume = aManager.getStreamVolume(AudioManager.STREAM_MUSIC);
			//aManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, volume);
			
			
			aManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
			
			mmsPlayer.reset();
			try {
				mmsPlayer.setDataSource(MMS_URL);
				//mmsPlayer.setDataSource("mmsh://bcr.media.hinet.net/RA000001");
				mmsPlayer.prepare();
				aManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
				mmsPlayer.start();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	public void onCreate(){
		super.onCreate();
		
		Vitamio.initialize(RadioService.this);
		mmsPlayer = new MediaPlayer(RadioService.this);
		
		aManager  = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i("Jim","Stopping the radio");
		
		//volume = aManager.getStreamVolume(aManager.STREAM_MUSIC);
		aManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
		mmsPlayer.stop();
		mmsPlayer.release();
		mmsPlayer = null;
		aManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
		//aManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, volume);
	}
}
