package org.nl.javatetris.scoreboard;

import javafx.scene.input.KeyEvent;

public class ScoreBoardController {

    private Runnable onBackToMenu;
    private int selectedItemIndex = 0;

    public ScoreBoardController(Runnable onBackToMenu) {
        this.onBackToMenu = onBackToMenu;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
            case ENTER:
                onBackToMenu.run();
                break;
        }

    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }


}