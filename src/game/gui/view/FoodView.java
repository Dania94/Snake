package game.gui.view;

import game.gui.settings.Settings;
import game.model.Food;
import game.tools.GraphicsView;
import game.tools.StaticDefinitions;

import java.awt.*;

public class FoodView extends GraphicsView implements Food {
    private Color foodColor = new Color(255,204,102); // the color is yellow as initial.
    private double foodScore = 1; // one means that the food increases 1 time the tail of the snake.
    private Graphics2D graphics2D;

    /**
     * Makes a food with a position and foodColor.
     * @param position is the food's position.
     * @param foodColor the food will get this color.
     */
    public FoodView(Point position, Color foodColor) {
        super(foodColor,position);
        setHeight(StaticDefinitions.SIZE_OF_TILE);
        setWidth(StaticDefinitions.SIZE_OF_TILE);
    }

    /**
     * Makes a Food view with default color and score.
     * @param position is the position of the food.
     */
    public FoodView(Point position) {
        setPosition(position);
        setHeight(StaticDefinitions.SIZE_OF_TILE);
        setWidth(StaticDefinitions.SIZE_OF_TILE);
    }

    /**
     * Makes a food view with position, color and food's score.
     * @param position is the food's position.
     * @param foodColor the food will get this color.
     * @param foodScore is the score of the food.
     */
    public FoodView(Point position, Color foodColor, double foodScore) {
        super(foodColor,position);
        this.foodScore = foodScore;
        setHeight(StaticDefinitions.SIZE_OF_TILE);
        setWidth(StaticDefinitions.SIZE_OF_TILE);
    }

    /**
     * It sets the the view that draws the food.
     * @param graphics2D ist the view that draws the food view
     */
    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    /**
     * It returns the color of the food.
     * @return color of the food.
     */
    public Color getFoodColor() {
        return foodColor;
    }

    /**
     * It sets the color of the food.
     * @param foodColor is the color of the food.
     */
    public void setFoodColor(Color foodColor) {
        this.foodColor = foodColor;
    }

    /**
     * It returns the score of the food.
     * @return the score of the food.
     */
    public double getFoodScore() {
        return foodScore;
    }

    /**
     * It sets the score of the food.
     * @param foodScore is the score of the food.
     */
    public void setFoodScore(double foodScore) {
        this.foodScore = foodScore;
    }

    @Override
    public void remove() {
        graphics2D.clearRect(getPosition().x,getPosition().y, getHeight(),getWidth());
        setPosition(new Point(1000,1000));
        Settings.Instance().getCurrentPlayer().setScore(Settings.Instance().getCurrentPlayer().getScore() + 1);
    }

    @Override
    public void draw() {
        graphics2D.setColor(getFoodColor());
        graphics2D.drawRoundRect(getPosition().x, getPosition().y, getWidth()-1,getHeight()-1,getWidth()-1,getHeight()-1);
        graphics2D.fillRoundRect(getPosition().x, getPosition().y, getWidth()-1,getHeight()-1,getWidth()-1,getHeight()-1);
    }
}
