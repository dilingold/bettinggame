package workingGUIcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import workingGUIview.AddPlayerDialog;



//activated with the add player dialog when user hits 'enter' or 'cancel'
public class AddPlayerDialogListener implements ActionListener {
	
	private AddPlayerDialog dialog;
	
	public AddPlayerDialogListener(AddPlayerDialog dialog) {
		
		this.dialog = dialog;
		
	}
	
	//if user hits 'enter', check that the user has entered a name
	//if not keep the dialog open, with a warning that the user must enter a name
	//if so, add the player to the player array in the GameEngineImpl
	//if the user hits 'cancel', close the dialog box and do nothing
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton buttonClicked = (JButton)e.getSource();

		if (buttonClicked.equals(dialog.getEnter())) {
			
			String name = dialog.getNameInput().getText();
			int points = (int)dialog.getPointsInput().getValue();
			
			if (name.length() == 0) {
				
				dialog.updateErrorMessage("*Must Enter Player Name");
				
			}
			
			else {
				
				dialog.addPlayer(name, points);
				
			}
			
		}
		
		if (buttonClicked.equals(dialog.getCancel())) {
			
			dialog.cancelDialog();
			
		}

	}

}
