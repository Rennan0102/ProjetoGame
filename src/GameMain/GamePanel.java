package GameMain;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;  // Um novo MouseInputs criado, pq precisamos de outro para usar no addMouseMotionListener
	private Game game;

	
	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		addKeyListener(new KeyboardInputs(this));  // Melhor implementar a interface para não colocar tudo no construtor
		setPanelSize();
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}


	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);  // Dimensão de tamanho 1280 x 800
		setPreferredSize(size);  // Recebe somente objetos do tipo Dimension

	}
	
	public void updateGame() {

	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		
	}
	
	public Game getGame() {
		return game;
	}
	
}