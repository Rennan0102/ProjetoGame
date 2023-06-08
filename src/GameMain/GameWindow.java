package GameMain;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();

		//removendo o setSize()
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //dizendo que é para fechar o código ao pressionar Exit
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false); //não queremos isso, faria a janela ficar com aparência ruim
		jframe.pack(); //falando para adequar o tamanho da janela ao seus componentes(JPanel)
		jframe.setVisible(true); //tem que estar na parte de baixo 
		jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}