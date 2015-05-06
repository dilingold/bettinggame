package application;

//creates a parcel to send to server when a new game has begun to reset hashmap of 
//players betting
@SuppressWarnings("serial")
public class ResetAllPlayerBetsParcel extends Parcel {

	public ResetAllPlayerBetsParcel(int operationId) {
		
		super(operationId);
		
	}
	
}
