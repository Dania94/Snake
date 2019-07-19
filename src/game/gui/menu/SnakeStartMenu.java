package game.gui.menu;

import game.gui.Main;
import game.gui.Navigator;
import game.gui.SnakeGame;
import game.gui.view.HighScoreView;
import game.gui.view.MenuCellView;
import game.gui.view.MenuListView;
import game.model.PageType;
import game.tools.GuiContainer;
import game.tools.StaticDefinitions;

import javax.swing.*;
import java.awt.*;

import static game.gui.Main.settingsMenu;
import static game.gui.Main.startMenu;

public class SnakeStartMenu extends MenuListView {

    private MenuCellView start = new MenuCellView("Start");
    private MenuCellView settings = new MenuCellView("Settings");
    private MenuCellView highScore = new MenuCellView("HighScore");
    private MenuCellView exit = new MenuCellView("Exit");


    public SnakeStartMenu(){
        setTitel("Start Menu");
        setSelectedCell(start);
        AddCell(start);
        AddCell(settings);
        AddCell(highScore);
        AddCell(exit);
    }

    @Override
    protected void handleKeypressUp() {
        if(getSelectedCell().getIdentifier().equals(start.getIdentifier())){
            setSelectedCell(exit);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            setSelectedCell(highScore);
        }
        else if(getSelectedCell().getIdentifier().equals(highScore.getIdentifier())){
            setSelectedCell(settings);
        }
        else if(getSelectedCell().getIdentifier().equals(settings.getIdentifier())){
            setSelectedCell(start);
        }
    }

    @Override
    protected void handleKeypressDown() {
        if(getSelectedCell().getIdentifier().equals(start.getIdentifier())){
            setSelectedCell(settings);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            setSelectedCell(start);
        }
        else if(getSelectedCell().getIdentifier().equals(settings.getIdentifier())){
            setSelectedCell(highScore);
        }
        else if(getSelectedCell().getIdentifier().equals(highScore.getIdentifier())){
            setSelectedCell(exit);
        }
    }
    @Override
    protected void handleReturnPress() {
        if(getSelectedCell().getIdentifier().equals(start.getIdentifier())){
            Main.gameDifficultyMenu = new GameDifficultyMenu();
            Navigator.Instance().PushTo(PageType.DIFFICULTY_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            System.exit(0);
        }
        else if(getSelectedCell().getIdentifier().equals(settings.getIdentifier())){
            settingsMenu = (settingsMenu == null)? new SettingsMenu(): settingsMenu;
            settingsMenu.setFromGame(false);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(highScore.getIdentifier())){
            Main.highScoreView = new HighScoreView();
            Navigator.Instance().PushTo(PageType.HIGHSCORE);
        }
    }
    @Override
    protected void handleSpacePress() {
        handleReturnPress();
    }


}