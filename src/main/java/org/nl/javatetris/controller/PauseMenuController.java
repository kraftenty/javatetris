package org.nl.javatetris.controller;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class PauseMenuController {

    private Runnable onResume;
    private int selectedItemIndex = 0;
    private int menuItemsCount;

    public PauseMenuController(Runnable onResume, int menuItemsCount) {
        this.onResume = onResume;
        this.menuItemsCount = menuItemsCount;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch(selectedItemIndex) {
                    case 0:
                        onResume.run();
                        break;
                    case 1:
                        Platform.exit();
                        break;
                }
                break;
        }
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
