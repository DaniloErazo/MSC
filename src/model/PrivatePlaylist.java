package model;

public class PrivatePlaylist extends Playlist{
	private User authorizedUser;
	
	/**
	 * Constructor that creates a new private playlist with duration 0:0:0, 
	 * empty genre, an empty array for the songs, a counter for the added songs
	 * and an owner 
	 * @param name playlist name
	 * @param authorizedUser playlist owner
	 */
	public PrivatePlaylist(String name, User authorizedUser){
		super(name);
		this.authorizedUser=authorizedUser;
	}
	
	/**
	 * Returns the owner of the playlist  
	 * @return authorizedUser  
	*/
	public User getAuthorizedUser(){
		return authorizedUser;
	}
	
	/**
	 * Method that checks if the given user's nickname is the authorized user of the playlist <br>
	 * <b> pre: </b> <br>  
	 * <b> pos: </b> <br>
	 * @param nickname is user's nickname to check. nickname!=null. nickname!="" <br>
	 * @return owner which is true if the user is the owner and false if it isn't
	 */
	public boolean checkOwner(String nickname){
		boolean owner=false;
		if(nickname.equals(authorizedUser.getNickname())){
			owner=true;
		}
		return owner;
	}
	
	/**
	 * Method that produces a String with the information of the playlist, including owner
	 */
	@Override 
	public String infoPlaylist(){
		String infoPlaylist= super.infoPlaylist() +
		"**  Authorized user: " + authorizedUser.getNickname() + "\n"+
		"***********************************\n";
		return infoPlaylist;
	}

}
