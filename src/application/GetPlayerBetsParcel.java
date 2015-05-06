package application;

//creates a parcel to get the hash map of players betting in the game from the server
//and return it to the client
@SuppressWarnings("serial")
public class GetPlayerBetsParcel extends Parcel {

	public GetPlayerBetsParcel(int operationId) {
		
		super(operationId);
		
	}
	
}
