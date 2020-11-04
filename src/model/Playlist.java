package model;
public class Playlist{
	protected String name;
	protected String genre;
	protected Song[] songsInPlaylist;
	
	//Relations
	protected Duration durationP;
	
	/**
	 * Constructor that creates a new playlist with duration 0:0:0, empty genre and an empty array for the songs
	 */
	public Playlist(String name){
	this.name=name;
	durationP= new Duration(0,0,0);
	songsInPlaylist= new Song[30];
	genre="";
	}

	public String getGenre(){
		return genre;
	}
	
	public String getName(){
		return name;
	}
	
	public void setGenre(String genre){
		this.genre=genre;
	}
	
	public void setDurationP(Duration duration){
		this.durationP=duration;
	}
	
	
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