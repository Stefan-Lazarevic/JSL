package game.com.jsl.game.main;

import java.awt.Color;
import java.awt.Graphics;

import engine.ScreenManager;

public class Ball {
	
	int x, y, r;
	int xVelocity, yVelocity;
	int ballSpeed = 3;
	
	public Ball(){
		this.x = 400;
		this.y = 500;
		this.r = 35;
		
		xVelocity = -ballSpeed;
		yVelocity = -ballSpeed;
	}
	
	public void update(){
		x = x + xVelocity;
		y = y + yVelocity;
		
		if(x < 0){
			xVelocity = ballSpeed;
		}
		else if(x > ScreenManager.width - r){
			xVelocity = -ballSpeed;
		}
		else if(y < 0){
			yVelocity = ballSpeed;
		}
		else if(y > ScreenManager.height - r){
			yVelocity = -ballSpeed;
		}
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.yellow);
		g.fillOval(x, y, r, r);
	}
}
