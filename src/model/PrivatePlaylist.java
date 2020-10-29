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
	public String infoPlaylist(){
		String infoPlaylist= super.toString() +
		"**  Authorized user: " + authorizedUser.getNickname() + "\n"+
		"***********************************\n";
		return infoPlaylist;
	}









}
