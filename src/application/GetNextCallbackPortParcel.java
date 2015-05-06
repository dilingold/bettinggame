package application;

//creates a parcel to get the next available port from the server stub, so that the callback server can connect
//to the serverside callback
@SuppressWarnings("serial")
public class GetNextCallbackPortParcel extends Parcel {

	public GetNextCallbackPortParcel(int operationId) {
		
		super(operationId);
		
	}
	
}
