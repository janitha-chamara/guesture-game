package rps.app;

public class ScoreBoard {
    private int opponentScore = 0;
    private int playerScore = 0;

    public void increaseOpponentScore (int score) {
        this.opponentScore += score;
    }

    public void increasePlayerScore (int score) {
        this.playerScore += score;
    }

    public void decreaseOpponentScore (int score) {
        this.opponentScore -= score;
    }

    public void decreasePlayerScore (int score) {
        this.opponentScore -= score;
    }

    public void resetScores () {
        this.opponentScore = 0;
        this.playerScore = 0;
    }

    public int getOpponentScore(){
        return this.opponentScore;
    }

    public int getPlayerScore(){
        return this.playerScore;
    }
}
