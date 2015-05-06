package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.UUID;

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

import model.SimplePlayer;

import controller.AddPlayerDialogListener;

//opens a dialog when the 'add player' button is pressed, to add a player to the game
@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {
	
	private WheelFrame frame;
	
	private JPanel topPanel;
	private JPanel inputPanel;
	private JPanel bottomPanel;
	
	private JLabel errorMessage;
	private JLabel warningIcon;
	
	private JLabel nameLabel;
	private JLabel pointsLabel;
	
	private JTextField nameInput;
	private JSpinner pointsInput;
	
	private JButton enter;
	private JButton cancel;
	
	private AddPlayerDialogListener dialogListener;

	public AddPlayerDialog(WheelFrame frame) {
		
		this.frame = frame;
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		warningIcon = new JLabel(new ImageIcon("src/view/warning.png"));
		
		errorMessage = new JLabel("Add Player To Game",JLabel.CENTER);
		errorMessage.setPreferredSize(new Dimension(200,40));
		errorMessage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		nameLabel = new JLabel("Please Enter Your Name: ");
		pointsLabel = new JLabel("Please Enter Your Points: ");
		
		nameInput = new JTextField();
		
		nameInput.setColumns(10);
		
		SpinnerModel pointsModel = new SpinnerNumberModel(500,1,10000,100);
		pointsInput = new JSpinner(pointsModel);
		
		enter = new JButton("Enter");
		cancel = new JButton("Cancel");
		
		dialogListener = new AddPlayerDialogListener(this);

		this.setLayout(new BorderLayout());
		this.setLocation(500,300);
		this.setSize(300,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		topPanel.add(warningIcon);
		topPanel.add(errorMessage);
		
		inputPanel.add(nameLabel);
		inputPanel.add(nameInput);
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
	
	public void addPlayer(String name, int points) {
		
		//creates a random and unique id
		String id = UUID.randomUUID().toString();
		
		SimplePlayer player = new SimplePlayer(id, name, points);
		frame.getGame().addPlayer(player);
		
		this.dispose();
		
	}
	
	public void cancelDialog() {
		
		this.dispose();
		
	}
	
	public void updateErrorMessage(String message) {
		
		errorMessage.setText(message);
		
	}
	
	public JTextField getNameInput() {
		
		return nameInput;
		
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
