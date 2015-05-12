package com.internetradio;

public class StationInfo {
	private String Station_Id;
	private String Station_Name;
	
	public StationInfo(String id, String name){
		
		Station_Id = id;
		Station_Name = name;
		
	}
	@Override
	public String toString(){
		return Station_Name;
	}
	public String getId(){
		
		return Station_Id;
	}
	public String getName(){
		
		return Station_Name;
	}
	
}
