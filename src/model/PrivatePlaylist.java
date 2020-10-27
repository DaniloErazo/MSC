package model;

public class PrivatePlaylist extends Playlist{
	private User authorizedUser;
	
	public PrivatePlaylist(String name, User authorizedUser){
		super(name);
		this.authorizedUser=authorizedUser;
	}
	
	public User getAuthorizedUser(){
		return authorizedUser;
	}
	@Override 
	public String toString(){
		String infoSong= "*************  Playlist **************\n"+
		"**  Title: " + name + "\n"+
		"**  Duration: " + duration.getHour() + ":" + duration.getMinutes() + ":" + duration.getSeconds() + "\n"+
		"**  Genre: " + genre + "\n"+
		"**  Authorized user: " + authorizedUser.getNickname() + "\n"+
		"***********************************\n";
		return infoSong;
	}









}
