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
	private int xDelta = 100, yDelta = 100;  // Assim já começam na posição desejada
	private BufferedImage img;  // Usado para importar uma imagem
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean moving = false;
	
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		
		importImg();
		loadAnimations();
		
		addKeyListener(new KeyboardInputs(this));  // Melhor implementar a interface para não colocar tudo no construtor
		setPanelSize();
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void loadAnimations() {
		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
			}
		}
	}

	private void importImg() {
		// Recebendo uma imagem como um InputStream
			InputStream is = getClass().getResourceAsStream("/player_sprites.png");
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	private void setPanelSize() {
		Dimension size = new Dimension(800, 500);  // Dimensão de tamanho 800 x 500
		setPreferredSize(size);  // Recebe somente objetos do tipo Dimension

	}
	
	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void setAnimation() {
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
	}
	
	private void updatePos() {
		if (moving) {
			switch (playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
	}

	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpritesAmount(playerAction)) {
				aniIndex = 0;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateAnimationTick();
		setAnimation();
		updatePos();
		
		g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
	}

}