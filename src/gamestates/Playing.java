package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import GameMain.Game;
import entities.Player;
import levels.LevelManager;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false; // Dependendo do seu valor, irá pausar ou não

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this); // Temos que passar essa classe como parâmetro
	}

	@Override
	public void update() {
		if (!paused) { // Não queremos que continue jogando quando está pausado
			levelManager.update();
			player.update();
		} else {
			pauseOverlay.update(); // Só atualiza o menu de pause, o level e player ficam congelados
		}
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		
		if (paused)
			pauseOverlay.draw(g); // Para não ficar desenhando na tela o menu de pause, temos que colocar uma condição
	}

	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_P:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			// gamePanel.getGame().getPlayer(). (antes precisava chamar tudo isso)
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}		
	}
	
	public void unpauseGame() {
		paused = false;
	}
	
	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}
}