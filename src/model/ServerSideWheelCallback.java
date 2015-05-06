package model;

import java.io.IOException;
import java.io.ObjectOutputStream;

import application.CalculateResultParcel;
import application.NextNumberParcel;
import application.Parcel;
import model.interfaces.GameEngine;
import model.interfaces.WheelCallback;

//gets the next number / result from GameEngineImpl and sends it to the clients callback servers
//in a parcel via the sockets in the socket array from the game engine server stub
@SuppressWarnings("serial")
public class ServerSideWheelCallback implements WheelCallback {

	private GameEngineServerStub server;
	
	private ObjectOutputStream outputToServer;
	
	public ServerSideWheelCallback(GameEngineServerStub server) {
		
		this.server = server;
		
	}
	
	@Override
	public void nextNumber(int nextNumber, GameEngine engine) {
		
		server.getTextArea().append(nextNumber + "|");
		
		Parcel parcel = new NextNumberParcel(1,nextNumber);
		
		for (int i = 0; i < server.getSocketArray().size(); i++) {
			
			try {
				
				outputToServer = new ObjectOutputStream(server.getSocketArray().get(i).getOutputStream());
				outputToServer.writeObject(parcel);
				
			} 
			
			catch (IOException e) {
				
				System.err.println(e);
				
			}
			
		}


	}

	@Override
	public void result(int result, GameEngine engine) {
		
		server.getTextArea().append("\nWinning Number is " + result + "\n");
		
		Parcel parcel = new CalculateResultParcel(2,result);
		
		for (int i = 0; i < server.getSocketArray().size(); i++) {
			
			try {
				
				outputToServer = new ObjectOutputStream(server.getSocketArray().get(i).getOutputStream());
				outputToServer.writeObject(parcel);
				
			} 
			
			catch (IOException e) {
				
				System.err.println(e);
				
			}

		}
		
	}

}
