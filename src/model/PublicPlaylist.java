package model;

public class PublicPlaylist extends Playlist{
	public final static int MAX_RANKS = 10;
	private int[] ranks;
	private double averageRank;
	private String[] nicknameRanked;
	
	public PublicPlaylist(String name){
		super(name);
		ranks = new int[MAX_RANKS];
		nicknameRanked = new String[MAX_RANKS];
		averageRank=0;
	}
	
	
	public double getAverageRank(){
		return averageRank;
	}
	public void setAverageRank(double rank){
		this.averageRank=rank;
	}
	
	public void rankPlaylist(int rank, String nickname){
		for (int i=0; i<MAX_RANKS; i++){
			if(ranks[i]==0){
				ranks[i]=rank;
			}
		}
		for (int i=0; i<MAX_RANKS; i++){
			if(nicknameRanked[i]==""){
				nicknameRanked[i]=nickname;
			}
		}
	}
	
	public double calculateAverage(){
		int sum=0;
		double averageRank=0;
		int count=0;
		for (int i=0; i<MAX_RANKS; i++){
			if(ranks[i]==0){
				count++;
			}
		}
		for (int i=0; i<MAX_RANKS; i++){
			if(ranks[i]!=0){
				sum+=ranks[i];
			}
		}
		
		averageRank= sum/(MAX_RANKS-count);
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