package game.model;

import java.awt.*;

public interface Food {

    /**
     * It returns position of the food.
     * @return the position of the food.
     */
    public Point getPosition();

    /**
     * It sets the position of the food.
     * @param position
     */
    public void setPosition(Point position);

    /**
     * It returns the color of the food.
     * @return color of the food.
     */
    public Color getFoodColor();

    /**
     * It sets the color of the food.
     * @param foodColor is the color of the food.
     */
    public void setFoodColor(Color foodColor);

    /**
     * It returns the score of the food.
     * @return the score of the food.
     */
    public double getFoodScore();

    /**
     * It sets the score of the food.
     * @param foodScore is the score of the food.
     */
    public void setFoodScore(double foodScore);

    public void remove();
}
