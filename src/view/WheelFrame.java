package view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;


import model.GameEngineClientStub;

//opens wheel frame with a reference to the game engine client stub
@SuppressWarnings("serial")
public class WheelFrame extends JFrame {

	private GameEngineClientStub game;
	
	private WheelMenuBar menuBar;
	private WheelToolBar toolBar;
	private GamePanel gamePanel;
	
	public WheelFrame(GameEngineClientStub game) throws IOException {
		
		this.game = game;
		
		menuBar = new WheelMenuBar(this);
		toolBar = new WheelToolBar(this);
		gamePanel = new GamePanel(this);
		
		this.setLayout(new BorderLayout());
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(toolBar, BorderLayout.SOUTH);
		this.add(gamePanel, BorderLayout.CENTER);
		
		this.setSize(725,800);
		this.setLocation(50,80);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public GameEngineClientStub getGame() {
		
		return game;
		
	}
	
	public GamePanel getGamePanel() {
		
		return gamePanel;
		
	}
	
	public WheelMenuBar getWheelMenuBar() {
		
		return menuBar;
		
	}
	
	public WheelToolBar getWheelToolBar() {
		
		return toolBar;
		
	}
	
}
