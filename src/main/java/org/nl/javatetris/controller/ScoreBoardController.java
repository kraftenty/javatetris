package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
//import org.nl.javatetris.model.settings.Settings;

public class ScoreBoardController {

    private Runnable onBack;
    private int selectedItemIndex = 0;

    public ScoreBoardController(Runnable onResume) {
        this.onBack = onResume;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ENTER:
                onBack.run();
                break;
        }

    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
}
