package application;

//creates a parcel to send to the server to notify it to create a new socket to connect to the WheelCallbackServer
@SuppressWarnings("serial")
public class CreateCallbackSocketParcel extends Parcel {
	
	public CreateCallbackSocketParcel(int operationId) {
		
		super(operationId);
		
	}

}
