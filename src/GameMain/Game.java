package GameMain;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();  // Aqui damos um "foco", assim, quando fornecemos alguma entrada ela é capturada
		StartGameLoop();
	}
	
	private void StartGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		//
		double timePerFrame = 1000000000.0 / FPS_SET; // duranção do Frame
		long lastFrame =  System.nanoTime();
		long now =  System.nanoTime();
		
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		
		while(true) {
			
			now = System.nanoTime();
			if(now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames ++;
			}
			
			
			frames ++;
			if((System.currentTimeMillis() - lastCheck ) >= 1000) {
				
				lastCheck = System.currentTimeMillis();
				System.out.printf("FPS: %d\n", frames); // frames por segundo 
				frames = 0;
				
				
			}
			
		}
		
	}
}