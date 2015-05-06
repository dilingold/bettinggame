package model;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import application.AddPlayerParcel;
import application.CalculateResultParcel;
import application.ChangeWheelSettingsParcel;
import application.Parcel;
import application.PlaceBetParcel;
import application.RemovePlayerBetParcel;

import model.interfaces.Player;

//creates a server socket for the clients to connect to and communicate with the GameEngineImpl
@SuppressWarnings("serial")
public class GameEngineServerStub extends JFrame {

	private GameEngineImpl game;
	private JTextArea textArea;
	private JButton refresh;
	
	private ArrayList<Socket> socketArray;
	
	private int wheelSize = 40;
	private int initialDelay = 1;
	private int finalDelay = 200;
	private int delayIncrement = 10;
	
	private int callbackPort;
	
	public GameEngineServerStub(GameEngineImpl game) throws IOException {
		
		this.game = game;
		
		callbackPort = 10000;
		
		textArea = new JTextArea();
		refresh = new JButton("Refresh");

		socketArray = new ArrayList<Socket>();
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(textArea,BorderLayout.CENTER);
		getContentPane().add(refresh,BorderLayout.SOUTH);
		
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(e.getSource() instanceof JButton) {
					
					textArea.setText(null);
					
				}
				
			}
			
		});
		
		setTitle("Game Engine Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		ServerSocket server = null;
		
		try {
			
			server = new ServerSocket(8000);
			
			int clientNumber = 1;
			
			while (true) {
				
				Socket socket1 = server.accept();
				
				textArea.append("Starting thread from client number " + clientNumber + " at " + new Date() + "\n");
				
				ClientHandlingThread thread = new ClientHandlingThread(socket1, this);
				
				thread.start();
				
				clientNumber++;
			}
			
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}
	
	//creates a new thread for each client
	public class ClientHandlingThread extends Thread {
		
		private Socket socket;
		private GameEngineServerStub server;
		
		public ClientHandlingThread(Socket socket, GameEngineServerStub server) {
			
			this.server = server;
			this.socket = socket;
			
		}
		
		public void run() {
			
			try {
				
				ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
				
				Player player;
				
				//when a client sends a parcel to the server, the server opens the parcel and acts according to the
				//operation Id
				while (true)
				{
					
					Parcel parcel = (Parcel)inputFromClient.readObject();
					
					int operationId = parcel.getOperationId();
					
					switch (operationId) {
					
					case 1: textArea.append("Wheel Spinning ...");
							game.spin(wheelSize, initialDelay, finalDelay, delayIncrement, new ServerSideWheelCallback(server));
							break;
							
					case 2: AddPlayerParcel addPlayerParcel = (AddPlayerParcel)parcel;
							player = addPlayerParcel.getPlayer();
							game.addPlayer(player);
							textArea.append("Player " + player.getPlayerName() + " has been added at " + new Date() + "\n");
							break;
							
					case 3: 
							PlaceBetParcel placeBetParcel = (PlaceBetParcel)parcel;
							player = placeBetParcel.getPlayer();
							int numberPick = placeBetParcel.getNumberPick();
							int bet = placeBetParcel.getBet();
							boolean betPlaced = game.placeBet(player, numberPick, bet);
							textArea.append("Player " + player.getPlayerName() + " has placed a bet on number " + numberPick + " for $" + bet + "\n");
							outputToClient.writeObject(betPlaced);
							outputToClient.reset();
							break;
							
					case 4: CalculateResultParcel calculateResultParcel = (CalculateResultParcel)parcel;
							int result = calculateResultParcel.getResult();
							game.calculateResult(result);
							break;
							
					case 5: ArrayList<Player> allPlayersArray = game.getAllPlayers();
							
							try {
								
								outputToClient.writeObject(allPlayersArray);
								outputToClient.reset();
								
							}
							
							catch (IOException ex) {
								
								System.err.println(ex);
								
							}
							break;
							
					case 6: RemovePlayerBetParcel removePlayerBetParcel = (RemovePlayerBetParcel)parcel;
							player = removePlayerBetParcel.getPlayer();
							game.removePlayerBet(player);
							break;
							
					case 7: game.resetAllPlayerBets();
							break;
							
					case 8: HashMap<Player, Integer> playerBetsMap = game.getPlayerBets();
					
							try {
				
								outputToClient.writeObject(playerBetsMap);
								outputToClient.reset();
				
							}
			
							catch (IOException ex) {
				
								System.err.println(ex);
				
							}
							break;
							
					case 9: ChangeWheelSettingsParcel changeWheelSettingsParcel = (ChangeWheelSettingsParcel)parcel;
							wheelSize = changeWheelSettingsParcel.getWheelSize();
							initialDelay = changeWheelSettingsParcel.getInitialDelay();
							finalDelay = changeWheelSettingsParcel.getFinalDelay();
							delayIncrement = changeWheelSettingsParcel.getDelayIncrement();
							textArea.append("Changing wheel settings to: \nWheel Size: " + wheelSize + "\nInitial Delay: " + 
									initialDelay + "\nFinal Delay: " + finalDelay + "\nDelayIncrement: " + delayIncrement + "\n");
							break;
							
							//sends back to client the next available port for its callback server to open
					case 10: callbackPort += 500;
							 outputToClient.writeObject(callbackPort);
							 outputToClient.reset();
							 break;
							 
						//	creates a new socket to connect to the clients' callback servers
					case 11: createNewSocket();
							 break;
					
					}
					
				}
			}
			
			catch (IOException | ClassNotFoundException ex) {
				
				System.err.println(ex);
				
			}
			
		}
	}
	
	//	creates a new socket to connect to the clients' callback servers
	// creates an array of sockets to keep track of all clients and communicate with them
	// when the wheel is spinning
	public void createNewSocket() {
		
		Socket socket = null;
		
		try {
			
			socket = new Socket("localhost",callbackPort);
			
			socketArray.add(socket);
			
		}
		
		catch (IOException ex) {
			
			System.err.println(ex);
			
		}
		
	}
	
	public JTextArea getTextArea() {
		
		return textArea;
		
	}
	
	public ArrayList<Socket> getSocketArray() {
		
		return socketArray;
		
	}
	
}
