import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


abstract class GameState {
	
	protected Main main;
	protected JFrame frame;
	
	private int id;
	
	public GameState(int id, Main main, JFrame frame){
		this.id = id;
		this.main = main;
		this.frame = frame;
	}
	
	abstract public void update();
	
	abstract public void render(Graphics2D g2d);
	
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}
	
	public int getID(){
		return id;
	}
}
