package utilz;

import GameMain.Game;

public class Constants {
	
	public static class UI{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140; // O tamanho da largura dos botões
			public static final int B_HEIGHT_DEFAULT = 56; // O tamanho da altura dos botões
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE); // O tamanho da largura dos botões com a escala
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE); // O tamanho da altura dos botões com a escala
		}
		
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42; // Os botões têm o mesmo tamanho tanto na altura quanto na largura
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE); // Tamanho dentro do jogo
		}
		
		public static class UMRButtons{
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM__SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
		}
		
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215; // O HEIGHT do slider é a mesma dos botões
			
			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case RUNNING:
				return 6;
			case IDLE:
				return 5;
			case HIT:
				return 4;
			case JUMP:
			case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3;
			case GROUND:
				return 2;
			case FALLING:
			default:
				return 1;
			}
		}
	}
}