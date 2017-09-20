import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by 2imontero on 11/19/15.
 */
public class Game extends GameState {

    private String[] imgPaths;
    private BufferedImage[] images;
    private Button[] buttons;
    private Random random;
    private int buttonWidth, buttonHeight, pDecision, cDecision, pHover, pWins, cWins, ties;
    private boolean pDecisionMade, cDecisionMade, pIsWinner, ended, isTie;

    public Game(int id, Main main, JFrame frame) {
        super(id, main, frame);

        buttonWidth = 250;
        buttonHeight = 80;

        imgPaths = new String[]{
                "/RPS/LRock.png",
                "/RPS/LPaper.png",
                "/RPS/LScissors.png",
                "/RPS/RRock.png",
                "/RPS/RPaper.png",
                "/RPS/RScissors.png"
        };
        random = new Random();
        images = new BufferedImage[imgPaths.length];
        //load images
        for (int i = 0; i < imgPaths.length; i++) {
            try {
                images[i] = ImageIO.read(getClass().getResource(imgPaths[i]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        buttons = new Button[]{
                new Button(main.getWidth() / 9, (main.getHeight() / 10) * 9 - buttonHeight, buttonWidth, buttonHeight, "Rock", 50),
                new Button(main.getWidth() / 2 - buttonWidth / 2, (main.getHeight() / 10) * 9 - buttonHeight, buttonWidth + 10, buttonHeight, "Paper", 50),
                new Button((main.getWidth() / 9) * 7 - buttonWidth / 2, (main.getHeight() / 10) * 9 - buttonHeight, buttonWidth, buttonHeight, "Scissors", 50)
        };

        ended = false;
        pDecisionMade = false;
        cDecisionMade = false;
        pIsWinner = false;
        isTie = false;
        pDecision = -1;
        cDecision = -1;
        pHover = -1;
        pWins = 0;
        cWins = 0;
        ties = 0;
    }

    @Override
    public void update() {
        if (!ended) {
            if (pDecisionMade) {
                if (!cDecisionMade) {
                    cDecision = random.nextInt(3);
                    cDecisionMade = true;
                }
                if (pDecision == 0 && cDecision == 2 || pDecision == 1 && cDecision == 0 || pDecision == 2 && cDecision == 1) {
                    pIsWinner = true;
                    pWins++;
                    ended = true;
                } else if (pDecision == cDecision) {
                    isTie = true;
                    ties++;
                    ended = true;
                } else {
                    pIsWinner = false;
                    cWins++;
                    ended = true;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        //set the background
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, main.getWidth(), main.getHeight());
        //reference lines
        /*
        g2d.setColor(Color.BLACK);
        g2d.drawLine(main.getWidth() / 4, 0,main.getWidth() / 4, main.getHeight());
        g2d.drawLine(main.getWidth() / 2, 0, main.getWidth() / 2, main.getHeight());
        g2d.drawLine((main.getWidth() / 4) * 3, 0, (main.getWidth() / 4) * 3, main.getHeight());
        g2d.drawLine(0, main.getHeight() / 4, main.getWidth(), main.getHeight() / 4);
        g2d.drawLine(0, main.getHeight() / 2, main.getWidth(), main.getHeight() / 2);
        g2d.drawLine(0, (main.getHeight() / 4) * 3, main.getWidth(), (main.getHeight() / 4) * 3);
        */
        if (pDecisionMade) {
            g2d.drawImage(images[pDecision], main.getWidth() / 4 - images[pDecision].getWidth() / 2, main.getHeight() / 2 - images[pDecision].getHeight() / 2, null);
            g2d.drawImage(images[cDecision + 3], (main.getWidth() / 4) * 3 - images[cDecision + 3].getWidth() / 2, main.getHeight() / 2 - images[cDecision + 3].getHeight() / 2, null);
            if (pIsWinner) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 200));
                g2d.drawString("You win!", main.getWidth() / 11, (main.getHeight() / 10) * 9);
            } else if (isTie) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 200));
                g2d.drawString("It's a tie!", main.getWidth() / 11, (main.getHeight() / 10) * 9);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 125));
                g2d.drawString("Computer Wins!", main.getWidth() / 50, (main.getHeight() / 10) * 9);
            }
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
            g2d.drawString("Click to continue", main.getWidth() / 3 + 70, (main.getHeight() / 10) * 9 + 50);
        } else if (!(pHover == -1)) {
            g2d.drawImage(images[pHover], main.getWidth() / 4 - images[pHover].getWidth() / 2, main.getHeight() / 2 - images[pHover].getHeight() / 2, null);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 300));
            g2d.drawString("?", (main.getWidth() / 5) * 3 + 50, main.getHeight() / 2 + 100);
            for (Button b : buttons) {
                b.draw(g2d);
            }
        } else {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 300));
            g2d.drawString("?", (main.getWidth() / 5) - 50, main.getHeight() / 2 + 100);
            g2d.drawString("?", (main.getWidth() / 5) * 3 + 50, main.getHeight() / 2 + 100);
            for (Button b : buttons) {
                b.draw(g2d);
            }
        }
        // VS.
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        g2d.drawString("VS.", main.getWidth() / 2 - 35, main.getHeight() / 2 + 20);

        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 75));
        g2d.drawString("You", main.getWidth() / 4 - 75, main.getHeight() / 10);
        g2d.drawString("Computer", (main.getWidth() / 4) * 3 - 175, main.getHeight() / 10);

        g2d.drawString("" + pWins, main.getWidth() / 4 - 20, main.getHeight() / 5);
        g2d.drawString("" + cWins, (main.getWidth() / 4) * 3 - 20, main.getHeight() / 5);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!pDecisionMade) {
            for (Button b : buttons) {
                if (b.getBounds().contains(e.getPoint())) {
                    b.setHighlighted(true);
                    switch (b.getText()) {
                        case "Rock":
                            pHover = 0;
                            break;
                        case "Paper":
                            pHover = 1;
                            break;
                        case "Scissors":
                            pHover = 2;
                            break;
                    }
                } else {
                    b.setHighlighted(false);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!pDecisionMade) {
            for (Button b : buttons) {
                if (b.getBounds().contains(e.getPoint())) {
                    switch (b.getText()) {
                        case "Rock":
                            pDecision = 0;
                            pDecisionMade = true;
                            break;
                        case "Paper":
                            pDecision = 1;
                            pDecisionMade = true;
                            break;
                        case "Scissors":
                            pDecision = 2;
                            pDecisionMade = true;
                            break;
                    }
                }
            }
        } else {
            reset();
        }
    }

    public void reset() {
        ended = false;
        pDecisionMade = false;
        cDecisionMade = false;
        pIsWinner = false;
        isTie = false;
        pDecision = -1;
        cDecision = -1;
        pHover = -1;
    }


}



