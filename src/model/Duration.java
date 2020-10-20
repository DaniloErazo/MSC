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
	public int getHour(){
		return hour;
	}
	public int getMinutes(){
		return minutes;
	}
	public int getSeconds(){
		return seconds;
	}
	public void setHour(int hour){
		this.hour=hour;
	}
	public void setMinutes(int minutes){
		this.minutes=minutes;
	}
	public void setSeconds(int seconds){
		this.seconds=seconds;
}
}
