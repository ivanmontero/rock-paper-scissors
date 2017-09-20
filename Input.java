import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    Main main;

    public Input(Main main) {
        this.main = main;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        main.getCurrentGameState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        main.getCurrentGameState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        main.getCurrentGameState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        main.getCurrentGameState().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        main.getCurrentGameState().mouseExited(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        main.getCurrentGameState().keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        main.getCurrentGameState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        main.getCurrentGameState().keyReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        main.getCurrentGameState().mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        main.getCurrentGameState().mouseMoved(e);
    }

}
