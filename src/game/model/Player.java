package game.model;

public class Player {
    private String name;
    private int score = 0;
    private int steps = 0;

    public Gamespeed getGamespeed() {
        return gamespeed;
    }

    public void setGamespeed(Gamespeed gamespeed) {
        this.gamespeed = gamespeed;
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    private Gamespeed gamespeed;
    private GameDifficulty gameDifficulty;

    /**
     * It creates a Player object.
     * @param name is the player name.
     */
    public Player(String name) {
        this.name = name;
    }
    /**
     * It creates a Player object.
     * @param name is the player name.
     */
    public Player(String name,int score,int steps,GameDifficulty gameDifficulty, Gamespeed gamespeed) {
        this.name = name;
        this.steps = steps;
        this.score = score;
        this.gameDifficulty = gameDifficulty;
        this.gamespeed = gamespeed;
    }

    /**
     * It returns the player name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * It sets the player name.
     * @param name is the name of the Player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * It returns the score of the player.
     * @return score.
     */
    public int getScore() {
        return score;
    }


    /**
     * It resets the score.
     */
    public void resetScore(){
        this.score = 0;
        this.steps = 0;
    }
    /**
     * It sets the score of the Player.
     * @param score is the score of the player.
     */
    public void setScore(int score) {
        this.score += score;
    }

    public void countSteps() {
        steps++;
    }

    public int getSteps() {
        return steps;
    }
}
