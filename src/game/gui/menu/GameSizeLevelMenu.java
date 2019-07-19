package game.gui.menu;
import game.gui.Main;
import game.gui.Navigator;
import game.gui.settings.Settings;
import game.gui.view.MenuCellView;
import game.gui.view.MenuListView;
import game.model.GameSize;
import game.model.Gamespeed;
import game.model.PageType;

public class GameSizeLevelMenu extends MenuListView {

    private MenuCellView xsmall = new MenuCellView("XS");
    private MenuCellView small = new MenuCellView("S");
    private MenuCellView medium = new MenuCellView("M");
    private MenuCellView large = new MenuCellView("L");
    private MenuCellView xlarge = new MenuCellView("XL");

    public GameSizeLevelMenu(){
        setTitel("Size of Level");
        switch (Settings.Instance().getGameSize()){
            case XSMALL:
                setSelectedCell(xsmall);
                break;
            case SMALL:
                setSelectedCell(small);
                break;
            case MEDIUM:
                setSelectedCell(medium);
                break;
            case LARGE:
                setSelectedCell(large);
                break;
            case XLARGE:
                setSelectedCell(xlarge);
                break;
        }
        AddCell(xsmall);
        AddCell(small);
        AddCell(medium);
        AddCell(large);
        AddCell(xlarge);

    }
    @Override
    protected void handleKeypressUp() {
        if(getSelectedCell().getIdentifier().equals(xsmall.getIdentifier())){
            setSelectedCell(xlarge);
        }
        else if(getSelectedCell().getIdentifier().equals(xlarge.getIdentifier())){
            setSelectedCell(large);
        }
        else if(getSelectedCell().getIdentifier().equals(large.getIdentifier())){
            setSelectedCell(medium);
        }
        else if(getSelectedCell().getIdentifier().equals(medium.getIdentifier())){
            setSelectedCell(small);
        }
        else if(getSelectedCell().getIdentifier().equals(small.getIdentifier())){
            setSelectedCell(xsmall);
        }
    }

    @Override
    protected void handleKeypressDown() {
        if(getSelectedCell().getIdentifier().equals(xsmall.getIdentifier())){
            setSelectedCell(small);
        }
        else if(getSelectedCell().getIdentifier().equals(small.getIdentifier())){
            setSelectedCell(medium);
        }
        else if(getSelectedCell().getIdentifier().equals(medium.getIdentifier())){
            setSelectedCell(large);
        }
        else if(getSelectedCell().getIdentifier().equals(large.getIdentifier())){
            setSelectedCell(xlarge);
        }
        else if(getSelectedCell().getIdentifier().equals(xlarge.getIdentifier())){
            setSelectedCell(xsmall);
        }
    }
    @Override
    protected void handleReturnPress() {
        if(getSelectedCell().getIdentifier().equals(xsmall.getIdentifier())){
            Settings.Instance().setGameSize(GameSize.XSMALL);
            Main.settingsMenu = new SettingsMenu();
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(small.getIdentifier())){
            Settings.Instance().setGameSize(GameSize.SMALL);
            Main.settingsMenu = new SettingsMenu();
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(medium.getIdentifier())){
            Settings.Instance().setGameSize(GameSize.MEDIUM);
            Main.settingsMenu = new SettingsMenu();
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(large.getIdentifier())){
            Settings.Instance().setGameSize(GameSize.LARGE);
            Main.settingsMenu = new SettingsMenu();
            Navigator.Instance().PushTo(PageType.SETTINGS_MENU);
        }
        else if(getSelectedCell().getIdentifier().equals(xlarge.getIdentifier())){
            Settings.Instance().setGameSize(GameSize.XLARGE);
            Main.settingsMenu = new SettingsMenu();
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

