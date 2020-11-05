package model;
public class User{
	private String nickname;
	private String password;
	private int age;
	private int uploadedSongs;
	
	//Relations
	private Category category;
	
	/**
	 * Constructor for a user with all its information <br>
	 * @param nickname is a identifier for the user <br>
	 * @param password <br>
	 * @param age the user's age <br>
	 * @param uploadedSongs is the quantity of songs uploaded to the pool <br>
	 */
	public User(String nickname, String password, int age, int uploadedSongs){
		this.nickname=nickname;
		this.password=password;
		this.age=age;
		this.uploadedSongs=uploadedSongs;
		category=Category.NEWBIE;
	}
	
	/**
	 * Returns the user's nickname 
	 * @return nickname 
	*/
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * Returns the quantity of uploaded songs 
	 * @return quantity of songs 
	*/
	public int getUploadedSongs(){
		return uploadedSongs;
	}
	/**
	* sets the quantity of uploaded songs 
	* @param uploadedSongs number of uploaded songs  
	*/
	public void setUploadedSongs(int uploadedSongs){
		this.uploadedSongs=uploadedSongs;
	}
	/**
	* sets the category for the user 
	* @param category category to update 
	*/
	public void setCategory(Category category){
		this.category=category;
	}
	/**
	* defineCategory is a method to determine to which category the user belongs to, 
	* according to the quantity of uploaded songs <br>
	* <b> pre: </b> <br>
	* <b> pro: </b> category updates if it changed <br>
	* @param uploadedSongs is the quantity of uploaded songs by the user. uploadedSongs &gt; 0 <br>
	* @return categoryUpdate it's the new category in case it's changed 
	*/
	public Category defineCategory(int uploadedSongs){
		Category categoryUpdate=null;
		if(uploadedSongs<3){
			categoryUpdate=Category.NEWBIE;
		}else if(uploadedSongs>3 && uploadedSongs<10){
			categoryUpdate=Category.LITTLE_CONTRIBUTOR;
		}else if(uploadedSongs>= 10 && uploadedSongs<30){
			categoryUpdate=Category.MILD_CONTRIBUTOR;
		} else if (uploadedSongs>30){
			categoryUpdate=Category.STAR_CONTRIBUTOR;
		}
		return categoryUpdate;
	}
	/**
	 * Method that produces a String with some of the user's information 
	 */
	public String toString(){
		String infoUser= "*************  User **************\n"+
		"**  UserName: " + nickname + "\n"+
		"**  Age: " + age + "\n"+
		"**  Category: " + category + "\n"+
		"***********************************\n";
		return infoUser;
	}
}
