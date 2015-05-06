package model;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class SimplePlayer implements Player {

	/*instance variables*/
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private int numberPick;
	
	public SimplePlayer(String playerId, String name, int points) {
		
		/*initialise playerId, playerName and points*/
		this.playerId = playerId;
		playerName = name;
		this.points = points;
		
	}
	
	public String getPlayerName() {
		
		return playerName;
		
	}
	
	public void setPlayerName(String name) {
		
		playerName = name;
		
	}
	
	public int getPoints() {
		
		return points;
		
	}
	
	/*sets a player's points after a bet or win*/
	public void setPoints(int points) {
		
		this.points = points;
		
	}
	
	public String getPlayerId() {
		
		return playerId;
		
	}
	
	/*places bet for player*/
	public boolean placeBet(Integer numberPick, int bet) {
			
		/*checks that the player has enough points for the bet
		 * and that the bet is greater than 0*/
		if (bet <= points && bet > 0) {
			
			this.numberPick = numberPick;
			this.bet = bet;
			points = points - bet;
			
			return true;
		}
		
		else {
			
			return false;
			
		}
	}
	
	public int getNumberPick() {
		
		return numberPick;
		
	}
	
	public int getBet() {
		
		return bet;
		
	}
	
	public String toString() {
		
		return playerId + "\t" + playerName + "\t" + bet + "\t" + numberPick + "\t" + points;
		
	}
	
}
