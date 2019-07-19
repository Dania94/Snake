package game.gui;

import game.gui.menu.*;
import game.gui.view.GameOverView;
import game.gui.view.HighScoreView;
import game.gui.view.PlayerNameView;
import game.model.PageType;
import game.tools.GameEnvironment;
import game.tools.GuiContainer;
import game.tools.StaticDefinitions;

public class Main {
    public static SnakeStartMenu startMenu;
    public static SettingsMenu settingsMenu;
    public static GameSpeedMenu gameSpeedMenu;
    public static GameSizeLevelMenu gameSizeLevelMenu ;
    public static GameDifficultyMenu gameDifficultyMenu ;
    public static SnakeGame snakeGame;
    public static GameOverView gameOver;
    public static PlayerNameView playerNameView;
    public static HighScoreView highScoreView;

    public static void main(String[] args) {
        startMenu = new SnakeStartMenu();
        // Start game session
        GuiContainer.show(StaticDefinitions.WINDOW_TITLE, startMenu);
    }
}