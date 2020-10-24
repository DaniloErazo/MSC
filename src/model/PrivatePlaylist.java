package model;

public class PrivatePlaylist extends Playlist{
	private User authorizedUser;
	
	public PrivatePlaylist(User authorizedUser){
		super(String name);
		this.authorizedUser=authorizedUser;
	}
	
	public String getAuthorizedUser(){
		return authorizedUser;
	}
	@override 
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
