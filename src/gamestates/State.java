package gamestates;

import java.awt.event.MouseEvent;

import GameMain.Game;
import ui.MenuButton;

public class State { // Será a super classe de todos os nossos gamestates
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	public Game getGame() {
		return game;
	}
}
