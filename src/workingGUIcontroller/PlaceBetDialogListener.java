package workingGUIcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import workingGUIview.PlaceBetDialog;



//activated from the place bet dialog when the user hits 'enter' or 'cancel'
public class PlaceBetDialogListener implements ActionListener {

	private PlaceBetDialog dialog;
	
	public PlaceBetDialogListener(PlaceBetDialog dialog) {

		this.dialog = dialog;
		
	}
	
	//if the user hits the 'enter' button, check the player array list from
	//GameEngineImpl that the player exists
	//if he does exist, place the bet, otherwise keep the dialog open
	//with a warning message
	//if the user hits 'cancel', close the dialog
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton buttonClicked = (JButton)e.getSource();

		if (buttonClicked.equals(dialog.getEnter())) {
			
			String playerName = dialog.getPlayerNameInput().getText().trim();
			int numberPick = (int)dialog.getNumberPickInput().getValue();
			int points = (int)dialog.getPointsInput().getValue();
			
			if (playerName.length() == 0) {
				
				dialog.updateErrorMessage("*Must Enter Player Name");
				
			}
			
			else {
				
				boolean playerNameValidated = dialog.validatePlayerName(playerName);
				
				if (playerNameValidated) {
					
					dialog.placeBet(playerName, numberPick, points);
					
				}
				
				else dialog.updateErrorMessage("*Invalid Player Name. Try Again.");
				
			}
			
		}
		
		if (buttonClicked.equals(dialog.getCancel())) {
			
			dialog.cancelDialog();
				
			}
			
		}

}
