package model;
public class Playlist{
	private String name;
	private String genre;
	
	
	//Relations
	private Duration duration;
	
	/**
	 * Constructor that creates a new playlist with duration 0:0:0 and empty genre 
	 */
	public Playlist(String name){
	this.name=name;
	duration= new Duration(0,0,0);
	genre="";
	}

	public String getGenre(){
		return genre;
	}
	
	public void setGenre(String genre){
		this.genre=genre;
	}
	
	/**
	 * Method that produces a String with the information of a playlist
	 */
	public String toString(){
		String infoSong= "*************  Playlist **************/n"+
		"**  Title: " + name + "/n"+
		"**  Duration: " + duration.getHour() + ":" + duration.getMinutes() + ":" + duration.getSeconds() + "/n"+
		"**  Genre: " + genre + "/n"+
		"***********************************";
		return infoSong;
	}
}