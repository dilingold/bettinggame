package workingGUIview;


import javax.swing.JButton;
import javax.swing.JToolBar;

import workingGUIcontroller.ToolBarListener;


//creates a tool bar with buttons allowing users to add a player, place a bet
//spin the wheel and change the wheel settings
@SuppressWarnings("serial")
public class WheelToolBar extends JToolBar {

	private WheelFrame frame;
	
	private JButton addPlayer;
	private JButton placeBet;
	private JButton spinWheel;
	private JButton exit;
	private JButton settings;
	
	private ToolBarListener toolBarListener;
	
	public WheelToolBar(WheelFrame frame) {
		
		this.frame = frame;
		
		this.setOrientation(HORIZONTAL);
		
		addPlayer = new JButton("Add Player");
		placeBet = new JButton("Place Bet");
		spinWheel = new JButton("Spin Wheel");
		exit = new JButton("Exit");
		settings = new JButton("Wheel Settings");

		this.add(addPlayer);
		this.add(placeBet);
		this.add(spinWheel);
		this.add(exit);
		this.add(settings);
		
		toolBarListener = new ToolBarListener(this);
		
		addPlayer.addActionListener(toolBarListener);
		placeBet.addActionListener(toolBarListener);
		spinWheel.addActionListener(toolBarListener);
		exit.addActionListener(toolBarListener);
		settings.addActionListener(toolBarListener);
		
	}
	
	public void startAddPlayerDialog() {
		
		new AddPlayerDialog(frame);
		
	}
	
	public void startPlaceBetDialog() {
		
		new PlaceBetDialog(frame);
		
	}
	
	public void spinWheel() {
	
		frame.getGamePanel().getResultsPanel().clearResults();
		frame.getGamePanel().getWheelPanel().spinWheel(40, 1, 200, 10);
		
	}
	
	public void exitGame() {
		
		System.exit(0);
		
	}
	
	public void goToSettings() {
		
		new SettingsDialog(frame);
		
	}
	
	public JButton getAddPlayerButton() {
		
		return addPlayer;
		
	}
	
	public JButton getPlaceBetButton() {
		
		return placeBet;
		
	}
	
	public JButton getSpinButton() {
		
		return spinWheel;
		
	}
	
	public JButton getExit() {
		
		return exit;
		
	}
	
	public JButton getSettings() {
		
		return settings;
		
	}
	
	
}
