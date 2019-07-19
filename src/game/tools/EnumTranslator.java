package game.tools;

import game.model.GameDifficulty;
import game.model.Gamespeed;

public class EnumTranslator {
    public static String GameDifficultyToString(GameDifficulty gameDifficulty){
        switch (gameDifficulty) {
            case EASY:
                return "Easy";
            case NORMAL:
                return "Normal";
            case HARD:
                return "Hard";
            case EXTREME:
                return "Extreme";
            case SuperExtreme:
                return "Super Extreme";
                default: return "Unknown";
        }
    }

    public static GameDifficulty GameDifficultyStringToEnum(String gameDifficulty){
        switch (gameDifficulty) {
            case "Easy":
                return GameDifficulty.EASY;
            case "Normal":
                return GameDifficulty.NORMAL;
            case "Hard":
                return GameDifficulty.HARD;
            case "Extreme":
                return GameDifficulty.EXTREME;
            case "Super Extreme":
                return GameDifficulty.SuperExtreme;
            default: return GameDifficulty.EASY;
        }
    }
    public static String GameSpeedToString(Gamespeed gamespeed){
        switch (gamespeed) {
            case SPEED1:
                return "Speed 1";
            case SPEED2:
                return "Speed 2";
            case SPEED3:
                return "Speed 3";
            case SPEED4:
                return "Speed 4";
            case SPEED5:
                return "Speed 5";
            case SPEED6:
                return "Speed 6";
            case SPEED7:
                return "Speed 7";
            case SPEED8:
                return "Speed 8";
            case SPEED9:
                return "Speed 9";
            case SPEED10:
                return "Speed 10";
            default: return "Unknown";
        }
    }

    public static Gamespeed GameSpeedStringToEnum(String gamespeed){
        switch (gamespeed) {
            case "Speed 1":
                return Gamespeed.SPEED1;
            case "Speed 2":
                return Gamespeed.SPEED2;
            case "Speed 3":
                return Gamespeed.SPEED3;
            case "Speed 4":
                return Gamespeed.SPEED4;
            case "Speed 5":
                return Gamespeed.SPEED5;
            case "Speed 6":
                return Gamespeed.SPEED6;
            case "Speed 7":
                return Gamespeed.SPEED7;
            case "Speed 8":
                return Gamespeed.SPEED8;
            case "Speed 9":
                return Gamespeed.SPEED9;
            case "Speed 10":
                return Gamespeed.SPEED10;
            default: return Gamespeed.SPEED1;
        }
    }
}
