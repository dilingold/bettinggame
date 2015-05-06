package application;

//creates a parcel to send from the serverside callback to the callback server with
//the next number to display on the gui
@SuppressWarnings("serial")
public class NextNumberParcel extends Parcel {

	private int nextNumber;
	
	public NextNumberParcel(int operationId, int nextNumber) {
		
		super(operationId);
		this.nextNumber = nextNumber;
		
	}
	
	public int getNextNumber() {
		
		return nextNumber;
		
	}
	
}
