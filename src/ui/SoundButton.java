package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton{
	private BufferedImage[][] soundImgs;
	private boolean mouseOver, mousePressed;
	private boolean muted; // Determina se está na primeira linha ou segunda (da imagem sound_button)
	private int rowIndex, colIndex; // Indicadores de linha e de coluna da imagem
	
	public SoundButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		loadSoundImgs();
	}

	private void loadSoundImgs() { 
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS); // Para temporariamente guardar o atlas do botão
		soundImgs = new BufferedImage[2][3]; // 2 linhas, 3 colunas
		for(int j = 0; j < soundImgs.length; j++)
			for(int i = 0; i < soundImgs[j].length; i++)
				soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
	} // "Recortando" a imagem em subimagens e salvando em endereços do array bidimensional
	
	public void update() {
		if (muted)
			rowIndex = 1;
		else 
			rowIndex = 0;
		
		colIndex = 0;
		if (mouseOver)
			colIndex = 1;
		if (mousePressed)
			colIndex = 2;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	
	public void draw(Graphics g) {
		g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
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

	public boolean isMuted() {
		return muted;
	}
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
}
