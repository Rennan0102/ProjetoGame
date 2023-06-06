package GameMain;

import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;  // Um novo MouseInputs criado, pq precisamos de outro para usar no addMouseMotionListener
	private int xDelta = 100, yDelta = 100;  // Assim já começam na posição desejada
	
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));  // Melhor implementar a interface para não colocar tudo no construtor
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	public void setRectPos(int x, int y) {  // Método para mover o rect com o mouse
		this.xDelta = x;  // Para isso, tem que deixar o valor de x e y (no rect) como padrão, basta colocar o valor na variável lá em cima
		this.yDelta = y;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(xDelta, yDelta, 200, 100);  // Adionado o xDelta e yDelta, para ser possível mover o retângulo
		
	}
}