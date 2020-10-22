package model;
public class Duration{
	private int hour;
	private int minutes;
	private int seconds;
	
	public Duration(int hour, int minutes, int seconds){
		this.hour=hour;
		this.minutes=minutes;
		this.seconds=seconds;
	}
	
	/**
	 * Returns the hours of duration of a song or playlist  
	 * @return hour hours of duration  
	*/
	public int getHour(){
		return hour;
	}
	/**
	 * Returns the minutes of duration of a song or playlist  
	 * @return minutes minutes of duration  
	*/
	public int getMinutes(){
		return minutes;
	}
	/**
	 * Returns the seconds of duration of a song or playlist  
	 * @return seconds seconds of duration  
	*/
	public int getSeconds(){
		return seconds;
	}
	/**
	* sets the hour of duration of a song or playlist  
	*/
	public void setHour(int hour){
		this.hour=hour;
	}
	/**
	* sets the minutes of duration of a song or playlist  
	*/
	public void setMinutes(int minutes){
		this.minutes=minutes;
	}
	/**
	* sets the seconds of duration of a song or playlist  
	*/
	public void setSeconds(int seconds){
		this.seconds=seconds;
}
}
