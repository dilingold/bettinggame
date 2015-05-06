package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import application.CalculateResultParcel;
import application.NextNumberParcel;
import application.Parcel;

import view.WheelFrame;

//server for wheel callback server side to connect to and communicate with to send the next number
//and result from spin through parcels
@SuppressWarnings("serial")
public class WheelCallbackServer extends JFrame implements Serializable {

	private WheelFrame frame;
	private GameEngineClientStub gameEngine;

	private ObjectInputStream inputFromServerSideCallback;
	
	public WheelCallbackServer(WheelFrame frame, GameEngineClientStub gameEngine) {
		
		this.frame = frame;
		this.gameEngine = gameEngine;
	
		//first determine the next available port from the server stub
		gameEngine.createNextCallbackPort();
		
		int port = gameEngine.getCallbackPort();
		
		//create a socket on this side, then initiate the socket to 
		//be created on the callback server side to connect to it
		ServerSocket callbackServer = null;
		
		try {
			
			callbackServer = new ServerSocket(port);
			
			gameEngine.createCallbackSocket();
			
			while (true) {
				
				Socket socket = callbackServer.accept();				
				
				Thread thread = new CallbackHandlingThread(socket);
				thread.start();
				
			}
			
		}
		
		catch (IOException ex) {
			
			System.err.print(ex.getStackTrace());
			
		}
		
	}

	//accepts parcel from callback server side, and uses it to display next number or result on gui
	public class CallbackHandlingThread extends Thread {
	
		private Socket socket;
	
		public CallbackHandlingThread(Socket socket) {
		
			this.socket = socket;
		
		}
		
		public void run() {
			
			try {
				
				inputFromServerSideCallback = new ObjectInputStream(socket.getInputStream());
				
				while (true) {
					
					Parcel parcel = (Parcel)inputFromServerSideCallback.readObject();
					
					int operationId = parcel.getOperationId();
					
					switch (operationId) {
					
					case 1: 
							NextNumberParcel nextNumberParcel = (NextNumberParcel)parcel;
							int nextNumber = nextNumberParcel.getNextNumber();
							frame.getGamePanel().getWheelPanel().nextNumber(nextNumber, gameEngine);
							break;
							
					case 2: CalculateResultParcel calculateResultParcel = (CalculateResultParcel)parcel;
							int result = calculateResultParcel.getResult();
							frame.getGamePanel().getWheelPanel().result(result, gameEngine);
							break;
							
					}
					
				}	
			
			} 
			
			catch (IOException | ClassNotFoundException e) {
				
				e.printStackTrace();
			
			}
			
			
			
		}
	
	}

}
