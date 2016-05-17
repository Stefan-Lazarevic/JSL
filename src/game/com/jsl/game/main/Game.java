package game.com.jsl.game.main;

import engine.ScreenManager;

public class Game extends ScreenManager{
	private static final long serialVersionUID = 1L;
	
	//private GameStateManager gsm;
	
	public Game(){
		
		setScreenEnvironment("Game", 800, 600, 32, false);
		
		gameStart(60, true);
		
		gsm.add(new Menu(gsm, this));
		
	}

	public static void main(String[] args) {
		
		new Game();
		
	}
}
