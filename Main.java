import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {

	/*
    Ivan's Swing game framework: Designed for window creating and the ability to render and switch between
	different "scenes" easily and efficiently. Utilizes the abstract class GameState as the different
	"scenes" with their own independent rendering and update functions, of which is called from the
	loop within this class.
	 */

    public static final int GAME = 0;

    private int currentGameState;
    private GameState[] gameStates;
    private Input input;
    private JFrame frame;
    private Timer timer;
    private boolean initialized;

    public Main(String title) {
        frame = new JFrame(title);
        frame.add(this);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        gameStates = new GameState[]{
                new Game(GAME, this, frame)
                //new GameStateName(MENU, this, frame)
                //declare gamestates here is this order: new GameStateName(int stateID, this, frame);
        };
        timer = new Timer(16, this);
        currentGameState = GAME;
        input = new Input(this);

        this.setFocusable(true);
        this.addMouseMotionListener(input);
        this.addMouseListener(input);
        this.addKeyListener(input);
        this.grabFocus();

        initialized = true;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameStates[currentGameState].update();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (initialized) {
            gameStates[currentGameState].render(g2d);
        }
    }

    public GameState getCurrentGameState() {
        return gameStates[currentGameState];
    }

    public GameState getGameState(int state) {
        return gameStates[state];
    }

    public void stop() {
        timer.stop();
        frame.dispose();
        //System.exit(0);
    }

    public void enterState(int state) {
        currentGameState = state;
    }

    public static void main(String[] args) {
        new Main("Game");
    }

}
