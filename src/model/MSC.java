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
	 * @return true if there's space, otherwise false
	 */
	public boolean spaceAvailableUser(){
		return numUsers<MAX_USERS;
	}
	
	/**
	 * Method that checks if there's space for more songs
	 * @return true if there's space, otherwise false
	 */
	public boolean spaceAvailableSong(){
		return numUsers<MAX_SONGS;
	}
	
	/**
	 * Method that checks if there's space for more playlists
	 * @return true if there's space, otherwise false
	 */
	public boolean spaceAvailablePlaylist(){
		return numUsers<MAX_PLAYLIST;
	}
	
	/**
	 * Method that returns a specific playlist of the playlists array
	 * @param index it's the position where the asked playlist is 
	 * @return playlists[index] the playlist in the given position 
	 */
	public Playlist getPlaylist(int index){
		return playlists[index];
	}
	
	/**
	 * Method that returns a specific user of the array users
	 * @param index index it's the position where the asked user is 
	 * @return users[index] the user in the given position 
	 */
	public User getUser(int index){
		return users[index];
	}
	
	/**
	 * Method that adds a new user <br>
	 * <b> pre: </b> array users is initialized. There isn't another user with the same nickname. There are empty indexes <br>  
	 * <b> pos: </b> numUsers++, users has a new object in the first empty index found <br>
	 * @param nickname is the identifier for the user. nickname !=null . nickname!= "" <br>
	 * @param password user's password. password != null. password != "" <br>
	 * @param age the user's age. age&gt;0 <br>
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
	
	/**
	 * Method that adds a public playlist  <br>
	 * <b> pre: </b> playlists array is initialized. There isn't another playlist with the same name. There are empty indexes <br>  
	 * <b> pos: </b> numPlaylists++, playlists has a new object of PublicPlaylist type in the first empty index found <br>
	 * @param name is the name of the playlist. title !=null . title!= "" <br>
	 */
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
		numPlaylists++;
	}
	
	/**
	 * Method that adds a private playlist  <br>
	 * <b> pre: </b> playlists array is initialized. There isn't another playlist with the same name. There are empty indexes <br>  
	 * <b> pos: </b> numPlaylists++, playlists has a new object of PrivatePlaylist type in the first empty index found <br>
	 * @param name is the name of the playlist. title !=null . title!= "" <br>
	 * @param userIndex is the position in the array users where the authorized user is. userIndex &ge; 0 
	 */
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
	
	/**
	 * Method that adds a restricted playlist  <br>
	 * <b> pre: </b> playlists array is initialized. There isn't another playlist with the same name. There are empty indexes <br>  
	 * <b> pos: </b> numPlaylists++, playlists has a new object of PrivatePlaylist type in the first empty index found <br>
	 * @param name is the name of the playlist. title !=null . title!= "" <br>
	 * @param userIndex is the position in the array users where the main authorized user is . userIndex &ge; 0
	 */
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
	
	/**
	 * Method that adds a new authorized user to a restricted playlist  <br>
	 * <b> pre: </b> playlists array and users are initialized. The playlist and the user exist. The new user  isn't an authorized user yet <br>  
	 * <b> pos: </b> <br>
	 * @param playlistPosition is the position in the playlists array where the given playlist is. playlistPosition &ge; 0 <br>
	 * @param userIndex is the position in the array users where the new authorized user is. userIndex &gt; 0  
	 */
	 //AÑADIR VALIDACIÓN DE ESPACIO SUFICIENTEEEEEEEEE
	public void addAuthorizedUserRestricted(int playlistPosition, int userIndex){
		RestrictedPlaylist auxiliar = (RestrictedPlaylist)playlists[playlistPosition];
		User newUser = users[userIndex];
		auxiliar.addAuthorizedUser(newUser);
		
	}
	
	/**
	 * Method that checks if the given user is an authorized user in a restricted playlist  <br>
	 * <b> pre: </b> playlists array is initialized. The playlist and the user exist. The playlist is indeed restricted <br>  
	 * <b> pos: </b> <br>
	 * @param nickname is user's nickname to check existence. nickname!=null. nickname!="" <br>
	 * @param position is the position in the playlists array where the playlist to be consulted is. userIndex &gt; 0
	 * @return found which is true if the user is one of the authorized users and false if it isn't
	 */
	public boolean findAuthorizedUser(String nickname, int position){
		RestrictedPlaylist auxiliar = (RestrictedPlaylist)playlists[position];
		boolean found = false;
		found= auxiliar.findAuthorizedUser(nickname);
		
		return found;
	}
	
	/**
	 * Method that checks if the given user is the authorized user of a private playlist <br>
	 * <b> pre: </b> playlists array is initialized. The playlist and the user exist. The playlist is indeed private <br>  
	 * <b> pos: </b> <br>
	 * @param nickname is user's nickname to check. nickname!=null. nickname!="" <br>
	 * @param position is the position in the playlists array where the playlist to be consulted is. userIndex &gt; 0  
	 * @return owner which is true if the user is the owner and false if it isn't
	 */
	public boolean checkOwner(String nickname, int position){
		PrivatePlaylist auxiliar = (PrivatePlaylist)playlists[position];
		boolean owner=false;
		owner= auxiliar.checkOwner(nickname);
		return owner;
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
	 * findUser is a method that informs if an user's nickname already exists and in which index is located
	 * @param nickname is the nickname to look for 
	 * @return userFoundData it's an array, in its first position it's either 1 or 0. 1 for found, 0 for not found
	 * and in it's second position it's the index where the user is found, by default is 0
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
	 * @return songFoundData it's an array, in its first position it's either 1 or 0. 1 for found, 0 for not found
	 * and in it's second position it's the index where the song is found, by default is 0
	 */
	public int[] findSong(String title) { 
		int[] songFoundData = new int[2];
		int found=0;
		int index=0;
		for (int i=0; i<MAX_SONGS && found!=1; i++){
			Song songAux= songs[i];
			if(songAux!=null && songAux.getTitle().equals(title)){
				found=1;
				index=i;
			}
		}
		songFoundData[0]=found;
		songFoundData[1]=index;
		return songFoundData;
	}
	
	/**
	 * findPlaylist is a method that informs if a playlist already exists and in which index is located
	 * @param name is the playlist's name to look for 
	 * @return playlistFoundData it's an array, in its first position it's either 1 or 0. 1 for found, 0 for not found
	 * and in it's second position it's the index where the playlist is found, by default is 0
	 */
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
	
	/**
	 * findPlaylistType is a method that checks if the given playlist belongs to the given type  <br>
	 * <b> pre: </b> playlists array is initialized. The playlist is already created <br>  
	 * <b> pos: </b> <br>
	 * @param type is the type to be consulted, it's either 1, 2 or 3. 1 for public. 2 for private and 3 for restricted. type &gt; 0 <br>
	 * @param position is the position in the playlists array where the playlist to be consulted is. userIndex &gt; 0  
	 * @return typeFound if the playlist belongs to the given type it's true, otherwise is false 
	 */
	public boolean findPlaylistType(int type, int position){
		boolean typeFound=false;
		
		if(type==1){
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
		
		
		return typeFound;
	}
	
	/**
	 * rankPlaylist is a method that gives a public playlist a score <br>
	 * <b> pre: </b> playlists array is initialized. The playlist is already created. The playlist is indeed public <br>  
	 * <b> pos: </b> <br>
	 * @param playlistIndex is the position in the playlists array where the playlist is. playlistIndex &ge;0 
	 * @param rank is the given score. Integer from 1 to 5. rank &gt; 0 <br> 
	 */
	public void rankPlaylist(int playlistIndex, int rank){
		PublicPlaylist auxiliar = (PublicPlaylist)playlists[playlistIndex];
		auxiliar.rankPlaylist(rank);
		double newAverage= auxiliar.calculateAverage();
		auxiliar.setAverageRank(newAverage);
		
	}
	
	/**
	 * addSongToPlaylist is a method that adds a song to a playlist  <br>;
	 * <b> pre: </b> playlists and songs array are initialized. The playlist and the song are already created. The song isn't already in the playlist <br>  
	 * <b> pos: </b> <br>
	 * @param songIndex is the position in the songs array where the song to be added is. songIndex&ge;0
	 * @param playlistIndex is the position in the playlists array where the playlist is. playlistIndex&ge;0 
	 */
	 //AÑADIR VALIDACIÓN DE CANCIÓN NO REPETIDAAAAAAAA
	public void addSongToPlaylist(int songIndex, int playlistIndex){
		playlists[playlistIndex].addSong(songs[songIndex], playlists[playlistIndex].getGenre());
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
				playlistsInfo+= playlists[i].infoPlaylist();
			}
		}
		return playlistsInfo;
	}
}

