package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/********************************
 * 
 * @author Stefan Lazarevic
 *
 *	This is my first public project
 *  In this project i will try to create a simple game engine for all of you game developers there
 *  All of my code is free to use, but an attribution would be very nice in your projects.
 *  
 ********************************/

public class ScreenManager extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private String TITLE;
	
	private GraphicsDevice device;
	private DisplayMode displayMode;
	private boolean fullScreen;
	private double graphicWidth;
	private double graphicHeight;
	public static int width;
	public static int height;
	
	private Thread game_thread;
	private boolean gameRunning;
	
	private float FPS;
	private Float currentFPS = 60f;
	private boolean showFPS;
	
	public GameStateManager gsm;
	
	public ScreenManager(){
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    this.device = environment.getDefaultScreenDevice();
	    
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    graphicWidth = screenSize.getWidth();
	    graphicHeight = screenSize.getHeight();
	    
	    addKeyListener(Keyboard.keyboard);
	    
	    gsm = new GameStateManager();
	        
	}
	
	public void setScreenEnvironment(String TITLE, int WIDTH, int HEIGHT, int BITDEPTH, boolean FULLSCREEN){
		if(WIDTH <= (int)graphicWidth && HEIGHT <= (int)graphicHeight){
			this.TITLE = TITLE;
			this.fullScreen = FULLSCREEN;
			
			frame = new JFrame();
			frame.setTitle(TITLE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
				
			if(FULLSCREEN){
				frame.setUndecorated(true);
				frame.setIgnoreRepaint(true);
				displayMode = new DisplayMode(WIDTH, HEIGHT, BITDEPTH, DisplayMode.REFRESH_RATE_UNKNOWN);
				if (displayMode != null && device.isDisplayChangeSupported()) {
					device.setDisplayMode(displayMode);
				}
				device.setFullScreenWindow(frame);
				width = device.getFullScreenWindow().getWidth();
				height = device.getFullScreenWindow().getHeight();
				frame.add(this);
			}
			else{
				width = WIDTH;
				height = HEIGHT;
				setPreferredSize(new Dimension(WIDTH, HEIGHT));
				frame.add(this);
				frame.pack();
				frame.setLocationRelativeTo(null);
				
			}
			setFocusable(true);
			frame.setVisible(true);
			
			requestFocus();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Resolution is not supported, Please make sure to set resolution more than " + WIDTH + "x" + HEIGHT + " in order to play this game.");
			System.exit(0);
		}
	}

	@Override
	public void run() {
		
		double nsPerUpdate = 1000000000.0 / FPS;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		
		double unprocessed = 0.0;
		
		int fpsCount = 0;
		int updateCount = 0;
		
		boolean canPaint = false;
		
		while(gameRunning){
			long now = System.nanoTime();
			
			unprocessed += (now - lastTime) / nsPerUpdate;
			
			lastTime = now;
			
			if(unprocessed >= 1.0){
				update();
				unprocessed--;
				updateCount++;
				canPaint = true;
			}
			else canPaint = false;
			
			try{
				Thread.sleep(5);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			if(canPaint){
				paint(fpsCount, updateCount);
				fpsCount++;
			}
			
			if(System.currentTimeMillis() - 1000 > timer){
				timer += 1000;
				setCurrentFPS(fpsCount);
				if(showFPS && !fullScreen) frame.setTitle(TITLE + " FPS >> " + fpsCount + " xxx UPS >> " + updateCount);
				fpsCount = 0;
				updateCount = 0;
			}
		}
	}
	
	public void update(){
		Keyboard.poll();
		
		gsm.update();
	}

	public void paint(int FPS, int UPS){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
	
		Graphics g = bs.getDrawGraphics();
		
		
		/************************ PAINT ALL HERE **************************/
		
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		gsm.paint(g);
		
		if(showFPS && fullScreen){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.BOLD, 24));
			g.drawString(getCurrentFPS(), 34, 34);
		}
		
		/******************************************************************/
		
		g.dispose();
		bs.show();
	}
	
	public void gameStart(float FPS, boolean showFPS){
		game_thread = new Thread(this, "Screen Manager Main Thread");
		if(game_thread != null){
			gameRunning = true;
			game_thread.start();
		}
		setShowFPS(showFPS);
		setFPS(FPS);
	}
	
	public float getFPS() {
		return FPS;
	}

	public void setFPS(float fPS) {
		FPS = fPS;
	}
	
	public void setShowFPS(boolean showFPS) {
		this.showFPS = showFPS;
	}
	
	public String getCurrentFPS() {
		return currentFPS.toString();
	}

	public void setCurrentFPS(float currentFPS) {
		this.currentFPS = currentFPS;
	}

}
