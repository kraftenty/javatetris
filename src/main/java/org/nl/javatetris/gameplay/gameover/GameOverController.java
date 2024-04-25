package org.nl.javatetris.gameplay.gameover;

public class GameOverController {

    private Runnable onBackToScoreBoard;

    public GameOverController(Runnable onBackToScoreBoard) {
        this.onBackToScoreBoard = onBackToScoreBoard;
    }

    //test용도
    public void onBackToScoreBoard() {
        onBackToScoreBoard.run();
    }


}
