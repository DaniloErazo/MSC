package model;

public class RestrictedPlaylist extends Playlist{
	public final static int MAX_AUSERS= 5;
	private User[] authorizedUsers;
	private int numberUsers;
	
	public RestrictedPlaylist(String name, User authorizedUser){
		super(name);
		authorizedUsers = new User[MAX_AUSERS];
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
	
	public boolean repeatedAuthorizedUser(String nickname){
		boolean repeated=false;
		for(int i=0; i<MAX_AUSERS & !repeated; i++){
			if(authorizedUsers[i]!=null){
				if(authorizedUsers[i].getNickname().equals(nickname)){
					repeated=true;
				}
			}
		}
		return repeated;
	}
	
	public boolean findAuthorizedUser(String nickname){
		boolean found= false;
		for(int i=0; i<MAX_AUSERS & !found; i++){
			if(authorizedUsers[i]!=null){
				
				if(authorizedUsers[i].getNickname().equals(nickname)){
					found=true;
				}
				
			}
		}
		return found;
	}
	
	public boolean authorizedUserAvailable(){
		return numberUsers<MAX_AUSERS;
	}
	
	@Override 
	public String infoPlaylist(){
		String infoPlaylist= super.infoPlaylist() +
		"**  Authorized user: " + getAllUsersNames() + "\n" +
		"***********************************\n";
		return infoPlaylist;
	}
	public String getAllUsersNames(){
		String allNames="";
		for(int i=0; i<MAX_AUSERS; i++){
			if(authorizedUsers[i]!=null){
				allNames+= authorizedUsers[i].getNickname() + ", ";
			}
		}
		return allNames;
	}
}