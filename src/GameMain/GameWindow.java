package GameMain;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);  // Serve para a janela iniciar no centro da tela
		jframe.setResizable(false);  // Não queremos isso, faria a janela ficar com aparência ruim
		jframe.pack();  // Falando para adequar o tamanho da janela ao seus componentes(JPanel)
		jframe.setVisible(true);
	}
}