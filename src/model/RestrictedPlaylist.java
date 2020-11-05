package model;

public class RestrictedPlaylist extends Playlist{
	public final static int MAX_AUSERS= 5;
	private User[] authorizedUsers;
	private int numberUsers;
	
	
	/**
	 * Constructor that creates a new restricted playlist with duration 0:0:0, 
	 * empty genre, an empty array for the songs, a counter for the added songs
	 * a counter for the authorized users and a main owner
	 * @param name playlist name
	 * @param authorizedUser main playlist user
	 */
	public RestrictedPlaylist(String name, User authorizedUser){
		super(name);
		authorizedUsers = new User[MAX_AUSERS];
		authorizedUsers[0]=authorizedUser;
		this.numberUsers=1;
	}
	
	/**
	 * Method that checks if there's space for more authorized users
	 * @return true if there's space, otherwise false
	 */
	public boolean availableSpace(){
		return numberUsers<MAX_AUSERS;
	}
	
	/**
	 * Returns the quantity of authorized users  
	 * @return numberUsers  
	*/
	public int getNumberUsers(){
		return numberUsers;
	}
	
	/**
	* sets the number of authorized users  
	* @param numberUsers new number of authorized users
	*/
	public void setNumberUsers(int numberUsers){
		this.numberUsers=numberUsers;
	}
	
	/**
	 * Method that adds a new authorized user the playlist  <br>
	 * <b> pre: </b> authorizedUsers is initialized <br>  
	 * <b> pos: </b>numberUsers++, authorizedUsers has a new object in the first empty found index <br>
	 * @param newUser is the user to be added as an authorized user <br>  
	 */
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
	
	/**
	 * Method that checks if a user is already an authorized user of the playlist<br>
	 * <b> pre: </b> authorizedUsers is initialized <br>  
	 * <b> pos: </b> <br>
	 * @param nickname is user's nickname to check existence. nickname!=null. nickname!="" <br>
	 * @return found true if the user's an authorized user, otherwise false
	 */
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
	
	/**
	 * Method that produces a String with the information of the playlist, 
	 * including authorized users 
	 */
	@Override 
	public String infoPlaylist(){
		String infoPlaylist= super.infoPlaylist() +
		"**  Authorized user: " + getAllUsersNames() + "\n" +
		"***********************************\n";
		return infoPlaylist;
	}
	
	/**
	 * Method that produces a String with the names of all the authorized users
	 * @return allNames a String with the names o all the authorized users 
	 */
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