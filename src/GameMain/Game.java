package GameMain;

public class Game {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();  // Aqui damos um "foco", assim, quando fornecemos alguma entrada ela é capturada
	}
}