package model;

import java.util.ArrayList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.WheelCallback;


@SuppressWarnings("serial")
public class WheelCallbackImpl implements WheelCallback {
	
	public WheelCallbackImpl() {
		
		
		
	}

	/*recieves a number from the GameEngineImpl class and displays it*/
	public void nextNumber(int nextNumber, GameEngine engine) {
				
//		System.out.println(nextNumber);
		
	}
	
	/*processes result of spin*/
	public void result(int result, GameEngine engine) {
				
		ArrayList<Player> playerList = (ArrayList<Player>) engine.getAllPlayers();
		
		/*goes through each player and awards winnings, and then displays winners and losers*/
		
		System.out.println("W/L: \t ID#: \t Name: \t\t Bet: \t Pick: \t Points:");
		
		for (int i = 0; i < playerList.size(); i++) {
			
			Player player = playerList.get(i);
			
			if (player.getNumberPick() == result) {
				
				System.out.println("Win \t" + playerList.get(i).toString());
								
			}
		
			else System.out.println("Lose \t" + playerList.get(i).toString());
			
			/*resets player bets and numberPicks to 0 for the next spin*/
			engine.placeBet(playerList.get(i), 0, 0);
			
		}
		
		assert playerList != null : "There are no players";
		
	}
	
}
