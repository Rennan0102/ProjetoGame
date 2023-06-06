package GameMain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;  // Um novo MouseInputs criado, pq precisamos de outro para usar no addMouseMotionListener
	private float xDelta = 100, yDelta = 100;  // Assim já começam na posição desejada
	private float xDir = 1f,yDir = 1f; // o video era 0.001f mas nao roda aqui
	private int frames;
	private long lastCheck = 0;
	private Color color = new Color(150,20,90); //
	private Random random; //
	
	
	public GamePanel() {
		random  = new Random();
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));  // Melhor implementar a interface para não colocar tudo no construtor
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void changeXDelta(int value) {
		this.xDelta += value;
		//repaint();
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		//repaint();
	}
	
	public void setRectPos(int x, int y) {  // Método para mover o rect com o mouse
		this.xDelta = x;  // Para isso, tem que deixar o valor de x e y (no rect) como padrão, basta colocar o valor na variável lá em cima
		this.yDelta = y;
		//repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateRectangle();
		g.setColor(color); //
		g.fillRect((int)xDelta,(int) yDelta, 200, 50);  // Adionado o xDelta e yDelta, para ser possível mover o retângulo
		
	
		
		//repaint();
	}

	private void updateRectangle() {
		xDelta += xDir;
		if(xDelta > 400 || xDelta < 0 ) {
			xDir *=-1; // vai para direção oposta
			color = getRndColor();//
		}
		
		yDelta += yDir;
		if(yDelta > 400 || yDelta < 0 ) {
			yDir *=-1;
			color = getRndColor();//
		}
	}
//fazer cores de retang aleatorios 
	private Color getRndColor() {
		int r = random.nextInt(255);
		int b = random.nextInt(255);
		int g = random.nextInt(255);
		return new Color(r,b,g);
	}
}