package engine;

import java.awt.Graphics;

public abstract class GameState {
	
	GameStateManager gsm;
	
	//all possible states
	public enum State{
		MAIN_MENU, PLAYING, LEVEL_SELECT, LEVEL1, LEVEL2, LEVEL3,
		LEVEL4, LEVEL5, LEVEL6, LEVEL7, LEVEL8, LEVEL9,
		LEVEL10, PAUSE, GAME_OVER
	}
	
	public State state;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public State getState()
	{
		return state;
	}
		
	public abstract void update();
	public abstract void paint(Graphics g);

}
