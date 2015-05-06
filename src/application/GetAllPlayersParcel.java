package application;

//creates a parcel to send to server to get all players and return it to client
@SuppressWarnings("serial")
public class GetAllPlayersParcel extends Parcel {
	
	public GetAllPlayersParcel(int operationId) {
		
		super (operationId);
		
	}

}
