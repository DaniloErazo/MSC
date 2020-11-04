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
	
	public boolean checkOwner(String nickname){
		boolean owner=false;
		if(nickname.equals(authorizedUser.getNickname())){
			owner=true;
		}
		return owner;
	}
	
	@Override 
	public String infoPlaylist(){
		String infoPlaylist= super.infoPlaylist() +
		"**  Authorized user: " + authorizedUser.getNickname() + "\n"+
		"***********************************\n";
		return infoPlaylist;
	}









}
