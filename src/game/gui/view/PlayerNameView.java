package game.gui.view;

import game.gui.Navigator;
import game.gui.settings.Settings;
import game.model.PageType;
import game.model.Player;
import game.tools.StaticDefinitions;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerNameView extends MenuListView {


    private MenuCellView save = new MenuCellView("Save");
    //private MenuCellView sizeOfLevel = new MenuCellView("Size of Level");
    private boolean fromGame;
    JTextField textField;
    Point initialTextPosition;

    private boolean gameOver;
    private boolean textfieldAdded = false;

    public PlayerNameView() {
        setTitel("Change Name");
        setSelectedCell(save);
        AddCell(save);
        textField = new JTextField(Settings.Instance().getCurrentPlayer().getName());
        textField.setBackground(Color.WHITE);
        textField.setFont(StaticDefinitions.Instance().standardFont());

    }
    public PlayerNameView(boolean gameOver) {
        setTitel("Change Name");
        setSelectedCell(save);
        AddCell(save);
        textField = new JTextField(Settings.Instance().getCurrentPlayer().getName());
        textField.setBackground(Color.WHITE);
        textField.setFont(StaticDefinitions.Instance().standardFont());
        this.gameOver = gameOver;
    }

    @Override
    protected void handleReturnPress() {
        handleEscapePress();
    }

    @Override
    protected void handleSpacePress() {
        handleEscapePress();
    }

    @Override
    protected void handleEscapePress() {
        Settings.Instance().getCurrentPlayer().setName((!textField.getText().isEmpty())?textField.getText():Settings.Instance().getCurrentPlayer().getName());
        Player user = Settings.Instance().getCurrentPlayer();
        Settings.Instance().updateUser(user.getName(),user.getScore(),user.getSteps(),user.getGamespeed(), user.getGameDifficulty());
        if (gameOver){
            Navigator.Instance().PushTo(PageType.GAME_OVER);
        }else {
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
    }

    @Override
    public void drawSnakeEnvironment(Graphics2D g) {
        super.drawSnakeEnvironment(g);
        initialTextPosition = new Point(getPosition().x + (StaticDefinitions.Instance().getMenueWidth()/2) - StaticDefinitions.Instance().getMenuCellWidth()/2,cells.get(0).getPosition().y - cells.get(0).getHeight()*3);

        if(!textfieldAdded){
            textField.setBounds(initialTextPosition.x,initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize() * 2,StaticDefinitions.Instance().getMenuCellWidth(),StaticDefinitions.Instance().getMenuCellHeight());
            add(textField);
            textfieldAdded = true;
        }


        g.setFont(StaticDefinitions.Instance().standardFont());
        g.drawString("Player: " , initialTextPosition.x , initialTextPosition.y);
        g.drawString(textField.getText() , initialTextPosition.x , initialTextPosition.y + StaticDefinitions.Instance().getDeselectedFont().getSize()+ 2);
        g.drawString("If you are done" , cells.get(0).getPosition().x , cells.get(0).getPosition().y + cells.get(0).getHeight() + StaticDefinitions.Instance().getDeselectedFont().getSize());
        g.drawString("with writing," , cells.get(0).getPosition().x , cells.get(0).getPosition().y + cells.get(0).getHeight() + + StaticDefinitions.Instance().getDeselectedFont().getSize()*2);
        g.drawString("press Tab key(->|)" , cells.get(0).getPosition().x , cells.get(0).getPosition().y + cells.get(0).getHeight() + StaticDefinitions.Instance().getDeselectedFont().getSize()*3);

    }
}