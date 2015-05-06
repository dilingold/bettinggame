package application;

import model.interfaces.Player;

//creates a parcel to remove a player from the game
@SuppressWarnings("serial")
public class RemovePlayerBetParcel extends Parcel {

	private Player player;
	
	public RemovePlayerBetParcel(int operationId, Player player) {
		
		super(operationId);
		this.player = player;
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
}
