package GameMain;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();  // Aqui damos um "foco", assim, quando fornecemos alguma entrada ela é capturada
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}