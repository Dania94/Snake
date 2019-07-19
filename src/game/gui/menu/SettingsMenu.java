package game.gui.menu;

import game.gui.Main;
import game.gui.Navigator;
import game.gui.settings.Settings;
import game.gui.view.MenuCellView;
import game.gui.view.MenuListView;
import game.gui.view.PlayerNameView;
import game.model.PageType;
import game.tools.StaticDefinitions;

import javax.xml.stream.events.StartDocument;
import java.awt.*;

public class SettingsMenu extends MenuListView {

    private MenuCellView playername = new MenuCellView("Player name");
    private MenuCellView speed = new MenuCellView("Game Speed");
    private MenuCellView exit = new MenuCellView("Exit");
    private MenuCellView sizeOfLevel = new MenuCellView("Size of Level");
    private boolean fromGame;

    public SettingsMenu(){
        setTitel("Settings");
        setSelectedCell(playername);
        AddCell(playername);
        AddCell(speed);
        AddCell(sizeOfLevel);

    }

    @Override
    protected void handleKeypressUp() {
        if(getSelectedCell().getIdentifier().equals(playername.getIdentifier())){
            if(ContainsCell(exit)){
                setSelectedCell(exit);

            }else {
                setSelectedCell(sizeOfLevel);
            }        }
        else if(getSelectedCell().getIdentifier().equals(speed.getIdentifier())){
            setSelectedCell(playername);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            setSelectedCell(speed);
        }
        else if(getSelectedCell().getIdentifier().equals(sizeOfLevel.getIdentifier())){
            setSelectedCell(speed);
        }
    }

    @Override
    protected void handleKeypressDown() {
        if(getSelectedCell().getIdentifier().equals(playername.getIdentifier())){
            setSelectedCell(speed);
        }
        else if(getSelectedCell().getIdentifier().equals(speed.getIdentifier())){
            if(ContainsCell(exit)){
                setSelectedCell(exit);

            }else {
                setSelectedCell(sizeOfLevel);
            }
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            setSelectedCell(playername);
        }
        else if(getSelectedCell().getIdentifier().equals(sizeOfLevel.getIdentifier())){
            setSelectedCell(playername);
        }
    }
    @Override
    protected void handleReturnPress() {
        if(getSelectedCell().getIdentifier().equals(playername.getIdentifier())){
            Main.playerNameView = new PlayerNameView();
            Navigator.Instance().PushTo(PageType.PLAYERNAME_CHANGE_PAGE);
        }
        else if(getSelectedCell().getIdentifier().equals(speed.getIdentifier())){
            Main.gameSpeedMenu = new GameSpeedMenu();
            Navigator.Instance().PushTo(PageType.SPEED_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            Main.startMenu = new SnakeStartMenu();
            Navigator.Instance().PushTo(PageType.START_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(sizeOfLevel.getIdentifier())){
            Main.gameSizeLevelMenu = new GameSizeLevelMenu();
            Navigator.Instance().PushTo(PageType.SIZE_OF_LEVEL_MENU);
        }
    }
    @Override
    protected void handleSpacePress() {
        handleReturnPress();
    }
    @Override
    protected void handleEscapePress() {
        if(isFromGame()){
            setFromGame(false);
            Navigator.Instance().PushToGame();
        }else {
            Main.startMenu = new SnakeStartMenu();
            Navigator.Instance().PushTo(PageType.START_MENU);
        }
    }

    public boolean isFromGame() {
        return fromGame;
    }

    public void setFromGame(boolean fromGame) {
        this.fromGame = fromGame;
        if(fromGame){
            if(!ContainsCell(exit))
                AddCell(exit);
                RemoveCell(sizeOfLevel);
        }else{
            if(!ContainsCell(sizeOfLevel))
                AddCell(sizeOfLevel);
            RemoveCell(exit);
            setSelectedCell(playername);
        }

    }
    public void drawSnakeEnvironment(Graphics2D g) {
        super.drawSnakeEnvironment(g);
        if(isFromGame()){
            g.setColor(Color.WHITE);
            Point initialTextPosition = new Point(getPosition().x + (StaticDefinitions.Instance().getMenueWidth()/2) - StaticDefinitions.Instance().getMenuCellWidth()/2,getPosition().y + (StaticDefinitions.Instance().getMenueWidth()) - (cellSize() * StaticDefinitions.Instance().getMenuCellHeight()) - StaticDefinitions.Instance().getMenuCellHeight());
            g.drawString("Game Difficaulty: ", initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().standardFont().getSize());
            g.drawString(Settings.Instance().getGameDifficulty().toString(), initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().standardFont().getSize()*2);
        }

    }
}