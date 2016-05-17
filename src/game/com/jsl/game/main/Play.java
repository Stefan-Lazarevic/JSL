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

public class Play extends GameState{

	ScreenManager sm;
	Ball ball;
	
	String welcome = "Welcome to JSL Engine";
	String info = "This is a play mode.";
	String info2 = "Press P to pause game or ESC to exit.";
	
	public Play(GameStateManager gsm, ScreenManager sm) {
		super(gsm);
		
		this.state = State.PLAYING;
		this.sm = sm;
		
		ball = new Ball();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(Keyboard.keyboard.keyDownOnce(KeyEvent.VK_P)){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Keyboard.keyboard.keyDownOnce(KeyEvent.VK_ESCAPE)){
			System.exit(-1);
		}
		
		ball.update();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		int width = g.getFontMetrics().stringWidth(welcome);
		int width2 = g.getFontMetrics().stringWidth(info);
		int width3 = g.getFontMetrics().stringWidth(info2);
		
		ball.paint(g);
		
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Dialog", Font.BOLD, 16));
		g2d.drawString(welcome, (int)sm.getWidth()/2-width,20);			
		g2d.drawString(info, (int)sm.getWidth()/2-width2,60);
		g2d.drawString(info2, (int)sm.getWidth()/2-width3 + 25,100);
		
	}

}
