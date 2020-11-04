package model;

public class PublicPlaylist extends Playlist{
	private double ranks;
	private double averageRank;
	private double timesRanked;
	
	public PublicPlaylist(String name){
		super(name);
		ranks=0;
		averageRank=0;
		timesRanked=0;
	}
	
	public double getAverageRank(){
		return averageRank;
	}
	public void setAverageRank(double rank){
		this.averageRank=rank;
	}
	
	public void rankPlaylist(int rank){
		ranks=ranks+rank;
		timesRanked++;
	}
	
	public double calculateAverage(){
		double averageRank=0;
		averageRank= ranks/timesRanked;
		return averageRank;
	}  
	
	@Override
	public String infoPlaylist(){
		String infoPlaylist = super.infoPlaylist()+
		"**  Calificación promedio: " + getAverageRank() + "\n"+
		"***********************************\n";
		return infoPlaylist;
	}
	
}