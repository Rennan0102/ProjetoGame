package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = B_WIDTH / 2; // Para pegar o centro da tela
	private Gamestate state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds; // Hitbox do botão
	
	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		this.xPos = xPos; // Posição x da imagem
		this.yPos = yPos; // Posição y da imagem
		this.rowIndex = rowIndex; // Indicador de linha
		this.state = state;
		loadImgs(); // Para carregar as imagens
		initBounds();
	}

	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
	}

	private void loadImgs() {
		imgs = new BufferedImage[3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if (mouseOver) // Se o mouse estiver em cima do botão troca de imagem
			index = 1;
		if (mousePressed) // Se o mouse clicar no botão troca de imagem
			index = 2;
	}

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
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void applyGamestate() {
		Gamestate.state = state;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
}
