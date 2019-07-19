package game.gui.view;

import game.tools.GraphicsView;
import game.tools.StaticDefinitions;

import java.awt.*;

public class MenuCellView extends GraphicsView {

    private String label;
    private String identifier;
    private Font font;
    private Color textColor;
    protected boolean isSelected;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdentifier() {
        return label;
    }


    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public boolean isSelected() {
        return isSelected;
    }



    public void setSelected(boolean selected) {
        isSelected = selected;
        if(isSelected){
            setBackgroundColor(Color.MAGENTA);
            setFont(StaticDefinitions.Instance().getSelectedFont());
            setTextColor(Color.WHITE);
        }else {
            setBackgroundColor(Color.PINK);
            setTextColor(Color.black);
            setFont(StaticDefinitions.Instance().getDeselectedFont());
        }
    }

    public MenuCellView(String label){
        setLabel(label);
        setWidth(StaticDefinitions.Instance().getMenuCellWidth());
        setHeight(StaticDefinitions.Instance().getMenuCellHeight());
        setSelected(false);
    }

    public void drawLabel(Graphics2D g){
        g.setColor(getTextColor());
        g.setFont(getFont());
        g.drawString(this.label,getPosition().x + (StaticDefinitions.Instance().getMenuCellWidth()/2) - StaticDefinitions.Instance().getDeselectedFont().getSize() - this.label.length() * 2,getPosition().y + (getHeight()/2) + 5);
    }
    public void drawButton(Graphics2D graphics2D){
        graphics2D.setBackground(getBackgroundColor());
        graphics2D.drawRect(getPosition().x,getPosition().y,getWidth(),getHeight());
        graphics2D.fillRect(getPosition().x,getPosition().y,getWidth(),getHeight());
    }
    @Override
    public void draw() {

    }
}
