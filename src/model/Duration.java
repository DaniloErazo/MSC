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
	* @param hour new duration of hour 
	*/
	public void setHour(int hour){
		this.hour=hour;
	}
	/**
	* sets the minutes of duration of a song or playlist  
	* @param minutes new duration for minutes
	*/
	public void setMinutes(int minutes){
		this.minutes=minutes;
	}
	/**
	* sets the seconds of duration of a song or playlist
	* @param seconds new duration for seconds	
	*/
	public void setSeconds(int seconds){
		this.seconds=seconds;
	}
	
	/**
	 * Method that adjusts time so minutes and seconds don't overpass 60 <br>
	 * <b> pre: </b> <br>  
	 * <b> pos: </b> <br>
	 * @param hour is the number of hours. hour &gt; 0 <br>
	 * @param minute is the number of minutes. minute &gt; 0 <br>
	 * @param second is the number of seconds. second &gt; 0 <br>
	 * @return durationPartsAdjusted which is an array that in its firt position contains seconds,
	 * in the second minutes and in the last one hours 
	 */
	public int[] adjustDuration(int hour, int minute, int second){
		int[] durationPartsAdjusted = new int[3];
		int minuteAux=0;
		int hourAux=0;
		if(second>59){
			minuteAux=second/60;
			second= second%60;
		}
		if(minute>59){
			hourAux=minute/60;
			minute= minute%60;
		}
		minute= minute+minuteAux;
		hour= hour+hourAux;
		durationPartsAdjusted[0]=second;
		durationPartsAdjusted[1]=minute;
		durationPartsAdjusted[2]=hour;
		
		return durationPartsAdjusted;
	}
}
