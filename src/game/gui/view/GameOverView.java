package game.gui.view;

import game.gui.Main;
import game.gui.Navigator;
import game.gui.settings.Settings;
import game.model.PageType;
import game.model.Player;
import game.tools.EnumTranslator;
import game.tools.StaticDefinitions;

import java.awt.*;

public class GameOverView extends MenuListView {


    private MenuCellView exit = new MenuCellView("Exit");
    private MenuCellView highScore = new MenuCellView("HighScore");
    private boolean fromGame;

    public GameOverView() {
        setTitel("Game Over!");
        setSelectedCell(highScore);
        AddCell(highScore);
        AddCell(exit);
    }
    @Override
    protected void handleKeypressUp() {
        if(getSelectedCell().getIdentifier().equals(highScore.getIdentifier())){
            setSelectedCell(exit);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            setSelectedCell(highScore);
        }
    }

    @Override
    protected void handleKeypressDown() {
        handleKeypressUp();
    }
    @Override
    protected void handleReturnPress() {
        if(getSelectedCell().getIdentifier().equals(highScore.getIdentifier())){
            Main.highScoreView = new HighScoreView(true);
            Navigator.Instance().PushTo(PageType.HIGHSCORE);
        }
        else if(getSelectedCell().getIdentifier().equals(exit.getIdentifier())){
            handleEscapePress();
        }
    }

    @Override
    protected void handleSpacePress() {
        handleReturnPress();
    }

    @Override
    protected void handleEscapePress() {
        Settings.Instance().getCurrentPlayer().resetScore();
        Navigator.Instance().PushTo(PageType.START_MENU);
    }

    @Override
    public void drawSnakeEnvironment(Graphics2D g) {
        super.drawSnakeEnvironment(g);

        Point initialTextPosition = new Point(getPosition().x + (StaticDefinitions.Instance().getMenueWidth()/2) - StaticDefinitions.Instance().getMenuCellWidth()/2,cells.get(0).getPosition().y - cells.get(0).getHeight()*3);

        g.setFont(StaticDefinitions.Instance().standardFont());
        g.setColor(Color.WHITE);
        g.drawString("Player: " , initialTextPosition.x , initialTextPosition.y);
        g.drawString(Settings.Instance().getCurrentPlayer().getName() , initialTextPosition.x , initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize());
        g.drawString("Score: " + Settings.Instance().getCurrentPlayer().getScore(), initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize()*2);
        g.drawString("Game Speed: " + EnumTranslator.GameSpeedToString(Settings.Instance().getGameSpeed()), initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize() * 3);
        g.drawString("Game Difficaulty: ", initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize() * 4);
        g.drawString(EnumTranslator.GameDifficultyToString(Settings.Instance().getGameDifficulty()), initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize() * 5);
        g.drawString("Steps: "+ Settings.Instance().getCurrentPlayer().getSteps(), initialTextPosition.x, initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize() * 6);

    }
}