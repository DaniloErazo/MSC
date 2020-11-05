package model;
public class Playlist{
	protected String name;
	protected String genre;
	protected Song[] songsInPlaylist;
	protected int song;
	
	//Relations
	protected Duration durationP;
	
	/**
	 * Constructor that creates a new playlist with duration 0:0:0, 
	 * empty genre, an empty array for the songs and a counter for the added songs
	 * @param name playlist name
	 */
	public Playlist(String name){
		this.name=name;
		durationP= new Duration(0,0,0);
		songsInPlaylist= new Song[30];
		genre="";
		song=0;
	}
	
	/**
	 * Returns the genre of the playlist 
	 * @return genre which is a String with the genres contained  
	*/
	public String getGenre(){
		return genre;
	}
	
	/**
	 * Returns name of the playlist 
	 * @return name playlist name  
	*/
	public String getName(){
		return name;
	}
	
	/**
	* sets the genre of the playlist
	* @param genre is a String with the genres contained
	*/
	public void setGenre(String genre){
		this.genre=genre;
	}
	
	/**
	* sets the duration of the playlist
	* @param duration is the playlist duration
	*/
	public void setDurationP(Duration duration){
		this.durationP=duration;
	}
	
	/**
	 * addSong is a method that adds a song to the playlist. It updates the genre and the duration of the playlist <br>
	 * <b> pre: </b> songs array is initialized. There is free indexes in the array<br>  
	 * <b> pos: </b> song++. There's a new object in the songs array in the first empty found position. genre and durationP are updated <br>
	 * @param newSong is the song to be added
	 * @param genre is the playlist genre before adding the new song. genre!=null. genre!=""  
	 */
	public void addSong(Song newSong, String genre){
		boolean emptyFound=false;
		int emptyIndex=0;
		for (int i=0; i<songsInPlaylist.length && !emptyFound ; i++){
			if(songsInPlaylist[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		songsInPlaylist[emptyIndex]= newSong; //Add song to array 
		song++;
		Duration auxiliar = newSong.getDuration(); //auxiliar variable to obtain song duration
		int songHour=auxiliar.getHour();
		int songMinute=auxiliar.getMinutes();
		int songSeconds=auxiliar.getSeconds();
		
		durationP.setHour(durationP.getHour()+songHour); //Add song duration to plyalist duration
		durationP.setMinutes(durationP.getMinutes()+songMinute);
		durationP.setSeconds(durationP.getSeconds()+songSeconds);
		
		int[] adjusted= durationP.adjustDuration(durationP.getHour(),durationP.getMinutes(),durationP.getSeconds()); //adjust playlist duration and set 
		durationP.setHour(adjusted[2]);
		durationP.setMinutes(adjusted[1]);
		durationP.setSeconds(adjusted[0]);
		
		setGenre(updateGenre(genre));
	}
	
	/**
	 * updateGenre is a method that updates the genre of the playlist if it's needed <br>
	 * <b> pre: </b> <br>  
	 * <b> pos: </b> genre is updated<br>
	 * @param genre is the genre before updating
	 * @return genre is a String with a new genre added if needed  
	 */
	public String updateGenre(String genre){
		String genreAux="";
		for(int i=0; i<songsInPlaylist.length; i++){
			if(songsInPlaylist[i]!=null){
				genreAux = songsInPlaylist[i].getGenre().name();
				if(!genre.contains(genreAux)){
					genre+=", " +genreAux;
				}
			}
		}
		return genre;
	}
	
	/**
	 * Method that produces a String with the information of a playlist
	 * @return infoPlaylist String with playlist information
	 */
	public String infoPlaylist(){
		String infoPlaylist= "*************  Playlist **************\n"+
		"**  Title: " + name + "\n"+
		"**  Duration: " + durationP.getHour() + ":" + durationP.getMinutes() + ":" + durationP.getSeconds() + "\n"+
		"**  Genre: " + genre + "\n"+
		"***********************************\n";
		return infoPlaylist;
	}
}