package GameMain;

import java.awt.Graphics;

import entities.Player;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	private Player player;
	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();  // Aqui damos um "foco", assim, quando fornecemos alguma entrada ela é capturada
		
		
		
		StartGameLoop();
	
		
	}
	
	private void initClasses() {
		player = new Player(200, 200);
		
	}

	private void StartGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		player.update();
	}
	
	public void render(Graphics g) {
		player.render(g);
	}

	@Override
	public void run() {  //Game Loop
		//
		double timePerFrame = 1000000000 / FPS_SET; // duranção do Frame
		double timePerUpdate = 1000000000 / UPS_SET; //tempo de frequência, tempo entre updates
		//removendo now e lastFrame
		
		long previousTime = System.nanoTime(); // mesmo que o lastFrame, só que para update
		
		int frames = 0;
		int updates = 0; //mesmo que frames
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0; //delta de tempo, só que para uptdate
		double deltaF = 0; //para frame
		
		while(true) {
			
			//removendo now
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate; //deltaU será 1.0 ou mais quando a duração desde o último update for igual ou maior que timePerUpdate
			//Ex.: deltaU = 1.1
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
				// deltaU = 0.1
			}//todo o tempo que seria "jogado fora" será guardado em deltaU para o próximo update para que ele aconteça um pouquinho mais cedo
			
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			if((System.currentTimeMillis() - lastCheck ) >= 1000) {
				
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates); // frames por segundo 
				frames = 0;
				updates = 0;
				
			}
			
		}
		
	}
	
	
	public void windowFocusLost() {
		player.resetDirBooleans();
	}
	
	public Player getPlayer() {
		return player;
	}
	
}