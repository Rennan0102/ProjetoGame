package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameMain.Game;
import gamestates.Gamestate;
import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.UMRButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;


public class PauseOverlay {
	private Playing playing;
	
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW ,bgH; // Posição x e y, largura e altura do background
	private SoundButton musicButton, sfxButton; // Criando os botões de música e o de efeitos de som
	private UrmButton unpauseB, replayB, menuB;
	private VolumeButton volumeButton;
	
	public PauseOverlay(Playing playing) {
		this.playing = playing;
		
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButton();
	}
	
	private void createVolumeButton() {
		int vX = (int) (309 * Game.SCALE);
		int vY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	private void createUrmButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE); // Esses botões têm diferentes x's, mas o mesmo y
		int bY = (int) (325 * Game.SCALE);
		
		menuB = new UrmButton(menuX, bY, URM__SIZE, URM__SIZE, 2); // O último parâmetro é o row da imagem
		replayB = new UrmButton(replayX, bY, URM__SIZE, URM__SIZE, 1);
		unpauseB = new UrmButton(unpauseX, bY, URM__SIZE, URM__SIZE, 0);
	}

	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE); // Os dois botões possuem o msm x, mas diferentes y's
		int musicY = (int) (140 * Game.SCALE); // Coordenada y do botão musicButton
		int sfxY = (int) (186 * Game.SCALE); // Coordenada y do botão sfxButton
		
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);
	}

	public void update() {
		musicButton.update();
		sfxButton.update();
		
		menuB.update();
		replayB.update();
		unpauseB.update();
		
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
		// Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null); // Background do pause
		
		// Sound Buttons
		musicButton.draw(g); // Botão de música
		sfxButton.draw(g); // Botão de efeitos de som
		
		// URMButtons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		
		// Volume Slider
		volumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) { // Só para o volume (slider)
		if (volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		
		else if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)){
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted()); // Se está falso, fica true e se está true, fica false
		} else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
		}
		
		else if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.unpauseGame(); // Serve para, quando clicarmos para voltar para o menu, e dermos Play novamente, o jogo não começar pausado
			}
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed())
				System.out.println("Replay Level!"); // Fazemos isso, pq não temos uma função replay
		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		}
		
		musicButton.resetBools(); // Para mostrar a animação de quando solta o botão
		sfxButton.resetBools(); // Para mostrar a animação de quando solta o botão
		
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		if (isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true);
		
		else if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true);
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}