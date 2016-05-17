package game.com.jsl.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import engine.GameState;
import engine.GameStateManager;
import engine.Keyboard;
import engine.ScreenManager;

public class Menu extends GameState{

	ScreenManager sm;
	String welcome = "Welcome to JSL Engine";
	String tip = "Press Enter to Start Demo";
	
	public Menu(GameStateManager gsm, ScreenManager sm) {
		super(gsm);
		
		this.state = State.MAIN_MENU;
		this.sm = sm;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(Keyboard.keyboard.keyDownOnce(KeyEvent.VK_ENTER)){
			sm.gsm.add(new Play(sm.gsm, sm));
		}
	}

	@Override
	public void paint(Graphics g) {
		int width = g.getFontMetrics().stringWidth(welcome);
		int width2 = g.getFontMetrics().stringWidth(tip);
		Graphics2D g2d = (Graphics2D)g;	
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Dialog", Font.BOLD, 16));
		g2d.drawString(welcome, (int)sm.getWidth()/2 - width,20);			
		g2d.drawString(tip, (int)sm.getWidth()/2- width2 + 5, 60);
		
	}

}
