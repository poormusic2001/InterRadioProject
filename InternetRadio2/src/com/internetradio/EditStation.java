package com.internetradio;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class EditStation extends Activity{
	
	private TextView eText1;
	private TextView eText2;
	private TextView eText3;
	
	
	private String Station_Url = null; 
	private String Station_Name = null;
	private String Station_Id = null;
	private String Station_Comment=null;
	@Override
	public void onCreate(Bundle InstanceState){
		super.onCreate(InstanceState);
		
		setContentView(R.layout.activity_editstation);
		Button Save_Button =(Button) findViewById(R.id.button10);
        Save_Button.setOnClickListener(SaveListener);
		
        Button Cancel_Button =(Button) findViewById(R.id.button11);
        Cancel_Button.setOnClickListener(CancelListener);
        
		
		Bundle receivedBundle = this.getIntent().getExtras();
		
		Station_Url =receivedBundle.getString("Station_Url");
		Station_Name = receivedBundle.getString("Station_Name");
		Station_Id = receivedBundle.getString("Station_Id");
		StationDB sDB = new StationDB(EditStation.this);
		Cursor c= sDB.ReadData(Station_Id);
		
		c.moveToFirst();
		do{
			Station_Comment = c.getString(3);
		}while(c.moveToNext());
		
		eText1 = (TextView)findViewById(R.id.editText1);//¦WºÙ
		eText2 = (TextView)findViewById(R.id.editText2);//mmsh url
		eText3 = (TextView)findViewById(R.id.editText3);//Comment
		eText1.setText(Station_Name);
		eText2.setText(Station_Url);
		eText3.setText(Station_Comment);
		sDB.close();
	}
	private OnClickListener SaveListener = new OnClickListener()
    {
    	public void onClick(View v){
    		
    		try {
    			Intent SaveIntent = new Intent();
    			SaveIntent.setClass(EditStation.this, MainActivity.class);
    			
    			StationDB sDB = new StationDB(EditStation.this);
    			
    			sDB.UpdateData(Station_Id, eText1.getText().toString(), 
    					eText2.getText().toString(), eText3.getText().toString());
    			if( "".equals(eText1.getText()) || eText1.getText() == null )
    				Toast.makeText(EditStation.this, R.string.Url_Name_Tip, Toast.LENGTH_SHORT).show();
    			else{
    				startActivity(SaveIntent);
    				EditStation.this.finish();
    			}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
    		
    	}
    	
    };
    private OnClickListener CancelListener = new OnClickListener()
    {//Return to the mainActivity
    	public void onClick(View v){
    		
    		//Intent CancelIntent = new Intent();
    		//CancelIntent.setClass(EditStation.this, MainActivity.class);
    		//startActivity(CancelIntent);//·|¦³nullexception
    		EditStation.this.finish();
    	}
    	
    };
}
