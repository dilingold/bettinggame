package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import model.interfaces.Player;
import controller.PlaceBetDialogListener;

//dialog to allow users to place a bet
@SuppressWarnings("serial")
public class PlaceBetDialog extends JDialog {

	private WheelFrame frame;
	
	private JPanel topPanel;
	private JPanel inputPanel;
	private JPanel bottomPanel;
	
	private JLabel errorMessage;
	private JLabel warning;
	
	private JLabel playerNameLabel;
	private JLabel numberPickLabel;
	private JLabel pointsLabel;
	
	private JTextField playerNameInput;
	private JSpinner numberPickInput;
	private JSpinner pointsInput;
	
	private JButton enter;
	private JButton cancel;
	
	private PlaceBetDialogListener dialogListener;

	public PlaceBetDialog(WheelFrame frame) {
		
		this.frame = frame;
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		warning = new JLabel(new ImageIcon("src/view/warning.png"));
		
		errorMessage = new JLabel("Enter Bet Details",JLabel.CENTER);
		errorMessage.setPreferredSize(new Dimension(200,40));
		errorMessage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		playerNameLabel = new JLabel("Please Enter Your Name: ");
		numberPickLabel = new JLabel("Please Enter Your Number Pick: ");
		pointsLabel = new JLabel("Please Enter Your Bet Amount: ");
		
		playerNameInput = new JTextField();
		
		playerNameInput.setColumns(10);
		
		SpinnerModel numberPickModel = new SpinnerNumberModel(40,1,40,1);
		numberPickInput = new JSpinner(numberPickModel);
		
		SpinnerModel pointsModel = new SpinnerNumberModel(500,1,10000,100);
		pointsInput = new JSpinner(pointsModel);
		
		enter = new JButton("Enter");
		cancel = new JButton("Cancel");
		
		dialogListener = new PlaceBetDialogListener(this);

		this.setLayout(new BorderLayout());
		this.setLocation(500,300);
		this.setSize(300,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		topPanel.add(warning);
		topPanel.add(errorMessage);
		
		inputPanel.add(playerNameLabel);
		inputPanel.add(playerNameInput);
		inputPanel.add(numberPickLabel);
		inputPanel.add(numberPickInput);
		inputPanel.add(pointsLabel);
		inputPanel.add(pointsInput);
		
		bottomPanel.add(enter);
		bottomPanel.add(cancel);
		
		enter.addActionListener(dialogListener);
		cancel.addActionListener(dialogListener);
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(inputPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
	}
	
	//goes through player array from GameEngineImpl to make sure player entered exists in the game
	public boolean validatePlayerName(String playerName) {
		
		boolean validated = false;
		
		ArrayList<Player> playerList = (ArrayList<Player>) frame.getGame().getAllPlayers();
		
		for (int i = 0; i < playerList.size(); i++) {
			
			Player player = playerList.get(i);
			
			String name = player.getPlayerName();
			
			if (name.equals(playerName)) {
				
				validated = true;
				
			}
			
		}
		
		return validated;
		
	}
	
	//places a bet in the game
	public void placeBet(String playerName, int numberPick, int points) {
		
		ArrayList<Player> playerList = (ArrayList<Player>) frame.getGame().getAllPlayers();
		
		for (int i = 0; i < playerList.size(); i++) {
			
			Player player = playerList.get(i);
			String name = player.getPlayerName();
			
			if (playerName.equals(name)) {
				
				boolean betPlaced = frame.getGame().placeBet(player, numberPick, points);
				
				if (betPlaced) {
					
					frame.getGamePanel().getRunningBets().getTextArea().append("Player " + player.getPlayerName() + " placed a bet on " 
							+ numberPick + " for $" + points + "\n");
					
				}
				
			}
			
		}
		
		this.dispose();
		
	}
	
	public void cancelDialog() {
		
		this.dispose();
		
	}
	
	public void updateErrorMessage(String message) {
		
		errorMessage.setText(message);
		
	}
	
	public JTextField getPlayerNameInput() {
		
		return playerNameInput;
		
	}
	
	public JSpinner getNumberPickInput() {
		
		return numberPickInput;
		
	}
	
	public JSpinner getPointsInput() {
		
		return pointsInput;
		
	}
	
	public JButton getEnter() {
		
		return enter;
		
	}
	
	public JButton getCancel() {
		
		return cancel;
		
	}
	
}
	

