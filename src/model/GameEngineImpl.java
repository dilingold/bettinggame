package model;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.WheelCallback;


public class GameEngineImpl implements GameEngine{

	/*instance variables */
	private int result;
	private WheelCallback callback;
	private ArrayList<Player> playerList;
	
	private HashMap<Player,Integer> playerBets;

	
	public GameEngineImpl() {
		
		/*initialise array list of players */
		playerList = new ArrayList<Player>();
				
		playerBets = new HashMap<Player,Integer>();
		
	}
	
	@SuppressWarnings("rawtypes")
	public void spin(int wheelSize, int initialDelay, int finalDelay,
			int delayIncrement, WheelCallback callback) {

		this.callback = callback;
		
		/*throws exception if wheel size is less than 1*/
		if (wheelSize <= 0) {
			
			throw new IllegalArgumentException("Wheel Size must be positive");
			
			
		}
		
		/*checks that the numberPick is within the size of the wheel
		 * if it is not the player's bet and numberPick is reset to 0*/
		boolean checkNumberPick = true;
		
		Iterator it = playerBets.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Map.Entry pair = (Map.Entry)it.next();
			
			Player player = (Player)pair.getKey();
			int numberPick = (Integer)pair.getValue();
		
			if (numberPick > wheelSize || numberPick < 0) {
				
//				checkNumberPick = false;
					
				player.setPoints(player.getPoints() + player.getBet());
				
				this.placeBet(player, 0, 0);
		
			}
		}
			
		
		
		assert checkNumberPick == true : "invalid numberPick";
		
		
		/*create a random number between 0 and wheelSize */
		Random rand = new Random();
		int currentNumber = rand.nextInt(wheelSize);
		
		/*the wheel keeps spinning and slowing down until its speed reaches
		 * finalDelay and it stops.
		 */
		while (initialDelay <= finalDelay) {

			/*when the wheel reaches the highest number in the wheel
			 * it resets to 0
			 */
			if (currentNumber > wheelSize) {
				
				currentNumber = 0;
				
			}
			
		
			
			/*slows down the wheel by initialDelay after it passes each number*/
		try {
				
				Thread.sleep(initialDelay);
				
			}

			catch(InterruptedException ie){
				
			} 	
			
			/*increase the delay after each number is passed by 
			 * the amount delayIncrement*/
			initialDelay = initialDelay + delayIncrement;
			
			/*refers to WheelCallBack method nextNumber to 
			 * display the current number on the wheel
			 */

			callback.nextNumber(currentNumber, this);
			
			currentNumber++;
			
		}
		
		/*when the wheel stops the result is displayed*/
		result = currentNumber - 1;

		this.calculateResult(result);
	}

	/*before adding player, check that his playerId does not 
	 * already exist*/
	public void addPlayer(Player player) {
		
		boolean playerIdUnique = true;
		
		String playerId = player.getPlayerId();
		
		for (int i = 0; i < playerList.size(); i++) {
			
			if (playerId.equals(playerList.get(i).getPlayerId())) {
				
				playerIdUnique = false;
				
			}
			
		}
		
		if (playerIdUnique) {
			
			playerList.add(player);
			
		}
		
		assert playerIdUnique : "Player ID already exists, player not added";
		
	}
	
	public boolean removePlayer(Player player) {
		
		return playerList.remove(player);
		
	}
	
	public boolean placeBet(Player player, Integer numberPick, int bet) {
		
		/*refers to SimplePlayer method placeBet to place the bet. If it
		 * was successful, boolean true is returned, otherwise false.
		 */
		boolean betPlaced = player.placeBet(numberPick, bet);
		
		if (betPlaced == true) {

			playerBets.put(player, numberPick);
			
		}
		
		return betPlaced;

	}
	
	/*refers to WheelCallBack method result to process bets */
	@SuppressWarnings("rawtypes")
	public void calculateResult(int result) {
		
		
		
		Iterator it = playerBets.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Map.Entry pair = (Map.Entry)it.next();
			
			Player player = (Player)pair.getKey();
			int numberPick = (Integer)pair.getValue();
		
			if (numberPick == result) {

				player.setPoints(player.getBet()*2 + player.getPoints());
		
			}
			
		}
		
		callback.result(result, this);
		
	}
	
	public void resetAllPlayerBets() {
		
		playerBets.clear();
		
	}
	
	public void removePlayerBet(Player player) {

		playerBets.remove(player);

		
	}
	
	public ArrayList<Player> getAllPlayers() {

		return playerList;

	}	
	
	public HashMap<Player,Integer> getPlayerBets() {
		
		return playerBets;
		
	}

}
