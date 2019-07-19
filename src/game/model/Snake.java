package game.model;

import java.awt.*;

public interface Snake {

    /**
     * It adds the snake's tail.
     * @param point is the position of a new tail of the snake.
     */
    public void addTail(Point point);

    /**
     * It returns the snake's tail size.
     * @return snake tail size.
     */
    public int getTailSize();

    /**
     * It returns the state of the snake.
     * @return false if Snake is dead otherwise true.
     */
    public boolean getSnakeIsAlive();

    /**
     * It sets the state of the snake
     * @param isAlive is the state of the snake.
     */
    public void setSnakeIsAlive(boolean isAlive);
}
