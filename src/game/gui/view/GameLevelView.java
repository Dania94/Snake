package game.gui.view;

import game.gui.settings.Settings;
import game.tools.GraphicsView;
import game.tools.StaticDefinitions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLevelView extends GraphicsView {
    private static Graphics2D graphics2D;
    private static GameLevelView _instance;

    ArrayList<Point> wallTiles;

    public GameLevelView() {
        initialWalls();
    }

    private void initialWalls(){
        wallTiles = new ArrayList<>();
        switch (Settings.Instance().getGameDifficulty()){
            case EASY:
                break;
            case NORMAL:
                createBoarderWall();
                break;
            case HARD:
                createBoarderWall();
                createWallRandom();
                break;
            case EXTREME:
                createBoarderWall();
                createWallRandom();
                createMoreWall();
                break;
            default:
                break;
        }
    }
    private void createBoarderWall(){
        for(int i = 0; i < StaticDefinitions.Instance().getWidth() ; i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(i,StaticDefinitions.Instance().getHeightOfGameIndicatorBar()));
        }
        for(int i = 0; i < StaticDefinitions.Instance().getHeight(); i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(0,StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + i));
        }
        for(int i = 0; i < StaticDefinitions.Instance().getHeight(); i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(StaticDefinitions.Instance().getWidth() - StaticDefinitions.SIZE_OF_TILE,StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + i));
        }
        for(int i = 0; i < StaticDefinitions.Instance().getWidth() ; i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(i,(StaticDefinitions.Instance().getHeight()) - StaticDefinitions.SIZE_OF_TILE));
        }
    }
    private Point getPoint() {
        Random rx = new Random();
        int x = StaticDefinitions.SIZE_OF_TILE * rx.nextInt(StaticDefinitions.SIZE_OF_LEVEL);
        Random ry = new Random();
        int y = StaticDefinitions.SIZE_OF_TILE * ry.nextInt(StaticDefinitions.SIZE_OF_LEVEL) + StaticDefinitions.Instance().getHeightOfGameIndicatorBar();

        Point res = new Point(x, y);
        if(wallTiles.contains(res)){
            res = getPoint();
        }
        return res;
    }


    private void createWallRandom(){

        int sizeOfTail = (int) (StaticDefinitions.SIZE_OF_TILE - StaticDefinitions.SIZE_OF_TAIL_SPACE);
        Point FirstWallPosition = getPoint();
        for(int i = 0; i <= sizeOfTail * 16 ; i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(FirstWallPosition.x,FirstWallPosition.y + i));
        }
        Point SecondWallPosition = getPoint();
        for(int i = 0; i <= sizeOfTail * 16 ; i += StaticDefinitions.SIZE_OF_TILE) {
            wallTiles.add(new Point(SecondWallPosition.x, SecondWallPosition.y + i));
        }
    }

    private void createMoreWall(){
        int sizeOfTail = (int) (StaticDefinitions.SIZE_OF_TILE - StaticDefinitions.SIZE_OF_TAIL_SPACE);
        Point ThirdWallPosition = getPoint();
        for(int i = 0; i <= sizeOfTail * 16 ; i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(ThirdWallPosition.x,ThirdWallPosition.y + i));
        }
        Point forthWallPosition = getPoint();
        for(int i = 0; i <= sizeOfTail * 16 ; i += StaticDefinitions.SIZE_OF_TILE){
            wallTiles.add(new Point(forthWallPosition.x,forthWallPosition.y + i));
        }
    }

    public ArrayList<Point> getWallTiles(){
        return this.wallTiles;
    }
    /**
     * It avoids create GameLevelView object for more.
     * @return created settings with default values if gameDifficulty and player is null otherwise with their values.
     */
    public static GameLevelView Instance()
    {
        if (_instance == null) _instance = new GameLevelView();
        return _instance;
    }

    public void ResetWalls(){
        initialWalls();
    }

    /**
     * It sets the the view that draws the food.
     * @param graphics2D ist the view that draws the food view
     */
    public static void setGraphics2D(Graphics2D graphics2D) {

        GameLevelView.graphics2D= graphics2D;
    }

    @Override
    public void draw() {
        graphics2D.setColor(Color.BLACK);
        int sizeOfTail = (int) (StaticDefinitions.SIZE_OF_TILE - StaticDefinitions.SIZE_OF_TAIL_SPACE);
        for(Point t: wallTiles){
            graphics2D.drawRect(t.x,t.y,sizeOfTail,sizeOfTail);
            graphics2D.fillRect(t.x,t.y,sizeOfTail,sizeOfTail);
        }
    }
}
