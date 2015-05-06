package application;

//create a parcel to send through the socket to change the wheel settings

@SuppressWarnings("serial")
public class ChangeWheelSettingsParcel extends Parcel {

	private int wheelSize;
	private int initialDelay;
	private int finalDelay;
	private int delayIncrement;
	
	public ChangeWheelSettingsParcel(int operationId, int wheelSize, int initialDelay, int finalDelay, int delayIncrement) {
		
		super(operationId);
		this.wheelSize = wheelSize;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
		
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
