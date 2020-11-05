package model;

public class PublicPlaylist extends Playlist{
	private double ranks;
	private double averageRank;
	private double timesRanked;
	
	/**
	 * Constructor that creates a new public playlist with duration 0:0:0, 
	 * empty genre, an empty array for the songs, a counter for the score and
	 * a counter for the times the playlist has been scored 
	 * @param name playlist name
	 */
	public PublicPlaylist(String name){
		super(name);
		ranks=0;
		averageRank=0;
		timesRanked=0;
	}
	
	/**
	 * Returns the playlist average score 
	 * @return averageRank  
	*/
	public double getAverageRank(){
		return averageRank;
	}
	
	/**
	* sets the average score of the playlist
	* @param rank new average
	*/
	public void setAverageRank(double rank){
		this.averageRank=rank;
	}
	
	/**
	 * method that scores the playlist <br>
	 * <b> pre: </b> ranks and timesRanked are initialized <br>  
	 * <b> pos: </b> timesRanked++. ranks gets increased <br>
	 * @param rank is the given score. rank &gt; 0.  
	 */
	public void rankPlaylist(int rank){
		ranks=ranks+rank;
		timesRanked++;
	}
	
	/**
	 * method that calculates the average score <br>
	 * <b> pre: </b> <br>  
	 * <b> pos: </b> <br>
	 * @return averageRank which is a double with the new average
	 */
	public double calculateAverage(){
		double averageRank=0;
		averageRank= ranks/timesRanked;
		return averageRank;
	}  
	
	
	/**
	 * Method that produces a String with the information of the playlist, 
	 * including the average score  
	 */
	@Override
	public String infoPlaylist(){
		String infoPlaylist = super.infoPlaylist()+
		"**  Calificación promedio: " + getAverageRank() + "\n"+
		"***********************************\n";
		return infoPlaylist;
	}
	
}