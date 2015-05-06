package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;

//creates a panel in the frame which displays players that have bet while the wheel is turning
//and updates when the wheel has finished turning with the result and remaining points
@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {

	private WheelFrame frame;
	
	private JLabel[][] resultsGrid;
	
	private JLabel head;
	private JLabel name;
	private JLabel numPick;
	private JLabel result;
	private JLabel points;
	
	private GridBagLayout gridBag;
	private GridBagConstraints c;
	
	@SuppressWarnings("rawtypes")
	private HashMap playerList;
	boolean resultsDisplayed;
	int i;
	
	public ResultsPanel(WheelFrame frame) {
		
		this.frame = frame;
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(340,200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		
		gridBag = new GridBagLayout();
		c = new GridBagConstraints();
		
		this.setLayout(gridBag);
		
		playerList = new HashMap<Player,Integer>();
		
		resultsGrid = new JLabel[4][100];
		
		head = new JLabel("Results", JLabel.LEFT);
		head.setFont(new Font("Sans Serif",Font.BOLD,14));
		
		name = new JLabel();
		result = new JLabel();
		points = new JLabel();

		resultsGrid[0][0] = new JLabel("    Name:    ", JLabel.LEFT);
		resultsGrid[1][0] = new JLabel("  Num:  ", JLabel.LEFT);
		resultsGrid[2][0] = new JLabel(" Result: ", JLabel.LEFT);
		resultsGrid[3][0] = new JLabel(" Points: ", JLabel.LEFT);

		name = resultsGrid[0][0];
		numPick = resultsGrid[1][0];
		result = resultsGrid[2][0];
		points = resultsGrid[3][0];
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridBag.setConstraints(head, c);
		
		name.setPreferredSize(new Dimension(100,20));
		numPick.setPreferredSize(new Dimension(50,20));
		result.setPreferredSize(new Dimension(50,20));
		points.setPreferredSize(new Dimension(50,20));

		this.add(head);
		this.add(name);
		this.add(numPick);
		this.add(result);
		this.add(points); 
		
		for (int i = 1; i <= 10; i++) {
			
			resultsGrid[0][i] = new JLabel("--");
			resultsGrid[0][i].setPreferredSize(new Dimension(100,20));
			this.add(resultsGrid[0][i]);
			
			resultsGrid[1][i] = new JLabel("--");
			resultsGrid[1][i].setPreferredSize(new Dimension(50,20));
			this.add(resultsGrid[1][i]);
			
			resultsGrid[2][i] = new JLabel("--");
			resultsGrid[2][i].setPreferredSize(new Dimension(50,20));
			this.add(resultsGrid[2][i]);
			
			resultsGrid[3][i] = new JLabel("--");
			resultsGrid[3][i].setPreferredSize(new Dimension(50,20));
			this.add(resultsGrid[3][i]);
			
			c.gridwidth = GridBagConstraints.REMAINDER;
			gridBag.setConstraints(resultsGrid[3][i], c);
			
		}
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridBag.setConstraints(points, c);
		
		resultsDisplayed = false;
		
	}
	
	//displays players betting while wheel is turning
	@SuppressWarnings("rawtypes")
	public void displayPlayers() {
		
		playerList = frame.getGame().getPlayerBets();
		
		Player player;
		int numberPick;
		String numberPickString;
		
		Iterator it = playerList.entrySet().iterator();
		
		i = 1;
		
		while (it.hasNext()) {
			
			Map.Entry pair = (Map.Entry)it.next();
			
			player = (Player)pair.getKey();
			numberPick = (Integer)pair.getValue();
	
			resultsGrid[0][i].setText((player.getPlayerName()));
			
			numberPickString = Integer.toString(numberPick);
			
			resultsGrid[1][i].setText((numberPickString));
			
			resultsGrid[2][i].setText("--");
			
			resultsGrid[3][i].setText("--");
			
			i++;
			resultsDisplayed = true;
			
		}
		
	}
	
	//displays results once the wheel has stopped turning
	@SuppressWarnings("rawtypes")
	public void displayResults(int result) {
		
		playerList = frame.getGame().getPlayerBets();
		
		Player player;
		int numberPick;
		String numberPickString;
		String resultString;
		
		Iterator it = playerList.entrySet().iterator();
		
		i = 1;
		
		while (it.hasNext()) {
			
			Map.Entry pair = (Map.Entry)it.next();
			
			player = (Player)pair.getKey();
			numberPick = (Integer)pair.getValue();
	
			resultsGrid[0][i].setText((player.getPlayerName()));
			
			numberPickString = Integer.toString(numberPick);
			
			resultsGrid[1][i].setText((numberPickString));
			
			if (numberPick == result) {
				
				resultString = "Win";
				
			}
			
			//if the user entered a number pick greater than the wheel size, the result column is blank
			else if (numberPick > frame.getGamePanel().getWheelPanel().getWheelSize()) {
				
				resultString = "---";
				
			}
			
			else resultString = "Lose";
			
			resultsGrid[2][i].setText((resultString));
			
			resultsGrid[3][i].setText((Integer.toString(player.getPoints())));
			
			i++;
			resultsDisplayed = true;
			
		}
		
		//reset text area in the running bets panel
		frame.getGamePanel().getRunningBets().getTextArea().setText("New Game" + "\n");
		frame.getGame().resetAllPlayerBets();
		
	}
	
	//when a new spin is initialized, clear the previous game's results
	public void clearResults() {
		
		if (resultsDisplayed) {
			
			
		
			for (int j = 1; j <= i; j++) {
				
				resultsGrid[0][j].setText("--");
				
				resultsGrid[1][j].setText("--");
				
				resultsGrid[2][j].setText("--");
				
				resultsGrid[3][j].setText("--");
				
			}
			
		}
		
		
	}
	
}
