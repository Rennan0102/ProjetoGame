package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton{
	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver, mousePressed;
	private int buttonX, minX, maxX; // Posição atual do botão do slider - Min e max, posição maxima que o botão pode chegar

	public VolumeButton(int x, int y, int width, int height) {
		super(x + width/2, y, VOLUME_WIDTH, height); // Hitbox do botão do slider
		bounds.x -= VOLUME_WIDTH / 2; // Assim não passa da borda direita
		buttonX = x + width / 2;
		this.x = x;
		this.width = width;
		minX = x + VOLUME_WIDTH / 2;
		maxX = x + width - VOLUME_WIDTH / 2; // VOLUME_WIDTH / 2 serve para ele não passar das bordas do slider
		loadImgs();
	}
	
	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
		imgs = new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT); // Segundo parâmetro igual a zero pq o slider só tem uma linha de imagem
		
		slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT); // 3 vezes width, pq tem três tipos de botões no slider
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;
	}

	public void draw(Graphics g) {
		g.drawImage(slider, x, y, width, height, null); // Desenha o slider primeiro
		g.drawImage(imgs[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null); // Desenha o botão, o height é o mesmo para os dois
	}
	
	public void changeX(int x) {
		if (x < minX)
			buttonX = minX;
		else if (x > maxX)
			buttonX = maxX;
		else
			buttonX = x;
		
		bounds.x = buttonX - VOLUME_WIDTH / 2; // Coloca a hitbox no lugar certo toda vez que o botão é movido
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	// Métodos Getters e Setters
	public boolean isMouseOver() {
		return mouseOver;
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
}