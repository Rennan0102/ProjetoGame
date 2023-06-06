package GameMain;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();
		jframe.setSize(400, 400);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);  // Serve para a janela iniciar no centro da tela
		jframe.setVisible(true);
	}
}