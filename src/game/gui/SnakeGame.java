package game.gui;

import game.gui.menu.SettingsMenu;
import game.gui.settings.Settings;
import game.gui.view.*;
import game.model.PageType;
import game.model.SnakeMove;
import game.tools.StaticDefinitions;

import java.awt.*;
import java.util.Random;


public class SnakeGame extends SnakeGameEnvironment {
    // Snake View
    private SnakeView snake;
    private FoodView foodView;

    public SnakeGame(){
        GameLevelView.Instance().ResetWalls();
        foodView = new FoodView(getFoodPosition());
        snake = new SnakeView(Color.green, getSnakePosition() , GameLevelView.Instance().getWallTiles());
    }
    @Override
    protected void handleKeypressUp() {
        if(snake.getSnakeMove() != SnakeMove.DOWN)
            snake.setSnakeMove(SnakeMove.UP);
    }

    @Override
    protected void handleKeypressDown() {
        if(snake.getSnakeMove() != SnakeMove.UP)
            snake.setSnakeMove(SnakeMove.DOWN);
    }

    @Override
    protected void handleKeypressLeft() {
        if(snake.getSnakeMove() != SnakeMove.RIGHT)
            snake.setSnakeMove(SnakeMove.LEFT);
    }


    @Override
    protected void handleKeypressRight() {
        if(snake.getSnakeMove() != SnakeMove.LEFT)
            snake.setSnakeMove(SnakeMove.RIGHT);
    }

    /**
     * Position of the Food
     * It returns random food position
     *
     * @return random food Position
     */
    private Point getFoodPosition() {
        Random rx = new Random();
        int x = StaticDefinitions.SIZE_OF_TILE * rx.nextInt(Settings.Instance().getSizeOfLevel());
        Random ry = new Random();
        int y = StaticDefinitions.SIZE_OF_TILE * ry.nextInt(Settings.Instance().getSizeOfLevel()) + StaticDefinitions.Instance().getHeightOfGameIndicatorBar();

        Point res = new Point(x, y);
        if(snake != null && snake.containsPosition(res)|| GameLevelView.Instance().getWallTiles().contains(res)){
            res = getFoodPosition();
        }
        return res;
    }


    private Point getSnakePosition(){
        Point res = getFoodPosition();
        if (foodView.getPosition().equals(res) || GameLevelView.Instance().getWallTiles().contains(res)){
            res = getSnakePosition();
        }
        return res;
    }



    @Override
    protected void handleEscapePress() {
        Main.settingsMenu = (Main.settingsMenu == null)? new SettingsMenu():Main.settingsMenu;
        Main.settingsMenu.setFromGame(true);
        Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
    }
    @Override
    public void drawSnakeEnvironment(Graphics2D graphics) {


        // GameIndicator.
        GameIndicatorBar.setGraphics2D(graphics);
        GameIndicatorBar.Instance().draw();

        GameLevelView.setGraphics2D(graphics);
        GameLevelView.Instance().draw();

        foodView.setGraphics2D(graphics);
        foodView.draw();

        // snake
        snake.setGraphics2D(graphics);
        snake.draw();


        // score
        if (foodView.getPosition().equals(snake.getHeadPosition())) {
            snake.addTail(foodView.getPosition());
            foodView.setPosition(getFoodPosition());
            Settings.Instance().getCurrentPlayer().setScore(1);
        }
        if (!snake.getSnakeIsAlive())
        {
            Main.gameOver = new GameOverView();
            Main.playerNameView = new PlayerNameView(true);
            Navigator.Instance().PushTo(PageType.PLAYERNAME_CHANGE_PAGE);
        }

    }
}
