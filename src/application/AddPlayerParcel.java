package application;

import model.interfaces.Player;

//create a parcel to send through the socket to add a player when addPlayer method is called

@SuppressWarnings("serial")
public class AddPlayerParcel extends Parcel {
	
	private Player player;

	public AddPlayerParcel(int operationId, Player player) {
		
		super(operationId);
		this.player = player;
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
}
