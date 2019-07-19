package game.gui;

import game.gui.menu.*;
import game.gui.settings.Settings;
import game.gui.view.GameLevelView;
import game.model.GameDifficulty;
import game.model.PageType;
import game.model.Snake;
import game.tools.GameEnvironment;
import game.tools.GuiContainer;
import game.tools.StaticDefinitions;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Navigator {
    Window lastWindow;
    GameEnvironment currentPage;
    Window currentWindow;

    private static Navigator _instance;

    public static Navigator Instance()
    {
        if (_instance == null) _instance = new Navigator();
        return _instance;
    }

    public void PushTo(PageType page){
        switch (page){

            case Snake_Game:
                PushTo(Main.snakeGame);
                break;
            case SETTINGS_MENU:
                PushTo(Main.settingsMenu);
                break;
            case SIZE_OF_LEVEL_MENU:
                PushTo(Main.gameSizeLevelMenu);
                break;
            case DIFFICULTY_MENU:
                PushTo(Main.gameDifficultyMenu);
                break;
            case SPEED_MENU:
                PushTo(Main.gameSpeedMenu);
                break;
            case PLAYERNAME_CHANGE_PAGE:
                PushTo(Main.playerNameView);
                break;
            case SCORE_PAGE:
                break;
            case START_MENU:
                PushTo(Main.startMenu);
                break;
            case GAME_OVER:
                PushTo(Main.gameOver);
                break;
            case HIGHSCORE:
                PushTo(Main.highScoreView);
                break;
        }
    }
    public void PushToGame(){
        PushTo(Main.snakeGame);
    }
    public void PushTo(GameEnvironment page){

        if( currentPage == null && page != currentPage){
            lastWindow = getCurrentWindow();
            GuiContainer.show(StaticDefinitions.WINDOW_TITLE, page);
            currentWindow = getCurrentWindowWithoutdispose();
        }else {
            currentWindow.setVisible(true);
        }
        currentPage = page;
    }


    private Window getCurrentWindowWithoutdispose(){
        Window res = null;
        for(Window w : GuiContainer.getWindows()){
            if(w instanceof JDialog){
                w.setVisible(false);
                res = w;
            }
        }
        return res;
    }
    private Window getCurrentWindow(){
        Window res = null;
        for(Window w : GuiContainer.getWindows()){
            if(w instanceof JDialog){
                w.setVisible(false);
                res = w;
            }
            w.dispose();
        }
        return res;
    }
}
