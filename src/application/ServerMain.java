package application;

import java.io.IOException;

import model.GameEngineImpl;
import model.GameEngineServerStub;

public class ServerMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	//creates a server
	public static void main(String[] args) throws IOException {

		GameEngineImpl game = new GameEngineImpl();
		new GameEngineServerStub(game);

	}

}
