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
	 * Qparam nickname is a identifier for the user <br>
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
	public int getUploadedSongs(){
		return uploadedSongs;
	}
	public void setUploadedSongs(int uploadedSongs){
		this.uploadedSongs=uploadedSongs;
	}
	public void setCategory(Category category){
		this.category=category;
	}
	/**
	* defineCategory is a method to determine to which category the user belongs to, 
	* according to the quantity of uploaded songs <br>
	* <b> pre: </b> <br>
	* <b> pro: </b> category updates if it changed <br>
	* @param uploadedSongs is the quantity of uploaded songs by the user. uploadedSongs>0 <br>
	* @return categoryUpdate it's the new category in case it's changed 
	*/
	public Category defineCategory(int uploadedSongs){
		Category category=null;
		if(uploadedSongs<3){
			category=Category.NEWBIE;
		}else if(uploadedSongs>3 && uploadedSongs<10){
			category=Category.LITTLE_CONTRIBUTOR;
		}else if(uploadedSongs>= 10 && uploadedSongs<30){
			category=Category.MILD_CONTRIBUTOR;
		} else if (uploadedSongs>30){
			category=Category.STAR_CONTRIBUTOR;
		}
		return categoryUpdate;
	}
	/**
	 * Method that produces a String with the information of a user
	 */
	public String toString(){
		String infoUser= "*************  User **************/n"+
		"**  UserName: " + nickname + "/n"+
		"**  Age: " + age + "/n"+
		"**  Category: " + category + "/n"+
		"***********************************";
		return infoUser;
	}
}
