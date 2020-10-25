package model;

public class PublicPlaylist extends Playlist{
	public final static int MAX_RANKS = 10;
	private int[] ranks;
	private double averageRank;
	
	public PublicPlaylist(String name){
		super(name);
		ranks = new int[MAX_RANKS];
		averageRank=0;
	}
}