package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.WheelToolBar;

//activated when a button is clicked on the tool bar
public class ToolBarListener implements ActionListener {

	private WheelToolBar toolBar;
	
	public ToolBarListener(WheelToolBar toolBar) {

		this.toolBar = toolBar;
		
	}
	
	//if the user clicks 'add player' button, open the add player dialog
	//if the user clicks 'place bet' button, open the place bet dialog
	//if the user clicks 'spin' button, spin the wheel
	//if the user clicks 'wheel settings' button, open the wheel settings dialog
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton)e.getSource();

		if (source == toolBar.getAddPlayerButton()) {
			
			toolBar.startAddPlayerDialog();
			
		}
		
		if (source == toolBar.getPlaceBetButton()) {
			
			toolBar.startPlaceBetDialog();
			
		}

		if (source == toolBar.getSpinButton()) {
	
			toolBar.spinWheel();
	
		}
		
		if (source == toolBar.getExit()) {
			
			toolBar.exitGame();
			
		}
		
		if (source == toolBar.getSettings()) {
			
			toolBar.goToSettings();
			
		}
		
	}

}
