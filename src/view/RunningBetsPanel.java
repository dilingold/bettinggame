package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//displays a panel of bets as they are being made
@SuppressWarnings("serial")
public class RunningBetsPanel extends JPanel {
	
	private JTextArea textArea;
	
	public RunningBetsPanel(WheelFrame frame) {
		
		textArea = new JTextArea();
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(340,200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.add(textArea);
		textArea.append("New Game" + "\n");
		
	}
	
	public JTextArea getTextArea() {
		
		return textArea;
		
	}

}
