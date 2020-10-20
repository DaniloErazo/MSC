package model;
import model.User;
import model.Song;
import model.Playlist;
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
}

