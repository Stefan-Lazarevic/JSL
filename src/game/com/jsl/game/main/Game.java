package game.com.jsl.game.main;

import engine.ScreenManager;

public class Game{
	
	private static ScreenManager screenManager;

	public static void main(String[] args) {
		
		screenManager = new ScreenManager();
		
		screenManager.setScreenEnvironment("Game", 600, 600, 32, false);
		
		screenManager.gameStart(60, true);
		
	}
}
