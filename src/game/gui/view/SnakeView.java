package game.gui.view;

import game.gui.settings.Settings;
import game.model.Snake;
import game.model.SnakeMove;
import game.tools.GraphicsView;
import game.tools.StaticDefinitions;

import java.awt.*;


import java.util.ArrayList;


public class SnakeView extends GraphicsView implements Snake  {
    private Graphics2D graphics2D;
    private SnakeMove snakeMove;
    private ArrayList<Point> snake;
    private boolean isAlive = true;
    private ArrayList<Point> wallTail;
    /**
     * It creates the Snake object.
     * @param snakeColor is the color of the 
     * @param snakePosition is the position of the 
     */
    public SnakeView(Color snakeColor, Point snakePosition,ArrayList<Point> wallTail) {
        super(snakeColor,snakePosition);
        this.wallTail = new ArrayList<>();
        this.snake = new ArrayList();
        snake.add(snakePosition);
        setHeight(StaticDefinitions.SNAKE_WIDTH_SIZE);
        setWidth(StaticDefinitions.SNAKE_WIDTH_SIZE);
        setSnakeMove(SnakeMove.Unknown);
        this.wallTail = wallTail;
    }

    /**
     * It adds a tail to the snake
     * @param snaketail is a position of the new stake's tail.
     */
    public void addTail(Point snaketail) {
        this.snake.add(snaketail);
    }


    /**
     * It updates the position of the tails.
     * @param head is a new position of the head.
     */
    public void updateSegments(Point head) {
        if(snake.contains(head)){
            isAlive = false;
            return;
        }
        for(Point w : wallTail){
            if(snake.contains(w)){
                isAlive = false;
                return;
            }
        }
        this.snake.add(0,head);
        this.snake.remove(this.snake.size()-1);
    }

    public boolean containsPosition(Point p){
        return this.wallTail.contains(p) || this.snake.contains(p);
    }

    public Point getHeadPosition(){
        return snake.get(0);
    }
    /**
     * It returns the snake's tail size.
     * @return snake tail size.
     */
    public int getTailSize() {
        return this.snake.size();
    }

    /**
     * It returns the state of the snake.
     * @return false if Snake is dead otherwise true.
     */
    @Override
    public boolean getSnakeIsAlive() {
        return this.isAlive;
    }

    /**
     * It sets the state of the snake
     * @param isAlive is the state of the snake.
     */
    @Override
    public void setSnakeIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }


    /**
     * It returns the snake moving direction.
     * @return snake moving direction.
     */
    public SnakeMove getSnakeMove() {
        return snakeMove;
    }

    /**
     * It sets snake moving direction.
     * @param snakeMove is the snake moving direction.
     */
    public void setSnakeMove(SnakeMove snakeMove) {
        this.snakeMove = snakeMove;
    }

    /**
     * It sets the the view that draws the food.
     * @param graphics2D ist the view that draws the food view
     */
    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }


    @Override
    public void draw() {

            graphics2D.setColor(getBackgroundColor());
            switch (snakeMove){
                case UP:
                    int toUp = (this.snake.get(0).y <= StaticDefinitions.Instance().getHeightOfGameIndicatorBar())? StaticDefinitions.Instance().getHeight() - StaticDefinitions.SNAKE_WIDTH_SIZE: (this.snake.get(0).y - getHeight());
                    setPosition(new Point(getPosition().x,toUp));
                    updateSegments(new Point(getPosition().x,toUp));
                    Settings.Instance().getCurrentPlayer().countSteps();
                    break;
                case DOWN:
                    int toBottom = (this.snake.get(0).y >= StaticDefinitions.Instance().getHeight() - StaticDefinitions.SNAKE_WIDTH_SIZE)? StaticDefinitions.Instance().getHeightOfGameIndicatorBar(): (this.snake.get(0).y + getHeight());
                    setPosition(new Point(getPosition().x,toBottom));
                    updateSegments(new Point(getPosition().x,toBottom));
                    Settings.Instance().getCurrentPlayer().countSteps();
                    break;
                case LEFT:
                    int toLeft = (this.snake.get(0).x <= 0 )? StaticDefinitions.Instance().getWidth() - StaticDefinitions.SNAKE_WIDTH_SIZE: (this.snake.get(0).x - getHeight());
                    setPosition(new Point(toLeft,getPosition().y));
                    updateSegments(new Point(toLeft,getPosition().y));
                    Settings.Instance().getCurrentPlayer().countSteps();
                    break;
                case RIGHT:
                    int toRight = (this.snake.get(0).x >= StaticDefinitions.Instance().getWidth() - StaticDefinitions.SNAKE_WIDTH_SIZE)? 0: (this.snake.get(0).x + getHeight());
                    setPosition(new Point(toRight,getPosition().y));
                    updateSegments(new Point(toRight,getPosition().y));
                    Settings.Instance().getCurrentPlayer().countSteps();
                default:
                    break;
            }
            for (Point tail : snake) {
                graphics2D.drawRoundRect(tail.x, tail.y, getWidth(),getHeight(),getWidth(),getHeight());
                graphics2D.fillRoundRect(tail.x, tail.y, getWidth(),getHeight(),getWidth(),getHeight());
            }


        }

}
