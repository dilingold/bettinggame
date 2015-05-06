package view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

//panel that contains the wheel, results and running bets panels
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private ResultsPanel resultsPanel;
	private WheelPanel wheelPanel;
	private RunningBetsPanel runningBets;
	
	public GamePanel(WheelFrame frame) {
		
		resultsPanel = new ResultsPanel(frame);
		wheelPanel = new WheelPanel(frame);
		runningBets = new RunningBetsPanel(frame);
		
		this.setLayout(new FlowLayout());
		
		this.add(runningBets);
		this.add(resultsPanel);
		this.add(wheelPanel);
		
	}
	
	public WheelPanel getWheelPanel() {
		
		return wheelPanel;
		
	}
	
	public ResultsPanel getResultsPanel() {
		
		return resultsPanel;
		
	}
	
	public RunningBetsPanel getRunningBets() {
		
		return runningBets;
		
	}
}
