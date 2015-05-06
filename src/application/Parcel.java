package application;

import java.io.Serializable;

//abstract super class parcel  to which all parcel classes are subclasses of
@SuppressWarnings("serial")
public abstract class Parcel implements Serializable {

	private int operationId;
	
	public Parcel(int operationId) {
		
		this.operationId = operationId;
		
	}
	
	public int getOperationId() {
		
		return operationId;
		
	}
	
}
