package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.SettingsDialog;

//activated with the settings dialog when the user hits 'Enter' or 'Cancel'
public class SettingsDialogListener implements ActionListener {

	
	private SettingsDialog dialog;
	
	public SettingsDialogListener(SettingsDialog dialog) {
		
		
		this.dialog = dialog;
		
	}
	
	//if the user hits 'enter', get the values entered and change the wheel settings
	//if the user hits 'cancel', close the dialog
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton buttonClicked = (JButton)e.getSource();

		if (buttonClicked.equals(dialog.getEnter())) {
			
			int wheelSize = (int)dialog.getWheelSizeInput().getValue();
			int initialDelay = (int)dialog.getInitialDelayInput().getValue();
			int finalDelay = (int)dialog.getFinalDelayInput().getValue();
			int delayIncrement = (int)dialog.getDelayIncrementInput().getValue();
			
			dialog.changeWheelSettings(wheelSize, initialDelay, finalDelay, delayIncrement);
			
		}
			
		if (buttonClicked.equals(dialog.getCancel())) {
				
			dialog.cancelDialog();
			
		}
		
	}

}
