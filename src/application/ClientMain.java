package application;

import java.io.IOException;

import model.GameEngineClientStub;
import model.WheelCallbackServer;

import view.WheelFrame;

//starts a client

public class ClientMain {
	
	public static void main(String[] args) throws IOException {
		
		GameEngineClientStub game = new GameEngineClientStub();
		WheelFrame frame = new WheelFrame(game);
		new WheelCallbackServer(frame,game);

	}


}
