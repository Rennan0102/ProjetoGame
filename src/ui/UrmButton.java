package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.UMRButtons.*;

public class UrmButton extends PauseButton {
	private BufferedImage[] imgs;
	private int rowIndex, index; // index depende do mouse event
	private boolean mouseOver, mousePressed;

	public UrmButton(int x, int y, int width, int height, int rowIndex) { // Esse rowIndex é para cada coluna da imagem
		super(x, y, width, height);
		this.rowIndex = rowIndex;
		loadImgs();
	}

	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
		imgs = new BufferedImage[3]; // 3 pq são 3 colunas
		
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE,URM_DEFAULT_SIZE);
	}

	public void update() {
		index = 0; // Toda vez que chamar o método, temos que ter certeza que está resetado
		
		if(mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;
	}

	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, URM__SIZE, URM__SIZE, null);
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