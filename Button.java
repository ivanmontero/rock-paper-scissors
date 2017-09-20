import java.awt.*;

public class Button {

    private int x, y, width, height, fontSize;
    private boolean isHighlighted;
    private String text;
    private Color highlightColor, normalColor;

    public Button(int x, int y, int width, int height, String text, int fontSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontSize = fontSize;
        this.normalColor = Color.BLACK;
        this.highlightColor = Color.WHITE;
        isHighlighted = false;
    }

    public void setHighlighted(boolean highlight) {
        this.isHighlighted = highlight;
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        if (isHighlighted) {
            g2d.setColor(highlightColor);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(normalColor);
            g2d.drawString(text, x + width / 10, (y + height / 2) + width / 15);
        } else {
            g2d.setColor(normalColor);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(highlightColor);
            g2d.drawString(text, x + width / 10, (y + height / 2) + width / 15);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
    }

    public String getText() {
        return text;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean contains(Point p) {
        return (new Rectangle(x, y, width, height)).contains(p);
    }
}
