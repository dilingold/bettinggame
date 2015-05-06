package application;

import model.interfaces.Player;

//creates a parcel to send to server to place a bet
@SuppressWarnings("serial")
public class PlaceBetParcel extends Parcel {

	private Player player;
	private int numberPick;
	private int bet;
	
	public PlaceBetParcel(int operationId, Player player, int numberPick, int bet) {
		
		super(operationId);
		this.player = player;
		this.numberPick = numberPick;
		this.bet = bet;
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
	public int getNumberPick() {
		
		return numberPick;
		
	}
	
	public int getBet() {
		
		return bet;
		
	}
	
}
