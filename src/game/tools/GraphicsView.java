package game.tools;

import java.awt.*;

public abstract class GraphicsView {
    private Color backgroundColor = Color.GRAY;
    private int height;
    private int width;
    private Color borderColor;
    private Point Position;

    protected GraphicsView() { }

    public GraphicsView(Color backgroundColor, Point position) {
        this.backgroundColor = backgroundColor;
        Position = position;
    }

    public GraphicsView(Color backgroundColor, Color borderColor, Point position) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        Position = position;
    }

    public GraphicsView(Color backgroundColor, int height, int width, Color borderColor, Point position) {
        this.backgroundColor = backgroundColor;
        this.height = height;
        this.width = width;
        this.borderColor = borderColor;
        Position = position;
    }
    public Point getPosition() {
        return Position;
    }

    public void setPosition(Point position) {
        Position = position;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public abstract void draw();
}
