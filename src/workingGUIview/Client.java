package workingGUIview;

import java.io.IOException;

import workingGUIview.WheelFrame;

import model.GameEngineImpl;

public class Client {
	
	public static void main(String[] args) throws IOException {

		GameEngineImpl game = new GameEngineImpl();
		new WheelFrame(game);

	}

}
