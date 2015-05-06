package workingGUIview;

import javax.swing.JMenuBar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import workingGUIcontroller.JMenuListener;


//wheel menu bar with items enabling users to add players, place bets, and spin wheel
@SuppressWarnings("serial")
public class WheelMenuBar extends JMenuBar {
	
	private WheelFrame frame;
	
	private JMenu file;
	
	private JMenuItem addPlayer;
	private JMenuItem placeBet;
	private JMenuItem spinWheel;
	private JMenuItem exit;
	
	private JMenuListener menuListener;
	
	private AddPlayerDialog addPlayerDialog;
	
	public WheelMenuBar(WheelFrame frame) {
		
		this.frame = frame;
		
		file = new JMenu("File");
		
		menuListener = new JMenuListener(this);
		
		addPlayer = new JMenuItem("Add Player");
		placeBet = new JMenuItem("Place Bet");
		spinWheel = new JMenuItem("Spin Wheel");
		exit = new JMenuItem("Exit");
		
		file.add(addPlayer);
		file.add(placeBet);
		file.add(spinWheel);
		file.add(exit);
		
		this.add(file);
		
		addPlayer.addActionListener(menuListener);
		placeBet.addActionListener(menuListener);
		spinWheel.addActionListener(menuListener);
		exit.addActionListener(menuListener);
		
	}
	
	public void startAddPlayerDialog() {
		
		addPlayerDialog = new AddPlayerDialog(frame);
		
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
	
	public JMenuItem getAddPlayer() {
		
		return addPlayer;
		
	}
	
	public JMenuItem getPlaceBet() {
		
		return placeBet;
		
	}
	
	public JMenuItem getSpinWheel() {
		
		return spinWheel;
		
	}
	
	public JMenuItem getExit() {
		
		return exit;
		
	}
	
	public AddPlayerDialog getAddPlayerDialog() {
		
		return addPlayerDialog;
		
	}

}
