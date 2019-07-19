package game.gui.view;

import game.gui.SnakeGameEnvironment;
import game.gui.settings.Settings;
import game.tools.StaticDefinitions;

import java.awt.*;
import java.util.ArrayList;

public class MenuListView extends SnakeGameEnvironment {

    private Color backgroundColor = Color.GRAY;
    private Color borderColor;
    private Point Position;


    ArrayList<MenuCellView> cells;
    MenuCellView selectedCell;
    private String Titel;

    public String getTitel() {
        return Titel;
    }


    public void setPosition(Point menuPositon) {
        this.Position = menuPositon;
    }

    public MenuListView(){
        super();
        setPosition(StaticDefinitions.Instance().getMenuPositon());
        cells = new ArrayList<>();
    }
    public void AddCell(MenuCellView cell){
        this.cells.add(cell);
    }
    public MenuCellView getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(MenuCellView selectedCell) {
        this.selectedCell = selectedCell;
    }


    public Point getPosition() {
        return this.Position;
    }


    public void setTitel(String titel) {
        Titel = titel;
    }


    public boolean ContainsCell(MenuCellView cell){
        return cells.contains(cell);
    }
    public void RemoveCell(MenuCellView cell){
        if (cells.contains(cell)){
            cells.remove(cell);
        }
    }

    public int cellSize(){
        return this.cells.size();
    }
    @Override
    public void drawSnakeEnvironment(Graphics2D graphics2D) {
        graphics2D.setBackground(Color.LIGHT_GRAY);
        graphics2D.drawRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueWidth(),StaticDefinitions.Instance().getMenueHeight());
        graphics2D.fillRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueWidth(),StaticDefinitions.Instance().getMenueHeight());

        graphics2D.setColor(Settings.Instance().getThemeColor());

        graphics2D.drawRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueWidth(),StaticDefinitions.Instance().getHeightOfGameIndicatorBar());
        graphics2D.fillRect(getPosition().x ,getPosition().y,StaticDefinitions.Instance().getMenueWidth(),StaticDefinitions.Instance().getHeightOfGameIndicatorBar());

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(StaticDefinitions.Instance().standardFont());
        graphics2D.drawString(this.Titel, getPosition().x + (StaticDefinitions.Instance().getMenueWidth()/2) - StaticDefinitions.Instance().getDeselectedFont().getSize() - this.Titel.length() * 2,getPosition().y + (StaticDefinitions.Instance().getHeightOfGameIndicatorBar()/2 + 5));

        Point lastCellPosition = new Point(getPosition().x + (StaticDefinitions.Instance().getMenueWidth()/2) - StaticDefinitions.Instance().getMenuCellWidth()/2,getPosition().y + (StaticDefinitions.Instance().getMenueHeight()/2) - ((cells.size()>5)? (StaticDefinitions.Instance().getMenueHeight()/2) -20 : (cells.size() * StaticDefinitions.Instance().getMenuCellHeight())));
        for (MenuCellView mcv:
                cells) {
            int cellGap = 15;
            if(cells.size() > 5){
                mcv.setHeight(StaticDefinitions.Instance().getMenueHeight()/20);
                cellGap = 10;
            }
            mcv.setPosition(new Point(lastCellPosition.x, lastCellPosition.y + mcv.getHeight() + cellGap));

            if(selectedCell.getIdentifier().equals(mcv.getIdentifier())){
                mcv.setSelected(true);
            }else {
                mcv.setSelected(false);
            }
            lastCellPosition = mcv.getPosition();

            graphics2D.setColor(mcv.getBackgroundColor());
            mcv.drawButton(graphics2D);
            mcv.drawLabel(graphics2D);

        }
    }
}
