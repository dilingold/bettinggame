package workingGUIcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import workingGUIview.WheelMenuBar;



//activated when a JMenu item is clicked
public class JMenuListener implements ActionListener {

	private WheelMenuBar menuBar;
	
	public JMenuListener(WheelMenuBar menuBar) {

		this.menuBar = menuBar;
		
	}
	
	//if the user clickes the 'add player' item, start the add player dialog
	//if the user clicks to 'place bet' item, start the place bet dialog
	//if the user clicks the 'spin' item, initiate a wheel spin
	//if the user clicks 'exit' then exit the game
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem menuItem = new JMenuItem();
		
		menuItem = (JMenuItem)e.getSource();
		
		if (menuItem.equals(menuBar.getAddPlayer())) {
			
			menuBar.startAddPlayerDialog();
			
		}
		
		if (menuItem.equals(menuBar.getPlaceBet())) {
			
			menuBar.startPlaceBetDialog();
			
		}
		
		if (menuItem.equals(menuBar.getSpinWheel())) {
			
			menuBar.spinWheel();
			
		}
		
		if (menuItem.equals(menuBar.getExit())) {
			
			menuBar.exitGame();
			
		}

	}

}
