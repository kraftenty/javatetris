package org.nl.javatetris.controller;

public class GameOverController {

    private Runnable onBackToScoreBoard;

    public GameOverController(Runnable onBackToScoreBoard) {
        this.onBackToScoreBoard = onBackToScoreBoard;
    }

}
