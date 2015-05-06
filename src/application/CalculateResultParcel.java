package application;

//create a parcel to send through the socket to caluculate results when calculateResult method is called

@SuppressWarnings("serial")
public class CalculateResultParcel extends Parcel {

	private int result;
	
	public CalculateResultParcel(int operationId, int result) {
		
		super(operationId);
		this.result = result;
		
	}

	public int getResult() {
		
		return result;
		
	}
	
}
