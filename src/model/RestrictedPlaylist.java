package model;

public class RestrictedPlaylist extends Playlist{
	public final static int MAX_AUSERS= 5;
	private User[] authorizedUsers;
	private int numberUsers;
	
	public RestrictedPlaylist(String name, User authorizedUser){
		super(name);
		this.authorizedUsers = new User[MAX_AUSERS];
		authorizedUsers[0]=authorizedUser;
		this.numberUsers=1;
	}
	
	public boolean availableSpace(){
		return numberUsers<MAX_AUSERS;
	}
	public int getNumberUsers(){
		return numberUsers;
	}
	public void setNumberUsers(int numberUsers){
		this.numberUsers=numberUsers;
	}
	
	public void addAuthorizedUser(User newUser){
		boolean emptyFound=false;
		for (int i=0; i<MAX_AUSERS && !emptyFound ; i++){
			
			if(authorizedUsers[i]==null){
				authorizedUsers[i]=newUser;
				numberUsers++;
				emptyFound=true;
			}
			
		}
	}
	
	@Override 
	public String toString(){
		String infoSong= "*************  Playlist **************\n"+
		"**  Title: " + name + "\n"+
		"**  Duration: " + duration.getHour() + ":" + duration.getMinutes() + ":" + duration.getSeconds() + "\n"+
		"**  Genre: " + genre + "\n"+
		"**  Authorized user: " + getAllUsersNames() + "\n"+
		"***********************************\n";
		return infoSong;
	}
	public String getAllUsersNames(){
		String allNames="";
		for(int i=0; i<(numberUsers-1); i++){
			allNames+= authorizedUsers[i].getNickname() + ", ";
		}
		return allNames;
	}
}