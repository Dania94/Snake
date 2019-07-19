package game.gui.menu;

import game.gui.Main;
import game.gui.Navigator;
import game.gui.SnakeGame;
import game.gui.settings.Settings;
import game.gui.view.MenuCellView;
import game.gui.view.MenuListView;
import game.model.GameDifficulty;
import game.model.PageType;

import java.util.ArrayList;

public class GameDifficultyMenu extends MenuListView {

    private MenuCellView easy = new MenuCellView("Easy");
    private MenuCellView hard = new MenuCellView("Hard");
    private MenuCellView normal = new MenuCellView("Normal");
    private MenuCellView extreme = new MenuCellView("Extreme");


    public GameDifficultyMenu(){
        setTitel("Game Difficulty");
        setSelectedCell(easy);
        AddCell(easy);
        AddCell(normal);
        AddCell(hard);
        AddCell(extreme);
    }
    @Override
    protected void handleKeypressUp() {
        if(getSelectedCell().getIdentifier().equals(easy.getIdentifier())){
            setSelectedCell(extreme);
        }
        else if(getSelectedCell().getIdentifier().equals(hard.getIdentifier())){
            setSelectedCell(normal);
        }
        else if(getSelectedCell().getIdentifier().equals(normal.getIdentifier())){
            setSelectedCell(easy);
        }
        else if(getSelectedCell().getIdentifier().equals(extreme.getIdentifier())){
            setSelectedCell(hard);
        }
    }

    @Override
    protected void handleKeypressDown() {
        if(getSelectedCell().getIdentifier().equals(easy.getIdentifier())){
            setSelectedCell(normal);
        }
        else if(getSelectedCell().getIdentifier().equals(normal.getIdentifier())){
            setSelectedCell(hard);
        }
        else if(getSelectedCell().getIdentifier().equals(hard.getIdentifier())){
            setSelectedCell(extreme);
        }
        else if(getSelectedCell().getIdentifier().equals(extreme.getIdentifier())){
            setSelectedCell(easy);
        }
    }
    @Override
    protected void handleReturnPress() {
        if(getSelectedCell().getIdentifier().equals(easy.getIdentifier())){
            Settings.Instance().setGameDifficulty(GameDifficulty.EASY);
            Main.snakeGame = new SnakeGame();
            Navigator.Instance().PushTo(PageType.Snake_Game);
        }
        else if(getSelectedCell().getIdentifier().equals(normal.getIdentifier())){
            Settings.Instance().setGameDifficulty(GameDifficulty.NORMAL);
            Main.snakeGame = new SnakeGame();
            Navigator.Instance().PushTo(PageType.Snake_Game);
        }
        else if(getSelectedCell().getIdentifier().equals(hard.getIdentifier())){
            Settings.Instance().setGameDifficulty(GameDifficulty.HARD);
            Main.snakeGame = new SnakeGame();
            Navigator.Instance().PushTo(PageType.Snake_Game);
        }
        else if(getSelectedCell().getIdentifier().equals(extreme.getIdentifier())){
            Settings.Instance().setGameDifficulty(GameDifficulty.EXTREME);
            Main.snakeGame = new SnakeGame();
            Navigator.Instance().PushTo(PageType.Snake_Game);
        }
    }
    @Override
    protected void handleSpacePress() {
        handleReturnPress();
    }
    @Override
    protected void handleEscapePress() {
        Main.startMenu = new SnakeStartMenu();
        Navigator.Instance().PushTo(PageType.START_MENU);
    }
}
