package game.gui.settings;

import game.model.GameSize;
import game.model.Gamespeed;
import game.model.Player;
import game.model.GameDifficulty;
import game.tools.Database;
import game.tools.EnumTranslator;
import game.tools.GuiContainer;
import game.tools.StaticDefinitions;

import java.awt.*;
import java.util.ArrayList;

public class Settings {
    private static GameDifficulty gameDifficulty;
    private static Player player;
    private static Settings _instance;
    private static Gamespeed gamespeed;
    private static boolean isGameDone = false;
    private Color ThemeColor = Color.GRAY;
    private int SizeOfLevel = 35;
    private static GameSize gameSize;
    private static Database db;

    /**
     * It creates a settings object
     * @param gameDifficulty is the game difficulty.
     * @param player is the Player object.
     */
    private Settings(GameDifficulty gameDifficulty, Player player, Gamespeed gamespeed) {
        Settings.gameDifficulty = gameDifficulty;
        Settings.player = player;
        Settings.gamespeed= gamespeed;
        updateUser(player.getName(),player.getScore(),player.getSteps(),player.getGamespeed(),gameDifficulty);
    }

    /**
     * It avoids create settings object for more.
     * @return created settings with default values if gameDifficulty and player is null otherwise with their values.
     */
    public static Settings Instance()
    {
        db = (db == null) ? new Database() :db;
        db.makeTable();
        gameDifficulty = (gameDifficulty == null)? GameDifficulty.EASY :gameDifficulty;
        gamespeed=(gamespeed==null)? Gamespeed.SPEED1:gamespeed;
        if(player == null){
            if (db.isUserInDB("Unknown Player")){
                player = db.getPlayer("Unknown Player");
            }else {
                player = new Player("Unknown Player",0,0, gameDifficulty,gamespeed);
            }
        }
        gameSize =(gameSize == null)? GameSize.SMALL:gameSize ;
        if (_instance == null) _instance = new Settings(gameDifficulty,player,gamespeed);
        return _instance;
    }

    public ArrayList<Player> getPlayers() {
        return db.players();

    }


    /**
     * It returns the state of the game.
     * @return true if game is done otherwise false.
     */
    public boolean getIsGameDone() {
        return isGameDone;
    }

    /**
     * It sets the state of the game.
     * @param isGameDone will be true if the game is done.
     */
    public void setIsGameDone(boolean isGameDone) {
        Settings.isGameDone = isGameDone;
    }
    /**
     * It returns the game difficulty.
     * @return game difficulty.
     */
    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    /**
     *
     * @return game speed.
     */
    public Gamespeed getGameSpeed(){return  gamespeed; }

    /**
     * It sets the game difficulty.
     * @param gameDifficulty is the game difficulty.
     */
    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        Settings.gameDifficulty = gameDifficulty;
        db.updateUserGameDifficulty(getCurrentPlayer().getName(), EnumTranslator.GameDifficultyToString(Settings.gameDifficulty));
    }

    /**
     * It returns the Player object.
     * @return player object.
     */
    public Player getCurrentPlayer() {
        return player;
    }


    /**
     It sets the game speed.
     * @param gameSpeed is the game speed.
     */
    public void setGameSpeed(Gamespeed gameSpeed){
        Settings.gamespeed= gameSpeed;
        db.updateUserGameSpeed(getCurrentPlayer().getName(), EnumTranslator.GameSpeedToString(Settings.gamespeed));
    }
    /**
     * It sets the player object.
     * @param player is the player object.
     */
    public void setPlayer(Player player) {
        updateUser(player.getName(),player.getScore(),player.getSteps(),player.getGamespeed(),player.getGameDifficulty());
    }

    public Color getThemeColor() {
        return ThemeColor;
    }

    public void setThemeColor(Color themeColor) {
        ThemeColor = themeColor;
    }

    public int getSizeOfLevel() {
        return SizeOfLevel;
    }

    public void setSizeOfLevel(int sizeOfLevel) {
        SizeOfLevel = sizeOfLevel;
    }

    public GameSize getGameSize() {
        return gameSize;
    }

    public void updateUser(String name,int score, int step, Gamespeed gamespeed, GameDifficulty gameDifficulty){
        if(db.isUserInDB(name)){
            db.updateUser(name,score,step,gamespeed.toString(),gameDifficulty.toString());
        }else {
            db.insertNewUser(name,gamespeed.toString(),gameDifficulty.toString(),score,step);
        }
    }

    public void setGameSize(GameSize gameSize) {
        this.gameSize = gameSize;
        switch (gameSize) {
            case XSMALL:
                this.setSizeOfLevel(35);
                break;
            case SMALL:
                this.setSizeOfLevel(40);
                break;
            case MEDIUM:
                this.setSizeOfLevel(50);
                break;
            case LARGE:
                this.setSizeOfLevel(75);
                break;
            case XLARGE:
                this.setSizeOfLevel(90);
                break;
        }
    }
}
