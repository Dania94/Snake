package game.tools;

import game.gui.settings.Settings;

import java.awt.*;
import java.security.PublicKey;

public class StaticDefinitions {
    public static final int SNAKE_WIDTH_SIZE = 15; // 15 pixel
    public static final int SIZE_OF_TILE = 15; // 15 pixel
    public static final Double SIZE_OF_TAIL_SPACE = 2.0;
    public static final String WINDOW_TITLE = "Snake";
    public static final int SIZE_OF_LEVEL = Settings.Instance().getSizeOfLevel();

    public static final String CELL_FONT_NAME = "Comic Sans MS";

    private static StaticDefinitions _instance;

    public static StaticDefinitions Instance()
    {
        if (_instance == null) _instance = new StaticDefinitions();
        return _instance;
    }

    public int getHeightOfGameIndicatorBar(){
        
        switch (Settings.Instance().getGameSize()){
            case XSMALL:
                return 22;
            case SMALL:
                return 25;
            case MEDIUM:
                return 30;
            case LARGE:
                return 35;
            case XLARGE:
                return 40;
            default:
                return 22;
        }
    }
    public Font standardFont(){
        switch (Settings.Instance().getGameSize()) {
            case XSMALL:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.LAYOUT_LEFT_TO_RIGHT,12);
            case SMALL:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.LAYOUT_LEFT_TO_RIGHT,15);
            case MEDIUM:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.LAYOUT_LEFT_TO_RIGHT,20);
            case LARGE:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.LAYOUT_LEFT_TO_RIGHT,22);
            case XLARGE:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.LAYOUT_LEFT_TO_RIGHT,25);
            default:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.LAYOUT_LEFT_TO_RIGHT,12);
        }
    }
    public Font getSelectedFont(){
        switch (Settings.Instance().getGameSize()) {
            case XSMALL:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.BOLD,12);
            case SMALL:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.BOLD,15);
            case MEDIUM:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.BOLD,20);
            case LARGE:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.BOLD,22);
            case XLARGE:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.BOLD,25);
            default:
                return new Font(StaticDefinitions.CELL_FONT_NAME,Font.BOLD,12);
        }
    }
    public Font getDeselectedFont(){
        return standardFont();
    }
    public Point getMenuPositon(){
        return new Point(getHalfWindowWidth() - getMenueWidth()/2, getHalfWindowHeight() - getMenueHeight() /2);
    }
    public Point getHighScorePosition(){
        return new Point(getHalfWindowWidth() - getMenueWidth(), getHalfWindowHeight() - getMenueHeight()/2);
    }
    public int getMenuCellHeight(){
        return getMenueHeight() / 10;
    }
    public int getMenuCellWidth(){
        return (getMenueWidth()/3)*2;
    }
    public int getMenueWidth(){
        return getWidth() /3;
    }
    public int getMenueHeight(){
        return (getWidth() / 6) * 4;
    }
    public int getHalfWindowWidth(){
        return getWidth()/2;
    }
    public int getHalfWindowHeight(){
        return getWidth()/2;
    }
    public int getWidth(){
        return Settings.Instance().getSizeOfLevel() * StaticDefinitions.SIZE_OF_TILE;
    }
    public int getHeight(){
        return Settings.Instance().getSizeOfLevel() * StaticDefinitions.SIZE_OF_TILE + StaticDefinitions.Instance().getHeightOfGameIndicatorBar();
    }
}
