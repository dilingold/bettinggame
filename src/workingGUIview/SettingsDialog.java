package workingGUIview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import workingGUIcontroller.SettingsDialogListener;

import application.WheelSettings;


//opens a dialog when the user clicks 'wheel settings' button
//allows users to change the wheel settings
@SuppressWarnings("serial")
public class SettingsDialog extends JDialog {

	@SuppressWarnings("unused")
	private WheelFrame frame;
	
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private JLabel wheelSizeLabel;
	private JLabel initialDelayLabel;
	private JLabel finalDelayLabel;
	private JLabel delayIncrementLabel;
	
	private JSpinner wheelSizeInput;
	private JSpinner initialDelayInput;
	private JSpinner finalDelayInput;
	private JSpinner delayIncrementInput;
	
	private JButton enter;
	private JButton cancel;
	
	private SettingsDialogListener dialogListener;
	
	private WheelSettings wheelSettings;
	
	public SettingsDialog(WheelFrame frame) {
		
		this.frame = frame;
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		wheelSizeLabel = new JLabel("Wheel Size: ");
		initialDelayLabel = new JLabel("Initial Delay: ");
		finalDelayLabel = new JLabel("Final Delay: ");
		delayIncrementLabel = new JLabel("Delay Increment: ");
		
		int wheelSize = 40;
		int initialDelay = 1;
		int finalDelay = 200;
		int delayIncrement = 10;
		
		if (wheelSettings != null) {
			
			wheelSize = wheelSettings.getWheelSize();
			initialDelay = wheelSettings.getInitialDelay();
			finalDelay = wheelSettings.getFinalDelay();
			delayIncrement = wheelSettings.getDelayIncrement();
			
		}
		
		SpinnerModel wheelSizeModel = new SpinnerNumberModel(wheelSize,1,40,1);
		wheelSizeInput = new JSpinner(wheelSizeModel);
		
		SpinnerModel initialDelayModel = new SpinnerNumberModel(initialDelay,1,100,10);
		initialDelayInput = new JSpinner(initialDelayModel);
		
		SpinnerModel finalDelayModel = new SpinnerNumberModel(finalDelay,100,1000,10);
		finalDelayInput = new JSpinner(finalDelayModel);
		
		SpinnerModel delayIncrementModel = new SpinnerNumberModel(delayIncrement,1,100,50);
		delayIncrementInput = new JSpinner(delayIncrementModel);
		
		enter = new JButton("Enter");
		cancel = new JButton("Cancel");
		
		dialogListener = new SettingsDialogListener(this);

		this.setLayout(new BorderLayout());
		this.setLocation(500,300);
		this.setSize(200,200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		topPanel.add(wheelSizeLabel);
		topPanel.add(wheelSizeInput);
		topPanel.add(initialDelayLabel);
		topPanel.add(initialDelayInput);
		topPanel.add(finalDelayLabel);
		topPanel.add(finalDelayInput);
		topPanel.add(delayIncrementLabel);
		topPanel.add(delayIncrementInput);
		
		bottomPanel.add(enter);
		bottomPanel.add(cancel);
		
		enter.addActionListener(dialogListener);
		cancel.addActionListener(dialogListener);
		
		this.add(topPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		
	}
	
	public void changeWheelSettings(int wheelSize, int initialDelay, int finalDelay, int delayIncrement) {
		
//		wheelSettings = new WheelSettings(frame.getGame(), wheelSize, initialDelay, finalDelay, delayIncrement);
		
		this.dispose();
		
	}
	
	public void cancelDialog() {
		
		this.dispose();
		
	}
	
	public JSpinner getWheelSizeInput() {
		
		return wheelSizeInput;
		
	}
	
	public JSpinner getInitialDelayInput() {
		
		return initialDelayInput;
		
	}
	
	public JSpinner getFinalDelayInput() {
		
		return finalDelayInput;
		
	}
	
	public JSpinner getDelayIncrementInput() {
		
		return delayIncrementInput;
		
	}

	public JButton getEnter() {
		
		return enter;
		
	}
	
	public JButton getCancel() {
		
		return cancel;
		
	}
	
}
