package game.gui.view;

import game.gui.settings.Settings;
import game.tools.StaticDefinitions;
import game.tools.GraphicsView;

import java.awt.*;

public class GameIndicatorBar extends GraphicsView {
    private static GameIndicatorBar _instance;
    private static Graphics2D graphics2D;


    public static void setGraphics2D(Graphics2D graphics2D) {
        GameIndicatorBar.graphics2D = graphics2D;
    }

    private GameIndicatorBar() {
        setHeight(StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
        setPosition(new Point(0,0));
    }

    /**
     * It avoids create settings object for more.
     * @return created settings with default values if gameDifficulty and player is null otherwise with their values.
     */
    public static GameIndicatorBar Instance()
    {
        if (_instance == null) _instance = new GameIndicatorBar();
        return _instance;
    }
    @Override
    public void draw() {
        graphics2D.setColor(Settings.Instance().getThemeColor());
        graphics2D.drawRect(0, 0, StaticDefinitions.Instance().getWidth(),     StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
        graphics2D.fillRect(0, 0, StaticDefinitions.Instance().getWidth(),    StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(StaticDefinitions.Instance().standardFont());
        graphics2D.drawString("Player: " + Settings.Instance().getCurrentPlayer().getName(),   getPosition().x + StaticDefinitions.Instance().standardFont().getSize(),   StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 +3);
        graphics2D.drawString("Steps: " + Settings.Instance().getCurrentPlayer().getSteps(),   StaticDefinitions.Instance().getHalfWindowWidth() - 7*3,   StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 +3);
        graphics2D.drawString("Points: " + Settings.Instance().getCurrentPlayer().getScore(),StaticDefinitions.Instance().getWidth() - StaticDefinitions.Instance().standardFont().getSize() - 8*(StaticDefinitions.Instance().getWidth()/50),   StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 +5);
    }

}
