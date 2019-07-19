package game.gui.view;

import game.model.GameDifficulty;
import game.model.Gamespeed;
import game.tools.EnumTranslator;
import game.tools.GraphicsView;
import game.tools.StaticDefinitions;

import java.awt.*;

public class HighScoreCell extends MenuCellView {


    private int score;
    private int step;
    private GameDifficulty gameDifficulty;
    private Gamespeed gamespeed;
    private boolean isHighlighted;

    public HighScoreCell(String name, int score, int step, GameDifficulty gameDifficulty, Gamespeed gamespeed){
        super(name);
        this.score = score;
        this.step = step;
        this.gameDifficulty = gameDifficulty;
        this.gamespeed = gamespeed;
        setWidth(StaticDefinitions.Instance().getMenueHeight());
        setHeight(StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
        if(isSelected()){
            setBackgroundColor(Color.orange);
            setFont(StaticDefinitions.Instance().getSelectedFont());
            setTextColor(Color.WHITE);
        }else {
            setBackgroundColor(Color.PINK);
            setTextColor(Color.black);
            setFont(StaticDefinitions.Instance().getDeselectedFont());
        }
    }

    public void drawLabels(Graphics2D g){
        g.setColor(getTextColor());
        g.setFont(getFont());
        g.drawString((getLabel().length() > 6)?getLabel().substring(0, 6) + ".": getLabel(),getPosition().x + 5,getPosition().y + (getHeight()/2) + 5);
        g.drawString("" + score,getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*1 + 10,getPosition().y + (getHeight()/2) + 5);
        g.drawString("" +step,getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*2,getPosition().y + (getHeight()/2) + 5);
        g.drawString(EnumTranslator.GameDifficultyToString(this.gameDifficulty),getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*3,getPosition().y + (getHeight()/2) + 5);
        g.drawString(EnumTranslator.GameSpeedToString(this.gamespeed),getPosition().x + (StaticDefinitions.Instance().getMenueHeight()/5)*4 +5 ,getPosition().y + (getHeight()/2) + 5);

    }
    public void drawButton(Graphics2D graphics2D){
        graphics2D.setBackground(getBackgroundColor());
        graphics2D.drawRect(getPosition().x,getPosition().y,getWidth(),getHeight());
        graphics2D.fillRect(getPosition().x,getPosition().y,getWidth(),getHeight());
        graphics2D.setBackground(Color.white);
        graphics2D.drawRect(getPosition().x,getPosition().y,getWidth(),2);
        graphics2D.fillRect(getPosition().x,getPosition().y,getWidth(),2);
    }
}
