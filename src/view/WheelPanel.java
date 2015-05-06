package view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import javax.swing.JPanel;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

import model.interfaces.GameEngine;
import model.interfaces.WheelCallback;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Color;

//a panel containing the wheel and a label to display the next number
@SuppressWarnings("serial")
public class WheelPanel extends JPanel implements WheelCallback {

	private WheelFrame frame;
	
	private ArrayList<ImageIcon> wheelImageArray;
	private JLabel wheelLabel;
	private JLabel nextNumberLabel;
	
	private int wheelImageOrder;
	
	private int wSize;
	
	public WheelPanel(WheelFrame frame) {
		
		this.frame = frame;

		wheelImageArray = new ArrayList<ImageIcon>();
		
		wheelImageArray.add(new ImageIcon("src/view/wheel1.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel2.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel3.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel4.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel5.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel6.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel7.png"));
		wheelImageArray.add(new ImageIcon("src/view/wheel8.png"));
		
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.setBackground(new Color(255,255,255));
		
		wheelLabel = new JLabel(wheelImageArray.get(1));
		wheelImageOrder = 1;
		nextWheelImage();
		nextNumberLabel = new JLabel("---",JLabel.CENTER);
		nextNumberLabel.setFont(new Font("Sans Serif",Font.BOLD,20));
		nextNumberLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));		
		nextNumberLabel.setPreferredSize(new Dimension(60,50));
		
		this.add(wheelLabel);
		this.add(nextNumberLabel);
		
	}
	
	//creates 2  threads, one to spin the wheel and one to display the players betting
	public void spinWheel(int size, int initDelay, int finDelay, int increment) {
		
		this.wSize = size;
		final int wheelSize = size;
		final int delayIncrement = increment;
		final int finalDelay = finDelay;
		final int initialDelay = initDelay;
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				
				frame.getGame().spin(wheelSize, initialDelay, finalDelay, delayIncrement, frame.getGamePanel().getWheelPanel());
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				
				frame.getGamePanel().getResultsPanel().displayPlayers();
				frame.getGamePanel().getRunningBets().getTextArea().append("wheel spinning ..." + "\n");
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		
		
	}

	@Override
	public void nextNumber(int nextNumber, GameEngine engine) {

		String currentNumberString = Integer.toString(nextNumber);
		
		spinning();
		nextNumberLabel.setText(currentNumberString);		

	}

	@Override
	public void result(int result, GameEngine engine) {

		frame.getGamePanel().getResultsPanel().displayResults(result);
		
	}
	
	public int nextWheelImage() {
		
		wheelImageOrder++;
		
		if (wheelImageOrder > 7) {
			
			wheelImageOrder = 1;
			
		}
		
		return wheelImageOrder;
		
	}
	
	public void spinning() {
		
		wheelLabel.setIcon(wheelImageArray.get(wheelImageOrder));
		nextWheelImage();
		
	}
	
	public int getWheelSize() {
		
		return wSize;
		
	}
	
}

	

