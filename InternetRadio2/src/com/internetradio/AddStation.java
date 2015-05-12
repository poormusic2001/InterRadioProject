package com.internetradio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddStation extends Activity {
	public StationDB sDB = new StationDB(AddStation.this);
	
	private TextView eText4;
	private TextView eText5;
	private TextView eText6;
	
	
	@Override
	public void onCreate(Bundle InstanceState){
		super.onCreate(InstanceState);
		setContentView(R.layout.activity_addstation);
		
		Button Save_Button =(Button) findViewById(R.id.button8);
		
		
		Save_Button.setOnClickListener(SaveListener);
		
		
        Button Cancel_Button =(Button) findViewById(R.id.button9);
        Cancel_Button.setOnClickListener(CancelListener);
        
        eText4 = (TextView)findViewById(R.id.editText4);
		eText5 = (TextView)findViewById(R.id.editText5);
		eText6 = (TextView)findViewById(R.id.editText6);
        
		
		
		
	}
	private OnClickListener SaveListener = new OnClickListener()
    {
    	public void onClick(View v){
    		
    		try {
    			//Intent SaveIntent = new Intent();
    			//SaveIntent.setClass(AddStation.this, MainActivity.class);
    			
    			
    			sDB.InsertData(eText4.getText().toString(), eText5.getText().toString(), eText6.getText().toString());
    			if( "".equals(eText4.getText()) || eText4.getText() == null )
    				Toast.makeText(AddStation.this, R.string.Url_Name_Tip, Toast.LENGTH_SHORT).show();
    			else{
    				
    				//startActivity(SaveIntent);
    				AddStation.this.finish();
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
    		//CancelIntent.setClass(AddStation.this, MainActivity.class);
    		//startActivity(CancelIntent);
    		AddStation.this.finish();
    	}
    	
    };
}
