package model;
public class Song{
	private String title;
	private String artist;
	private String releaseDate;
	
	//Relations
	private Duration duration;
	private Genre genre;
	
	/**
	 * Constructor for a song with all its information
	 * Qparam title is the name of the song
	 * @param artist is the singer or band that sings the song
	 * @param releaseDate is the date when the song was released to the public
	 * @param duration duration of the song in HH:MM:SS
	 * @param genre is the genre to which the song belongs, from a defined list of genres
	 */
	public Song(String title, String artist, String releaseDate, Duration duration, Genre genre){
		this.title=title;
		this.artist=artist;
		this.releaseDate=releaseDate;
		this.duration=duration;
		this.genre=genre;
	}
	/**
	 * Returns the name of the song  
	 * @return title song's name  
	*/
	public String getTitle(){
		return title;
	}
	
	public Duration getDuration(){
		return duration;
	}
	
	public Genre getGenre(){
		return genre;
	}
	
	/**
	 * Method that produces a String with the some of the information of a song 
	 */
	public String toString(){
		String infoSong= "*************  Song **************\n"+
		"**  Title: " + title + "\n"+
		"**  Artist: " + artist + "\n"+
		"**  Duration: " + duration.getHour() + ":" + duration.getMinutes() + ":" + duration.getSeconds() + "\n"+
		"**  Genre: " + genre + "\n"+
		"***********************************\n";
		return infoSong;
	}
}
