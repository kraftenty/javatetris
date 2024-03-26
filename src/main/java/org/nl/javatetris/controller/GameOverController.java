package org.nl.javatetris.controller;

public class GameOverController {

    private Runnable onBackToMenu;

    public GameOverController(Runnable onBackToMenu) {
        this.onBackToMenu = onBackToMenu;
    }


}
