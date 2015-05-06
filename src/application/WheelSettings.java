package application;

import model.GameEngineClientStub;

//changes the game's wheel settings when a player clicks on the wheel settings button and clickes ok
public class WheelSettings {

	private int wheelSize;
	private int initialDelay;
	private int finalDelay;
	private int delayIncrement;
	
	public WheelSettings(GameEngineClientStub game, int wheelSize, int initialDelay, int finalDelay, int delayIncrement) {
		
		this.wheelSize = wheelSize;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
		
		game.changeWheelSettings(wheelSize, initialDelay, finalDelay, delayIncrement);
		
		
	}
	
	public int getWheelSize() {
		
		return wheelSize;
		
	}
	
	public int getInitialDelay() {
		
		return initialDelay;
		
	}
	
	public int getFinalDelay() {
		
		return finalDelay;
		
	}
	
	public int getDelayIncrement() {
		
		return delayIncrement;
		
	}
	
}
