package model;
import model.User;
import model.Song;
import model.Playlist;
import model.RestrictedPlaylist;
public class MSC{
	public final static int MAX_USERS= 10;
	public final static int MAX_PLAYLIST= 20;
	public final static int MAX_SONGS= 30;
	private User[] users;
	private Song[] songs;
	private Playlist[] playlists;
	private int numSongs;
	private int numUsers;
	private int numPlaylists;
	
	/**
	 * Constructor that initializes three arrays: users, songs and playlists 
	 * and three attributes for the total amount of users, songs and playlists in the MSC 
	 */
	public MSC(){
	this.users = new User[MAX_USERS];
	this.songs = new Song[MAX_SONGS];
	this.playlists = new Playlist[MAX_PLAYLIST];
	this.numUsers=0;
	this.numSongs=0;
	this.numPlaylists=0;
	}
	
	/**
	 * Method that checks if there's space for more users
	 */
	public boolean spaceAvailableUser(){
		return numUsers<MAX_USERS;
	}
	
	/**
	 * Method that checks if there's space for more songs
	 */
	public boolean spaceAvailableSong(){
		return numUsers<MAX_SONGS;
	}
	
	/**
	 * Method that checks if there's space for more playlists
	 */
	public boolean spaceAvailablePlaylist(){
		return numUsers<MAX_PLAYLIST;
	}
	
	public Playlist getPlaylist(int index){
		return playlists[index];
	}
	
	public User getUser(int index){
		return users[index];
	}
	
	/**
	 * Method that adds a new user <br>
	 * <b> pre: </b> array users is initialized. There isn't another user with the same nickname. There are empty indexes <br>  
	 * <b> pos: </b> numUsers++, users has a new object in the first empty index found <br>
	 * @param nickname is the identifier for the user. nickname !=null . nickname!= "" <br>
	 * @param password user's password. password != null. password != "" <br>
	 * @param age the user's age. age>0 <br>
	 * @param uploadedSongs is the quantity of songs uploaded to the pool, by default it starts in 0<br>
	 */
	public void addUser(String nickname, String password, int age, int uploadedSongs){
		int emptyIndex=0; 
		boolean emptyFound=false;
		for (int i=0; i<users.length && !emptyFound ; i++){
			if(users[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		users[emptyIndex]= new User(nickname, password, age, uploadedSongs);
		numUsers++;
	}
	
	
	/**
	 * Method that adds a new song to the pool <br>
	 * <b> pre: </b> array songs is initialized. There isn't another song with the same title. There are empty indexes <br>  
	 * <b> pos: </b> numSongs++, songs has a new object in the first empty index found <br>
	 * @param title is the name of the song. title !=null . title!= "" <br>
	 * @param artist is the name of the perfomer of the song. artist !=null . artist!= "" <br>
	 * @param releaseDate is the release date of the song in format DD/MM/YYYY. releaseDate != null. releaseDate != "" <br>
	 * @param duration is the duration of the song in format HH:MM:SS. duration!=null, duration!="" <br>
	 * @param genre is the genre which the song belongs to, it's one the options in Genre class<br>
	 */
	public void addSong(String title, String artist, String releaseDate, String duration, String genre){
		Genre aGenre= Genre.valueOf(genre);
		String[] durationParts = duration.split(":");
		int hour = Integer.parseInt(durationParts[0]);
		int minutes = Integer.parseInt(durationParts[1]);
		int seconds = Integer.parseInt(durationParts[2]);
		Duration aDuration = new Duration(hour, minutes, seconds);
		int emptyIndex=0; 
		boolean emptyFound=false;
		for (int i=0; i<songs.length && !emptyFound ; i++){
			if(songs[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		songs[emptyIndex]= new Song(title, artist, releaseDate, aDuration, aGenre);
		numSongs++;
	}
	
	public void addPublicPlaylist(String name){
		int emptyIndex=0; 
		boolean emptyFound=false;
		for (int i=0; i<MAX_PLAYLIST && !emptyFound ; i++){
			if(playlists[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		playlists[emptyIndex] = new PublicPlaylist(name);
	}
	
	public void addPrivatePlaylist(String name, int userIndex){
		int emptyIndex=0; 
		boolean emptyFound=false;
		User userOwner = users[userIndex];
		for (int i=0; i<MAX_PLAYLIST && !emptyFound ; i++){
			if(playlists[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		playlists[emptyIndex] = new PrivatePlaylist(name, userOwner);
	}
	
	public void addRestrictedPlaylist(String name, int userIndex){
		int emptyIndex=0; 
		boolean emptyFound=false;
		User userOwner = users[userIndex];
		for (int i=0; i<MAX_PLAYLIST && !emptyFound ; i++){
			if(playlists[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		playlists[emptyIndex] = new RestrictedPlaylist(name, userOwner);
	}
	public void addAuthorizedUserRestricted(int playlistPosition, int userIndex){
		RestrictedPlaylist auxiliar = (RestrictedPlaylist)playlists[playlistPosition];
		User newUser = users[userIndex];
		auxiliar.addAuthorizedUser(newUser);
		
	}
	public boolean checkRepeatedUser(String nickname, int position){
		RestrictedPlaylist auxiliar = (RestrictedPlaylist)playlists[position];
		boolean repeated = auxiliar.repeatedAuthorizedUser(nickname);
		return repeated;
	}
	public boolean findAuthorizedUser(String nickname, int position){
		RestrictedPlaylist auxiliar = (RestrictedPlaylist)playlists[position];
		boolean found = auxiliar.findAuthorizedUser(nickname);
		
		return found;
	}
	/**
	 * showUsers method that returns the information of all the users in the MSC <br>
	 * @return usersListInfo, String with all the information
	 */
	public String showUsers(){
		String usersListInfo="";
		for (int i=0; i<MAX_USERS; i++){
			if(users[i]!=null){
				usersListInfo+= users[i].toString();
			}
		}
		return usersListInfo;
	}
	/**
	 * Method that adds a new song <br>
	 * <b> pre: </b> array songs is initialized. There isn't another song with the same title. There are empty indexes <br> 
	 * <b> pos: </b> numSongs++, songs has a new object in the first empty index found <br>
	 * @param title is the name of the song. title !=null . title!= "" <br>
	 * @param artist the singer or band that performs the song. artist != null. artist != "" <br>
	 * @param releaseDate the date when the song was published <br>
	 * @param duration <br>
	 * @param genre <br>
	 */
	public void addSong(String title, String artist, String releaseDate, Duration duration, Genre genre){
		int emptyIndex=0; 
		boolean emptyFound=false;
		for (int i=0; i<songs.length && !emptyFound ; i++){
			if(songs[i]==null){
				emptyIndex=i;
				emptyFound=true;
			}
		}
		songs[emptyIndex]= new Song(title, artist, releaseDate, duration, genre);
		numUsers++;
	}
	
	/**
	 * findUser is a method that informs if an user's nickname already exists 
	 * @param nickname is the nickname to look for 
	 * @return found if it exists, it's true and if it doesn't it's false  //!!!!!
	 */
	public int[] findUser(String nickname) { 
		int[] userFoundData = new int[2];
		int found=0;
		int index=0;
		for (int i=0; i<MAX_USERS && found!=1; i++){
			User userAux= users[i];
			if(userAux!=null && userAux.getNickname().equals(nickname)){
				found=1;
				index=i;
			}
		}
		userFoundData[0]=found;
		userFoundData[1]=index;
		return userFoundData;
	}
	/**
	 * findSong is a method that informs if a song's title already exists 
	 * @param title is the song to look for 
	 * @return found if it exists, it's true and if it doesn't it's false 
	 */
	public boolean findSong(String title) { 
		boolean found=false;
		for (int i=0; i<MAX_SONGS && !found; i++){
			Song songAux= songs[i];
			if(songAux!=null && songAux.getTitle().equals(title)){
				found=true;
			}
		}
		return found;
	}
	
	public int[] findPlaylist(String name) { 
		int[] playlistFoundData = new int[2];
		int found=0;
		int index=0;
		for (int i=0; i<MAX_PLAYLIST && found!=1; i++){
			Playlist playlistAux= playlists[i];
			if(playlistAux!=null && playlistAux.getName().equals(name)){
				found=1;
				index=i;
			}
		}
		playlistFoundData[0]=found;
		playlistFoundData[1]=index;
		return playlistFoundData;
	}
	
	public boolean findPlaylistType(int type, int position){
		boolean typeFound=false;
		
		/*if(type==1){
			if(playlists[position] instanceof PublicPlaylist){
				typeFound=true;
			}
		}else if(type==2){
			if(playlists[position] instanceof PrivatePlaylist){
				typeFound=true;
			}
		}else if (type==3){
			if(playlists[position] instanceof RestrictedPlaylist){
				typeFound=true;
			}
		}
		*/
		
		return (playlists[position] instanceof RestrictedPlaylist);
	}
	
	/**
	 * showSongs method that returns the information of all the songs in the MSC <br>
	 * @return songsListInfo, String with all the information
	 */
	public String showSongs(){
		String songsListInfo="";
		for (int i=0; i<MAX_SONGS; i++){
			if(songs[i]!=null){
				songsListInfo+= songs[i].toString();
			}
		}
		return songsListInfo;
	}
	
	/**
	 * showPlaylists method that returns the information of all the playlists in the MSC <br>
	 * @return playlistsInfo, String with all the information
	 */
	public String showPlaylists(){
		String playlistsInfo="";
		for (int i=0; i<MAX_PLAYLIST; i++){
			if(playlists[i]!=null){
				playlistsInfo+= playlists[i].toString();
			}
		}
		return playlistsInfo;
	}
}

