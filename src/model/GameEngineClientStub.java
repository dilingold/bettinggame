package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import application.AddPlayerParcel;
import application.CalculateResultParcel;
import application.ChangeWheelSettingsParcel;
import application.CreateCallbackSocketParcel;
import application.GetAllPlayersParcel;
import application.GetNextCallbackPortParcel;
import application.GetPlayerBetsParcel;
import application.Parcel;
import application.PlaceBetParcel;
import application.RemovePlayerBetParcel;
import application.ResetAllPlayerBetsParcel;
import application.SpinParcel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.WheelCallback;

//client stub to handle all communication between client and server through socket
@SuppressWarnings("serial")
public class GameEngineClientStub extends JFrame implements GameEngine 
{
	
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;
	
	private int callbackPort;
	
	public GameEngineClientStub() throws IOException {
		
		Socket socket = null;
		
		try {
			
			socket = new Socket("localhost",8000);
			
			outputToServer = new ObjectOutputStream(socket.getOutputStream());
			inputFromServer = new ObjectInputStream(socket.getInputStream());
			
		}
		
		catch (IOException ex) {
			
			if (socket != null)
				socket.close();
			System.err.println(ex);
			
		}
		
	}

	//sends the spin parcel through the socket to initiate a spin
	@Override
	public void spin(int wheelSize, int initialDelay, int finalDelay,
			int delayIncrement, WheelCallback callback) {
	
		Parcel parcel = new SpinParcel(1);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
			
			
			
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}

	//sends the add player parcel through the socket to add a player
	//to the player array at GameEngineImpl
	@Override
	public void addPlayer(Player player) {
		
		Parcel parcel = new AddPlayerParcel(2, player);

		
		try {

			outputToServer.writeObject(parcel);
			outputToServer.reset();

		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	//sends the place bet parcel through the socket to place a bet
	//and return a boolean if the bet was successful
	@Override
	public boolean placeBet(Player player, Integer numberPick, int bet) {
		
		boolean betPlaced = false;

		Parcel parcel = new PlaceBetParcel(3, player, numberPick, bet);
		
		try {

			outputToServer.writeObject(parcel);
			outputToServer.reset();
			betPlaced = (Boolean)inputFromServer.readObject();
			
		}
		
		catch (IOException | ClassNotFoundException ex) {
			
			System.err.println(ex);
			
		}
		return betPlaced;
	}

	//sends the calculate result parcel through the socket to 
	//calculate the result
	@Override
	public void calculateResult(int result) {

		Parcel parcel = new CalculateResultParcel(4, result);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
			
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}

	}

	//sends a parcel to the server to return the player array from GameEngineImpl
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Player> getAllPlayers() {
		
		ArrayList<Player> allPlayersArray = new ArrayList<Player>();
		
		Parcel parcel = new GetAllPlayersParcel(5);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
			allPlayersArray = (ArrayList<Player>)inputFromServer.readObject();
			
		}
		
		catch (IOException | ClassNotFoundException ex) {
			
			System.err.println(ex);
			
		} 

		return allPlayersArray;
	}
	
	//sends a parcel to the server to remove a player from the playerBet hashmap
	public void removePlayerBet(Player player) {

		Parcel parcel = new RemovePlayerBetParcel(6, player);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
			
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}	
	
	//sends a parcel to server to resets the player bets hashmap in GameEngineImpl to start a new game
	public void resetAllPlayerBets() {

		Parcel parcel = new ResetAllPlayerBetsParcel(7);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
			
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}
	
	//sends a parcel to server to return the player bets hashmap from GameEngineImpl
	@SuppressWarnings("unchecked")
	public HashMap<Player,Integer> getPlayerBets() {
		
		HashMap<Player,Integer> playerBets = new HashMap<Player,Integer>();
		
		Parcel parcel = new GetPlayerBetsParcel(8);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
			playerBets = (HashMap<Player,Integer>)inputFromServer.readObject();
			
		}
		
		catch (IOException | ClassNotFoundException ex) {
			
			System.err.println(ex);
			
		}
		return playerBets;
		
	}
	
	//sends a parcel to server to change the wheel settings
	public void changeWheelSettings(int wheelSize, int initialDelay, int finalDelay, int delayIncrement) {
		
		Parcel parcel = new ChangeWheelSettingsParcel(9, wheelSize, initialDelay, finalDelay, delayIncrement);
		
		try {
			
			outputToServer.writeObject(parcel);
			outputToServer.reset();
						
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
		
	}
	
	//sends a parcel to server to retrieve the next available port for the serverside callback
	//to connect to the callback server
	public void createNextCallbackPort() {
		
		Parcel parcel = new GetNextCallbackPortParcel(10);
		
		try {
			
			outputToServer.writeObject(parcel);
			callbackPort = (Integer)inputFromServer.readObject();
			outputToServer.reset();
						
		}
		
		catch (IOException | ClassNotFoundException ex) {
			
			System.err.println(ex);
			
		}
		
	}
	
	//sends a parcel to server to create a socket to connect to the callback server
	public void createCallbackSocket() {
		
		Parcel parcel = new CreateCallbackSocketParcel(11);
		
		try {
			
			outputToServer.writeObject(parcel);
						
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}
	
	//returns the next available port for the client's callback server to use
	public int getCallbackPort() {
		
		return callbackPort;
		
	}
}
