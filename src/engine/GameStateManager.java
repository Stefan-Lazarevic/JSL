package engine;

import java.awt.Graphics;
import java.util.Stack;

import engine.GameState.State;




public class GameStateManager {

	private Stack<GameState> states = new Stack<GameState>();
	
	
	public GameStateManager() {
		
	}
	
	public State getState()
	{
		return states.peek().getState();
	}
	
	public Stack<GameState> getStates()
	{
		return states;
	}
	
	public void paint(Graphics g)
	{
		states.peek().paint(g);
	}
	
	public void update()
	{
		states.peek().update();
	}
	
	public void add(GameState gs)
	{
		states.add(gs);
	}
	
	public void pop()
	{
		states.pop();
	}
	
	public void push(GameState gs)
	{
		states.push(gs);
	}

}
