package game.gui.menu;

import game.gui.Main;
import game.gui.Navigator;
import game.gui.settings.Settings;
import game.gui.view.MenuCellView;
import game.gui.view.MenuListView;
import game.model.Gamespeed;
import game.model.PageType;

public class GameSpeedMenu extends MenuListView {

    private MenuCellView first = new MenuCellView("Speed 1");
    private MenuCellView second = new MenuCellView("Speed 2");
    private MenuCellView third = new MenuCellView("Speed 3");
    private MenuCellView forth = new MenuCellView("Speed 4");
    private MenuCellView fifth = new MenuCellView("Speed 5");
    private MenuCellView sixth = new MenuCellView("Speed 6");
    private MenuCellView seventh = new MenuCellView("Speed 7");
    private MenuCellView eighth = new MenuCellView("Speed 8");
    private MenuCellView ninth = new MenuCellView("Speed 9");
    private MenuCellView tenth = new MenuCellView("Speed 10");


    public GameSpeedMenu(){
        setTitel("Game Speed");
        switch (Settings.Instance().getGameSpeed()){

            case SPEED1:
                setSelectedCell(first);
                break;
            case SPEED2:
                setSelectedCell(second);
                break;
            case SPEED3:
                setSelectedCell(third);
                break;
            case SPEED4:
                setSelectedCell(forth);
                break;
            case SPEED5:
                setSelectedCell(fifth);
                break;
            case SPEED6:
                setSelectedCell(sixth);
                break;
            case SPEED7:
                setSelectedCell(seventh);
                break;
            case SPEED8:
                setSelectedCell(eighth);
                break;
            case SPEED9:
                setSelectedCell(ninth);
                break;
            case SPEED10:
                setSelectedCell(tenth);
                break;
        }
        AddCell(first);
        AddCell(second);
        AddCell(third);
        AddCell(forth);
        AddCell(fifth);
        AddCell(sixth);
        AddCell(seventh);
        AddCell(eighth);
        AddCell(ninth);
        AddCell(tenth);

    }
    @Override
    protected void handleKeypressUp() {
        if(getSelectedCell().getIdentifier().equals(first.getIdentifier())){
            setSelectedCell(tenth);
        }
        else if(getSelectedCell().getIdentifier().equals(forth.getIdentifier())){
            setSelectedCell(third);
        }
        else if(getSelectedCell().getIdentifier().equals(second.getIdentifier())){
            setSelectedCell(first);
        }
        else if(getSelectedCell().getIdentifier().equals(third.getIdentifier())){
            setSelectedCell(second);
        }
        else if(getSelectedCell().getIdentifier().equals(fifth.getIdentifier())){
            setSelectedCell(forth);
        }
        else if(getSelectedCell().getIdentifier().equals(sixth.getIdentifier())){
            setSelectedCell(fifth);
        }
        else if(getSelectedCell().getIdentifier().equals(seventh.getIdentifier())){
            setSelectedCell(sixth);
        }
        else if(getSelectedCell().getIdentifier().equals(eighth.getIdentifier())){
            setSelectedCell(seventh);
        }
        else if(getSelectedCell().getIdentifier().equals(ninth.getIdentifier())){
            setSelectedCell(eighth);
        }
        else if(getSelectedCell().getIdentifier().equals(tenth.getIdentifier())){
            setSelectedCell(ninth);
        }
    }

    @Override
    protected void handleKeypressDown() {
        if(getSelectedCell().getIdentifier().equals(first.getIdentifier())){
            setSelectedCell(second);
        }
        else if(getSelectedCell().getIdentifier().equals(second.getIdentifier())){
            setSelectedCell(third);
        }
        else if(getSelectedCell().getIdentifier().equals(third.getIdentifier())){
            setSelectedCell(forth);
        }
        else if(getSelectedCell().getIdentifier().equals(forth.getIdentifier())){
            setSelectedCell(fifth);
        }
        else if(getSelectedCell().getIdentifier().equals(fifth.getIdentifier())){
            setSelectedCell(sixth);
        }
        else if(getSelectedCell().getIdentifier().equals(sixth.getIdentifier())){
            setSelectedCell(seventh);
        }
        else if(getSelectedCell().getIdentifier().equals(seventh.getIdentifier())){
            setSelectedCell(eighth);
        }
        else if(getSelectedCell().getIdentifier().equals(eighth.getIdentifier())){
            setSelectedCell(ninth);
        }
        else if(getSelectedCell().getIdentifier().equals(ninth.getIdentifier())){
            setSelectedCell(tenth);
        }
        else if(getSelectedCell().getIdentifier().equals(tenth.getIdentifier())){
            setSelectedCell(first);
        }
    }
    @Override
    protected void handleReturnPress() {
        if(getSelectedCell().getIdentifier().equals(first.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED1);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(second.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED2);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);

        }
        else if(getSelectedCell().getIdentifier().equals(third.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED3);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(forth.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED4);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(fifth.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED5);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(sixth.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED6);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(seventh.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED7);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(eighth.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED8);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(ninth.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED9);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(tenth.getIdentifier())){
            Settings.Instance().setGameSpeed(Gamespeed.SPEED10);
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
    }
    @Override
    protected void handleSpacePress() {
        handleReturnPress();
    }
    @Override
    protected void handleEscapePress() {
        Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
    }
}
