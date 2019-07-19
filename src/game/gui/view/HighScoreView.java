package game.gui.view;

import game.gui.Main;
import game.gui.Navigator;
import game.gui.settings.Settings;
import game.model.PageType;
import game.model.Player;
import game.tools.EnumTranslator;
import game.tools.StaticDefinitions;

import java.awt.*;
import java.util.ArrayList;

public class HighScoreView extends MenuListView{
    private MenuCellView exit = new MenuCellView("Back");
    private boolean fromGame;
    private ArrayList<Player> players = new ArrayList<>();
    public HighScoreView() {
        setTitel("High Score");
        setSelectedCell(exit);
        AddCell(exit);
        setPosition(StaticDefinitions.Instance().getHighScorePosition());
        players = Settings.Instance().getPlayers();
    }
    public HighScoreView(boolean fromGame) {
        setTitel("High Score");
        setSelectedCell(exit);
        AddCell(exit);
        setPosition(StaticDefinitions.Instance().getHighScorePosition());
        if(fromGame) exit.setLabel("Back");
        players = Settings.Instance().getPlayers();

    }

    @Override
    protected void handleReturnPress() {
        handleEscapePress();
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

        players = Settings.Instance().getPlayers();

        g.setBackground(Color.LIGHT_GRAY);
        g.drawRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueHeight(),StaticDefinitions.Instance().getMenueHeight());
        g.fillRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueHeight(),StaticDefinitions.Instance().getMenueHeight());

        g.setColor(Settings.Instance().getThemeColor());

        g.drawRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueHeight(),StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
        g.fillRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueHeight(),StaticDefinitions.Instance().getHeightOfGameIndicatorBar());

        g.setColor(Color.WHITE);
        g.setFont(StaticDefinitions.Instance().standardFont());
        g.drawString(getTitel(), getPosition().x + (StaticDefinitions.Instance().getMenueWidth()) - StaticDefinitions.Instance().getDeselectedFont().getSize() - getTitel().length() * 2,getPosition().y + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5));

        g.setFont(StaticDefinitions.Instance().getSelectedFont());
        g.drawString("Name",getPosition().x + 10,getPosition().y + StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5) );
        g.drawString("Score",getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*1,getPosition().y + StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5));
        g.drawString("Steps",getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*2 ,getPosition().y + StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5));
        g.drawString("diff.",getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*3,getPosition().y + StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5));
        g.drawString("speed",getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*4 + 5,getPosition().y + StaticDefinitions.Instance().getHeightOfGameIndicatorBar() + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5));

        Point lastCellPosition = new Point(getPosition().x,getPosition().y + StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
        for(Player p: players){
            HighScoreCell cell = new HighScoreCell(p.getName(),p.getScore(),p.getSteps(),p.getGameDifficulty(),p.getGamespeed());
            cell.setPosition(new Point(lastCellPosition.x, lastCellPosition.y + cell.getHeight()));
            lastCellPosition = cell.getPosition();
            if(p.getName().equals(Settings.Instance().getCurrentPlayer().getName())){
                cell.setSelected(true);
            }else {
                cell.setSelected(false);
            }
            g.setColor(cell.getBackgroundColor());
            cell.drawButton(g);
            cell.drawLabels(g);
        }

    }
}
