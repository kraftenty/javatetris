package org.nl.javatetris.controller;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class PauseMenuController {

    private Runnable onResume;
    private Runnable onBackToMenu;
    private Runnable onSettings;
    private int selectedItemIndex = 0;
    private int menuItemsCount;

    public PauseMenuController(int menuItemsCount, Runnable onResume, Runnable onBackToMenu) {
        this.menuItemsCount = menuItemsCount;
        this.onResume = onResume;
        this.onBackToMenu = onBackToMenu;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                onResume.run();
                break;
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
                        onBackToMenu.run();
                        break;
                    case 2:
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
